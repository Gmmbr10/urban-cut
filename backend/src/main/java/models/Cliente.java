package models;

import core.Model;

public class Cliente extends Model {

    private Integer idCliente;
    private String nome;
    private String email;
    private String senha;

    private Cliente(Integer idCliente, String nome, String email, String senha) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public static class ClienteBuilder {

        private Integer idCliente = null;
        private String nome = null;
        private String email = null;
        private String senha = null;

        public ClienteBuilder idCliente(Integer idCliente) {
            this.idCliente = idCliente;
            return this;
        }

        public ClienteBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ClienteBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClienteBuilder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Cliente build() {
            return new Cliente(idCliente, nome, email, senha);
        }
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
