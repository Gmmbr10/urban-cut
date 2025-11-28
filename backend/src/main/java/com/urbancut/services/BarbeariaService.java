package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.core.Service;
import com.urbancut.models.Barbearia;
import com.urbancut.models.DiaFuncionamento;
import com.urbancut.models.Endereco;
import com.urbancut.repositories.BarbeariaRepository;
import com.urbancut.repositories.DiaFuncionamentoRepository;
import com.urbancut.repositories.EnderecoRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class BarbeariaService extends Service<BarbeariaRepository> {
    public BarbeariaService() {
        this.repository = new BarbeariaRepository();
    }

    public Response<Barbearia> searchById(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbearia"));

        if (id == 0) {
            return new Response<>(400, "Falta de informações!", null);
        }

        try {
            Barbearia barbearia = this.repository.searchById(id);
            DiaFuncionamento[] diasFuncionamento = new DiaFuncionamentoRepository().searchByBarbearia(barbearia.getIdBarbearia()).toArray(new DiaFuncionamento[0]);
            barbearia.setDiasFuncionamento(diasFuncionamento);
            return new Response<>(200, barbearia);
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

        if (cep.isBlank() || estado.isBlank() || cidade.isBlank() || bairro.isBlank() || logradouro.isBlank() || numeroStr.isBlank()) {
            return new Response<>(400, "Falta de informações!", false);
        }

        int numero = Integer.parseInt(numeroStr);

        Endereco endereco = new Endereco.EnderecoBuilder().cep(cep).estado(estado).cidade(cidade).bairro(bairro).logradouro(logradouro).numero(numero).complemento(complemento).build();

        int enderecoId;

        try {
            enderecoId = (new EnderecoRepository()).saveWithReturn(endereco);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }

        int idDono = Integer.parseInt(request.getParameter("idDono"));
        String nome = request.getParameter("nome");
        int tempoMedioAtendimento = Integer.parseInt(request.getParameter("tempoMedioAtendimento"));

        if (idDono == 0 || nome.isBlank() || tempoMedioAtendimento == 0) {
            return new Response<>(400, "Falta de informações!", false);
        }

        Barbearia barbearia = new Barbearia.BarbeariaBuilder().idDono(idDono).nome(nome).idEndereco(enderecoId).tempoMedioAtendimento(tempoMedioAtendimento).build();

        try {
            repository.save(barbearia);
            return new Response<>(201, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }
    }

    public Response<Boolean> update(HttpServletRequest request) {

        int idDono = Integer.parseInt(request.getParameter("idDono"));
        int idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
        String nome = request.getParameter("nome");
        int tempoMedioAtendimento = Integer.parseInt(request.getParameter("tempoMedioAtendimento"));

        if (idDono == 0 || nome.isBlank() || idBarbearia == 0 || tempoMedioAtendimento == 0) {
            return new Response<>(400, "Falta de informações!", false);
        }

        Barbearia barbearia = new Barbearia.BarbeariaBuilder().idDono(idDono).idBarbearia(idBarbearia).nome(nome).tempoMedioAtendimento(tempoMedioAtendimento).build();

        try {
            repository.update(barbearia);
            return new Response<>(204, true);
        } catch (SQLException e) {
            return new Response<>(500, "Erro durante a execução!\nErro: " + e.getMessage(), false);
        }

    }

    public Response<Boolean> delete(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("idBarbearia"));

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
