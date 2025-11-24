package com.urbancut.models;

import com.urbancut.core.Model;

public class Endereco extends Model {
    private Integer idEndereco;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;

    private Endereco(Integer idEndereco, String cep, String estado, String cidade, String bairro, String logradouro, int numero, String complemento) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }


    public static final class EnderecoBuilder {
        private Integer idEndereco;
        private String cep;
        private String estado;
        private String cidade;
        private String bairro;
        private String logradouro;
        private int numero;
        private String complemento;

        public EnderecoBuilder idEndereco(Integer idEndereco) {
            this.idEndereco = idEndereco;
            return this;
        }

        public EnderecoBuilder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public EnderecoBuilder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public EnderecoBuilder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public EnderecoBuilder bairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public EnderecoBuilder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public EnderecoBuilder numero(int numero) {
            this.numero = numero;
            return this;
        }

        public EnderecoBuilder complemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public Endereco build() {
            return new Endereco(idEndereco, cep, estado, cidade, bairro, logradouro, numero, complemento);
        }
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", cep='" + cep + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
