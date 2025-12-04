package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.models.Barbearia;
import com.urbancut.models.Barbeiro;
import com.urbancut.models.DiaFuncionamento;
import com.urbancut.repositories.BarbeariaRepository;
import com.urbancut.repositories.BarbeiroRepository;
import com.urbancut.repositories.DiaFuncionamentoRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HorariosDisponiveisService {
    public Response<List<LocalTime>> list(HttpServletRequest request){
        Integer idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        Integer idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        String diaEscolhido = request.getParameter("diaEscolhido");

        if (idBarbearia == null || idBarbeiro == null || diaEscolhido.isBlank()) {
            return new Response<>(400, "Preencha todos os campos!", null);
        }

        try {
            Barbeiro barbeiro = new BarbeiroRepository().searchById(idBarbeiro);
            Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);

            String diaSemana = LocalDate.parse(diaEscolhido).getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            diaSemana = Normalizer.normalize(diaSemana, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            diaSemana = diaSemana.substring(0, 1).toUpperCase() + diaSemana.substring(1);
            diaSemana = diaSemana.replace("feira","Feira");

            List<DiaFuncionamento> diasFuncionamento = new DiaFuncionamentoRepository().searchByBarbearia(barbearia.getIdBarbearia());

            DiaFuncionamento diaFuncionamento = null;

            for (DiaFuncionamento df : diasFuncionamento) {
                if (df.getDiaSemana().equals(diaSemana)) {
                    diaFuncionamento = df;
                    break;
                }
            }

            if (diaFuncionamento == null){
                return new Response<>(200, "Estabelecimento fechado!", null);
            }

            List<LocalTime> horariosDisponiveis = new ArrayList<>();

            LocalTime horaAdicionada = diaFuncionamento.getHoraAbertura();

            if (barbeiro.getHorarioBloqueado() != null) {

                while ( barbeiro.getHorarioBloqueado().getInicio().minusMinutes(1).isAfter(horaAdicionada) ) {
                    horariosDisponiveis.add(horaAdicionada);
                    horaAdicionada = horaAdicionada.plusMinutes(barbearia.getTempoMedioAtendimento().getMinute());
                }

                horaAdicionada = barbeiro.getHorarioBloqueado().getFim();

                while ( diaFuncionamento.getHoraFechamento().minusMinutes(barbearia.getTempoMedioAtendimento().getMinute()).isAfter(horaAdicionada) ) {
                    horariosDisponiveis.add(horaAdicionada);
                    horaAdicionada = horaAdicionada.plusMinutes(barbearia.getTempoMedioAtendimento().getMinute());
                }

            } else {

                while ( diaFuncionamento.getHoraFechamento().minusMinutes(1).isAfter(horaAdicionada) ) {
                    horariosDisponiveis.add(horaAdicionada);
                    horaAdicionada = horaAdicionada.plusMinutes(barbearia.getTempoMedioAtendimento().getMinute());
                }

            }

            return new Response<>(204, horariosDisponiveis);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
