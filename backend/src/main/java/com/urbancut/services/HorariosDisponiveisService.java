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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorariosDisponiveisService {
    public Response<List<LocalTime>> list(HttpServletRequest request){
        Integer idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        Integer idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        String diaEscolhido = request.getParameter("diaEscolhido");

        if (idBarbearia == null || idBarbeiro == null || diaEscolhido.isBlank()) {
            return new Response<>(400, "Falta de informações!", null);
        }

        try {
            Barbeiro barbeiro = new BarbeiroRepository().searchById(idBarbeiro);
            Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);
            DayOfWeek dia = LocalDate.parse(diaEscolhido).getDayOfWeek();

            String diaSemana = null;

            switch (dia) {
                case SUNDAY:
                    diaSemana = "Domingo";
                    break;
                case MONDAY:
                    diaSemana = "Segunda-feira";
                    break;
                case TUESDAY:
                    diaSemana = "Terça-feira";
                    break;
                case WEDNESDAY:
                    diaSemana = "Quarta-feira";
                    break;
                case THURSDAY:
                    diaSemana = "Quinta-feira";
                    break;
                case FRIDAY:
                    diaSemana = "Sexta-feira";
                    break;
                case SATURDAY:
                    diaSemana = "Sábado";
                    break;
            }

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

            while ( barbeiro.getHorarioBloqueado().getInicio().minusMinutes(1).isAfter(horaAdicionada) ) {
                horariosDisponiveis.add(horaAdicionada);
                horaAdicionada.plusMinutes(Long.parseLong(barbearia.getTempoMedioAtendimento().toString()));
            }

            horaAdicionada = barbeiro.getHorarioBloqueado().getFim();

            while ( barbeiro.getHorarioBloqueado().getFim().minusMinutes(Long.parseLong(barbearia.getTempoMedioAtendimento().toString())).isAfter(horaAdicionada) ) {
                horariosDisponiveis.add(horaAdicionada);
                horaAdicionada.plusMinutes(Long.parseLong(barbearia.getTempoMedioAtendimento().toString()));
            }

            return new Response<>(204, horariosDisponiveis);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
