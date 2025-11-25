package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.Atendimento;
import com.urbancut.models.Barbearia;
import com.urbancut.models.Barbeiro;
import com.urbancut.models.DiaFuncionamento;
import com.urbancut.repositories.AtendimentoRepository;
import com.urbancut.repositories.BarbeariaRepository;
import com.urbancut.repositories.BarbeiroRepository;
import com.urbancut.repositories.DiaFuncionamentoRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AtendimentoService extends Service<AtendimentoRepository> {
    public AtendimentoService() {
        this.repository = new AtendimentoRepository();
    }

    public Response<Atendimento> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idAtendimento"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!", null);
        }

        try {
            Atendimento atendimento = this.repository.searchById(id);
            return new Response<>(200, atendimento);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        int idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        int idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        LocalDate data = LocalDate.parse(request.getParameter("data"));
        LocalTime time = LocalTime.parse(request.getParameter("horario"));

        if (idBarbearia == 0 || idBarbeiro == 0 || idCliente == 0 || data.isAfter(LocalDate.now())) {
            return new Response<>(400, "Falta de informações!", null);
        }

        Atendimento atendimento = new Atendimento.AtendimentoBuilder().Atendimento(LocalDateTime.of(data, time)).idBarbeiro(idBarbeiro).idBarbearia(idBarbearia).idCliente(idCliente).build();

        try {

            if (repository.isHorarioAgendado(atendimento.getAtendimento(), atendimento.getIdBarbeiro())) {
                return new Response<>(200, "Não foi possível realizar o agendamento, este horário já foi agendado", false);
            }

            if (isDentroDoHorarioBloqueado(atendimento.getAtendimento(), atendimento.getIdBarbeiro())) {
                return new Response<>(200, "Não foi possível realizar o agendamento, horário bloqueado pelo barbeiro", false);
            }

            repository.save(atendimento);
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }
    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idAtendimento"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!", false);
        }

        try {
            repository.delete(id);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }

    }

    private boolean isDentroDoHorarioBloqueado(LocalDateTime dataHora, int idBarbeiro) throws SQLException {
        Barbeiro barbeiro = new BarbeiroRepository().searchById(idBarbeiro);

        if (barbeiro == null) return false;

        boolean isDentroDoHorarioBloqueado = barbeiro.getHorarioBloqueado().getInicio().isBefore(dataHora.toLocalTime()) && barbeiro.getHorarioBloqueado().getFim().isAfter(dataHora.toLocalTime());

        return isDentroDoHorarioBloqueado;
    }

    private boolean isDentroDoHorarioDaBarbearia(LocalDateTime dataHora, int idBarbearia) throws SQLException {
        Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);

        if (barbearia == null) return false;

        List<DiaFuncionamento> diasFuncionamento = new DiaFuncionamentoRepository().searchByBarbearia(barbearia.getIdBarbearia());

        DayOfWeek dia = dataHora.toLocalDate().getDayOfWeek();

        for (DiaFuncionamento diaFuncionamento : diasFuncionamento) {
            switch (dia) {
                case SUNDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Domingo")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case MONDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Segunda")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case TUESDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Terça")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case WEDNESDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Quarta")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case THURSDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Quinta")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case FRIDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Sexta")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case SATURDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Sábado")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
            }
        }

        return false;
    }
}
