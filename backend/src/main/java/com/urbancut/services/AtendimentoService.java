package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.Atendimento;
import com.urbancut.repositories.AtendimentoRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.urbancut.utils.HorarioValidator.isDentroDoHorarioBloqueado;
import static com.urbancut.utils.HorarioValidator.isDentroDoHorarioDaBarbearia;

public class AtendimentoService extends Service<AtendimentoRepository> {
    public AtendimentoService() {
        this.repository = new AtendimentoRepository();
    }

    public Response<Atendimento> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idAtendimento"));

        if (id == 0) {
            return new Response<>(400, "Preencha todos os campos!", null);
        }

        try {
            Atendimento atendimento = this.repository.searchById(id);
            return new Response<>(200, atendimento);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        int idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        int idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        LocalDate data = LocalDate.parse(request.getParameter("data"));
        LocalTime time = LocalTime.parse(request.getParameter("horario"));

        if (idBarbearia == 0 || idBarbeiro == 0 || idCliente == 0 || data == null || time == null) {
            return new Response<>(400, "Preencha todos os campos!", null);
        }

        if (!data.isAfter(LocalDate.now())) {
            return new Response<>(400,"A data deve ser posterior ao dia atual!",null);
        }

        Atendimento atendimento = new Atendimento.AtendimentoBuilder().Atendimento(LocalDateTime.of(data, time)).idBarbeiro(idBarbeiro).idBarbearia(idBarbearia).idCliente(idCliente).build();

        try {

            if (repository.isHorarioAgendado(atendimento.getAtendimento(), atendimento.getIdBarbeiro())) {
                return new Response<>(200, "Não foi possível realizar o agendamento, este horário já foi agendado", false);
            }

            if (isDentroDoHorarioBloqueado(atendimento.getAtendimento(), atendimento.getIdBarbeiro())) {
                return new Response<>(200, "Não foi possível realizar o agendamento, horário bloqueado pelo barbeiro", false);
            }

            if (!isDentroDoHorarioDaBarbearia(atendimento.getAtendimento(),atendimento.getIdBarbearia())) {
                return new Response<>(200, "Não foi possível realizar o agendamento, horário fora do estabelecimento", false);
            }

            repository.save(atendimento);
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }
    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idAtendimento"));

        if (id == 0) {
            return new Response<>(400, "Preencha todos os campos!", false);
        }

        try {
            repository.delete(id);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }

    }
}
