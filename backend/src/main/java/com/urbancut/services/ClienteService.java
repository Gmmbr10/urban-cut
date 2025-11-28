package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.Cliente;
import com.urbancut.repositories.ClienteRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class ClienteService extends Service<ClienteRepository> {

    public ClienteService() {
        this.repository = new ClienteRepository();
    }

    public Response<Cliente> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idCliente"));

        if (id == 0) {
            return new Response<>(400, "Preencha todos os campos!", null);
        }

        try {
            Cliente cliente = this.repository.searchById(id);
            return new Response<>(200, cliente);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", null);
        }
    }

    public Response<Cliente> searchByEmail(HttpServletRequest request) {
        String email = request.getParameter("email");

        if (email.isBlank()) {
            return new Response<>(400, "Preencha todos os campos!", null);
        }

        try {
            Cliente cliente = this.repository.searchByEmail(email);
            return new Response<>(200, cliente);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            return new Response<>(400, "Preencha todos os campos!", false);
        }

        Cliente cliente = new Cliente.ClienteBuilder().nome(nome).email(email).senha(senha).build();

        try {
            repository.save(cliente);
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idCliente"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (id == 0 || nome.isBlank() || email.isBlank() || senha.isBlank()) {
            return new Response<>(400, "Preencha todos os campos!", false);
        }

        Cliente cliente = new Cliente.ClienteBuilder().idCliente(id).nome(nome).email(email).senha(senha).build();

        try {
            repository.update(cliente);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Não foi possível realizar esta ação! Erro no lado do servidor!", false);
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idCliente"));

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
