package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.DiaFuncionamento;
import com.urbancut.repositories.DiaFuncionamentoRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class DiaFuncionamentoService extends Service<DiaFuncionamentoRepository> {
    private static final List<String> DIAS_ENUM = Arrays.asList("Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado");

    public DiaFuncionamentoService() {
        this.repository = new DiaFuncionamentoRepository();
    }

    private boolean isDiaSemanaValido(String dia) {
        return DIAS_ENUM.contains(dia);
    }

    public Response<DiaFuncionamento> searchById(HttpServletRequest request) {

        String idStr = request.getParameter("idDiaFuncionamento");

        if (idStr == null) {
            return new Response<>(400, "Preencha todos os campos!", null);
        }

        int id = Integer.parseInt(idStr);

        try {
            DiaFuncionamento dia = this.repository.searchById(id);
            return new Response<>(200, dia);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        String idBarbeariaStr = request.getParameter("idBarbearia");
        String[] diaSemana = request.getParameterValues("diaSemana");
        String horaAberturaStr = request.getParameter("horaAbertura");
        String horaFechamentoStr = request.getParameter("horaFechamento");

        if (idBarbeariaStr == null || diaSemana == null || horaAberturaStr == null || horaFechamentoStr == null) {
            return new Response<>(400, "Preencha todos os campos!", false);
        }

        for (String dia : diaSemana) {
            if (!isDiaSemanaValido(dia)) {
                return new Response<>(400, "Dia da semana inválido! Valores permitidos: " + DIAS_ENUM, false);
            }
        }

        int idBarbearia = Integer.parseInt(idBarbeariaStr);
        LocalTime horaAbertura = LocalTime.parse(horaAberturaStr);
        LocalTime horaFechamento = LocalTime.parse(horaFechamentoStr);

        try {
            for (String strDia : diaSemana) {
                DiaFuncionamento dia = new DiaFuncionamento.DiasFuncionamentoBuilder().idBarbearia(idBarbearia).diaSemana(strDia).horaAbertura(horaAbertura).horaFechamento(horaFechamento).build();
                repository.save(dia);
            }
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        String idStr = request.getParameter("idDiaFuncionamento");
        String idBarbeariaStr = request.getParameter("idBarbearia");
        String horaAberturaStr = request.getParameter("horaAbertura");
        String horaFechamentoStr = request.getParameter("horaFechamento");

        if (idStr == null || idBarbeariaStr == null || horaAberturaStr == null || horaFechamentoStr == null) {
            return new Response<>(400, "Preencha todos os campos!", false);
        }

        int idDiaFuncionamento = Integer.parseInt(idStr);
        int idBarbearia = Integer.parseInt(idBarbeariaStr);
        LocalTime horaAbertura = LocalTime.parse(horaAberturaStr);
        LocalTime horaFechamento = LocalTime.parse(horaFechamentoStr);

        DiaFuncionamento dia = new DiaFuncionamento.DiasFuncionamentoBuilder().idDiaFuncionamento(idDiaFuncionamento).idBarbearia(idBarbearia).horaAbertura(horaAbertura).horaFechamento(horaFechamento).build();

        try {
            repository.update(dia);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        String idStr = request.getParameter("idDiaFuncionamento");

        if (idStr == null) {
            return new Response<>(400, "Preencha todos os campos!", false);
        }

        int id = Integer.parseInt(idStr);

        try {
            repository.delete(id);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }

    }
}
