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

public class AtendimentoService extends Service<AtendimentoRepository> {
    public AtendimentoService() {
        this.repository = new AtendimentoRepository();
    }

    public Response<Atendimento> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idAtendimento"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!",null);
        }

        try {
            Atendimento atendimento = this.repository.searchById(id);
            return new Response<>(200, atendimento);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        int idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        int idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        LocalDate data = LocalDate.parse(request.getParameter("data"));
        LocalTime time = LocalTime.parse(request.getParameter("horario"));

        if (idBarbearia == 0 || idBarbeiro == 0 || idCliente == 0 || data.isAfter(LocalDate.now())) {
            return new Response<>(400, "Falta de informações!",null);
        }

        Atendimento atendimento = new Atendimento.AtendimentoBuilder()
                .Atendimento(LocalDateTime.of(data,time))
                .idBarbeiro(idBarbeiro)
                .idBarbearia(idBarbearia)
                .idCliente(idCliente)
                .build();

        try {
            repository.save(atendimento);
            return new Response<>(201,true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),false);
        }
    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idAtendimento"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!",false);
        }

        try {
            repository.delete(id);
            return new Response<>(204,true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),false);
        }

    }
}
