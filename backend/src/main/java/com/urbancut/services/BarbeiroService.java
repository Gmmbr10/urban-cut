package com.urbancut.services;

import com.urbancut.core.Service;
import com.urbancut.models.Cliente;
import jakarta.servlet.http.HttpServletRequest;
import com.urbancut.models.Barbeiro;
import com.urbancut.core.Response;
import com.urbancut.repositories.BarbeiroRepository;

import java.sql.SQLException;

public class BarbeiroService extends Service<BarbeiroRepository> {

    public BarbeiroService() {
        this.repository = new BarbeiroRepository();
    }

    public Response<Barbeiro> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbeiro"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!",null);
        }

        try {
            Barbeiro barbeiro = this.repository.searchById(id);
            return new Response<>(200, barbeiro);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),null);
        }
    }

    public Response<Barbeiro> searchByEmail(HttpServletRequest request) {
        String email = request.getParameter("email");

        if (email == null) {
            return new Response<>(400, "Falta de informações!",null);
        }

        try {
            Barbeiro barbeiro = this.repository.searchByEmail(email);
            return new Response<>(200, barbeiro);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (nome == null || email == null || senha == null) {
            return new Response<>(400, "Falta de informações!",false);
        }

        Integer idBarbearia = request.getParameter("idBarbearia") != null ? Integer.parseInt(request.getParameter("idBarbearia")) : null;

        Barbeiro barbeiro = new Barbeiro.BarbeiroBuilder().nome(nome).email(email).senha(senha).idBarbearia(idBarbearia).build();

        try {
            repository.save(barbeiro);
            return new Response<>(201,true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),null);
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbeiro"));
        int id_barbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (id == 0 || nome == null || email == null || senha == null) {
            return new Response<>(400, "Falta de informações!",false);
        }

        Barbeiro barbeiro = new Barbeiro.BarbeiroBuilder().idBarbeiro(id).idBarbearia(id_barbearia).nome(nome).email(email).senha(senha).build();

        try {
            repository.update(barbeiro);
            return new Response<>(204,true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),true);
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbeiro"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!",false);
        }

        try {
            repository.delete(id);
            return new Response<>(204,true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(),true);
        }

    }
}
