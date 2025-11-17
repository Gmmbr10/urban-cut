package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.HorarioBloqueado;
import com.urbancut.repositories.HorarioBloqueadoRepository;
import com.urbancut.utils.TimeValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.LocalTime;

public class HorarioBloqueadoService extends Service<HorarioBloqueadoRepository> {
    public HorarioBloqueadoService() {
        this.repository = new HorarioBloqueadoRepository();
    }

    public Response<HorarioBloqueado> searchById(HttpServletRequest request) {

        int idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));

        if (idBarbeiro == 0) {
            return new Response<>(400, "Falta de informações!", null);
        }

        try {
            HorarioBloqueado horario = this.repository.searchById(idBarbeiro);
            return new Response<>(200, horario);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        int idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        String inicioStr = request.getParameter("inicio");
        String fimStr = request.getParameter("fim");

        if (idBarbeiro == 0 || inicioStr == null || fimStr == null) {
            return new Response<>(400, "Falta de informações!", false);
        }

        if (!TimeValidator.validar(inicioStr) || !TimeValidator.validar(fimStr)) {
            return new Response<>(400, "O tempo deve estar formatado da seguinte maneira: 00:00!", false);
        }

        LocalTime inicio = LocalTime.parse(inicioStr);
        LocalTime fim = LocalTime.parse(fimStr);

        HorarioBloqueado model = new HorarioBloqueado.HorarioBloqueadoBuilder()
                .idBarbeiro(idBarbeiro)
                .inicio(inicio)
                .fim(fim)
                .build();

        try {
            repository.save(model);
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        int idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));
        int idHorarioBloqueado = Integer.parseInt(request.getParameter("idHorarioBloqueado"));
        String inicioStr = request.getParameter("inicio");
        String fimStr = request.getParameter("fim");

        if (idBarbeiro == 0 || idHorarioBloqueado == 0 || inicioStr == null || fimStr == null) {
            return new Response<>(400, "Falta de informações!", false);
        }

        if (!TimeValidator.validar(inicioStr) || !TimeValidator.validar(fimStr)) {
            return new Response<>(400, "O tempo deve estar formatado da seguinte maneira: 00:00!", false);
        }

        LocalTime inicio = LocalTime.parse(inicioStr);
        LocalTime fim = LocalTime.parse(fimStr);

        HorarioBloqueado model = new HorarioBloqueado.HorarioBloqueadoBuilder()
                .idHorarioBloqueado(idHorarioBloqueado)
                .idBarbeiro(idBarbeiro)
                .inicio(inicio)
                .fim(fim)
                .build();

        try {
            repository.update(model);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }
    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int idHorarioBloqueado = Integer.parseInt(request.getParameter("idHorarioBloqueado"));

        if (idHorarioBloqueado == 0) {
            return new Response<>(400, "Falta de informações!", false);
        }

        try {
            repository.delete(idHorarioBloqueado);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }
    }
}
