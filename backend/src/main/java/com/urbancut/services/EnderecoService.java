package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.Endereco;
import com.urbancut.repositories.EnderecoRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class EnderecoService extends Service<EnderecoRepository> {
    public EnderecoService() {
        this.repository = new EnderecoRepository();
    }

    public Response<Endereco> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idEndereco"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!", null);
        }

        try {
            Endereco endereco = this.repository.searchById(id);
            return new Response<>(200, endereco);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), null);
        }
    }

    public Response<Boolean> register(HttpServletRequest request) {

        String cep = request.getParameter("cep");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String bairro = request.getParameter("bairro");
        String logradouro = request.getParameter("logradouro");
        String numeroStr = request.getParameter("numero");
        String complemento = request.getParameter("complemento");

        if (cep == null || estado == null || cidade == null || bairro == null ||
                logradouro == null || numeroStr == null) {
            return new Response<>(400, "Falta de informações!", false);
        }

        int numero = Integer.parseInt(numeroStr);

        Endereco endereco = new Endereco.EnderecoBuilder()
                .cep(cep)
                .estado(estado)
                .cidade(cidade)
                .bairro(bairro)
                .logradouro(logradouro)
                .numero(numero)
                .complemento(complemento)
                .build();

        try {
            repository.save(endereco);
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        String idStr = request.getParameter("idEndereco");
        String cep = request.getParameter("cep");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String bairro = request.getParameter("bairro");
        String logradouro = request.getParameter("logradouro");
        String numeroStr = request.getParameter("numero");
        String complemento = request.getParameter("complemento");

        if (idStr == null || cep == null || estado == null || cidade == null ||
                bairro == null || logradouro == null || numeroStr == null) {
            return new Response<>(400, "Falta de informações!", false);
        }

        int idEndereco = Integer.parseInt(idStr);
        int numero = Integer.parseInt(numeroStr);

        Endereco endereco = new Endereco.EnderecoBuilder()
                .idEndereco(idEndereco)
                .cep(cep)
                .estado(estado)
                .cidade(cidade)
                .bairro(bairro)
                .logradouro(logradouro)
                .numero(numero)
                .complemento(complemento)
                .build();

        try {
            repository.update(endereco);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idEndereco"));

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
}
