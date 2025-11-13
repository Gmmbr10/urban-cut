package services;

import core.Service;
import jakarta.servlet.http.HttpServletRequest;
import models.Cliente;
import core.Response;
import repositories.ClienteRepository;

import java.sql.SQLException;

public class ClienteService extends Service<ClienteRepository> {

    public ClienteService() {
        this.repository = new ClienteRepository();
    }

    public Response<Cliente> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idCliente"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!");
        }

        try {
            Cliente cliente = this.repository.searchById(id);
            return new Response<>(200, cliente);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }
    }

    public Response<Cliente> searchByEmail(HttpServletRequest request) {
        String email = request.getParameter("email");

        if (email == null) {
            return new Response<>(400, "Falta de informações!");
        }

        try {
            Cliente cliente = this.repository.searchByEmail(email);
            return new Response<>(200, cliente);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (nome == null || email == null || senha == null) {
            return new Response<>(400, "Falta de informações!");
        }

        Cliente cliente = new Cliente.ClienteBuilder().nome(nome).email(email).senha(senha).build();

        try {
            repository.save(cliente);
            return new Response<>(201);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idCliente"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (id == 0 || nome == null || email == null || senha == null) {
            return new Response<>(400, "Falta de informações!");
        }

        Cliente cliente = new Cliente.ClienteBuilder().idCliente(id).nome(nome).email(email).senha(senha).build();

        try {
            repository.update(cliente);
            return new Response<>(204);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage());
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idCliente"));

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
