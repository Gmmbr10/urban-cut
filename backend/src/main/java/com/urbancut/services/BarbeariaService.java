package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.Barbearia;
import com.urbancut.repositories.BarbeariaRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class BarbeariaService extends Service<BarbeariaRepository> {
    public BarbeariaService() {
        this.repository = new BarbeariaRepository();
    }

    public Response<Barbearia> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbearia"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!");
        }

        try {
            Barbearia barbearia = this.repository.searchById(id);
            return new Response<>(200, barbearia);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        int idDono = Integer.parseInt(request.getParameter("idDono"));
        int idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        String nome = request.getParameter("nome");
        String urlMaps = request.getParameter("urlMaps");
        int tempoMedioAtendimento = Integer.parseInt(request.getParameter("tempoMedioAtendimento"));

        if (idDono == 0 || nome == null || idBarbearia == 0 || urlMaps == null || tempoMedioAtendimento == 0) {
            return new Response<>(400, "Falta de informações!");
        }

        Barbearia barbeiro = new Barbearia.BarbeariaBuilder().idDono(idDono).idBarbearia(idBarbearia).nome(nome).urlMaps(urlMaps).tempoMedioAtendimento(tempoMedioAtendimento).build();

        try {
            repository.save(barbeiro);
            return new Response<>(201);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        int idDono = Integer.parseInt(request.getParameter("idDono"));
        int idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        String nome = request.getParameter("nome");
        String urlMaps = request.getParameter("urlMaps");
        int tempoMedioAtendimento = Integer.parseInt(request.getParameter("tempoMedioAtendimento"));

        if (idDono == 0 || nome == null || idBarbearia == 0 || urlMaps == null || tempoMedioAtendimento == 0) {
            return new Response<>(400, "Falta de informações!");
        }

        Barbearia barbearia = new Barbearia.BarbeariaBuilder().idDono(idDono).idBarbearia(idBarbearia).nome(nome).urlMaps(urlMaps).tempoMedioAtendimento(tempoMedioAtendimento).build();

        try {
            repository.update(barbearia);
            return new Response<>(204);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbearia"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!");
        }

        try {
            repository.delete(id);
            return new Response<>(204);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }

    }
}
