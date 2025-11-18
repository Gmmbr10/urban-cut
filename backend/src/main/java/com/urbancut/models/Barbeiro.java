package com.urbancut.models;

import com.urbancut.core.Model;

public class Barbeiro extends Model {
    private Integer idBarbeiro;
    private String nome;
    private String email;
    private String senha;
    private Integer idBarbearia;
    private HorarioBloqueado horarioBloqueado;

    private Barbeiro(Integer idBarbeiro, String nome, String email, String senha, Integer idBarbearia, HorarioBloqueado horarioBloqueado) {
        this.idBarbeiro = idBarbeiro;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idBarbearia = idBarbearia;
        this.horarioBloqueado = horarioBloqueado;
    }

    public static class BarbeiroBuilder {
        private Integer idBarbeiro = null;
        private String nome = null;
        private String email = null;
        private String senha = null;
        private Integer idBarbearia = null;
        private HorarioBloqueado horarioBloqueado = null;

        public BarbeiroBuilder idBarbeiro(int id) {
            this.idBarbeiro = id;
            return this;
        }

        public BarbeiroBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public BarbeiroBuilder email(String email) {
            this.email = email;
            return this;
        }

        public BarbeiroBuilder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public BarbeiroBuilder idBarbearia(int id) {
            this.idBarbearia = id == 0 ? null : id;
            return this;
        }

        public BarbeiroBuilder horarioBloqueado(HorarioBloqueado horarioBloqueado){
            this.horarioBloqueado = horarioBloqueado;
            return this;
        }

        public Barbeiro build() {
            return new Barbeiro(idBarbeiro, nome, email, senha, idBarbearia,horarioBloqueado);
        }
    }

    public Integer getIdBarbeiro() {
        return idBarbeiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(Integer idBarbearia) {
        this.idBarbearia = idBarbearia;
    }

    public HorarioBloqueado getHorarioBloqueado() {
        return horarioBloqueado;
    }

    public void setHorarioBloqueado(HorarioBloqueado horarioBloqueado) {
        this.horarioBloqueado = horarioBloqueado;
    }

    @Override
    public String toString() {
        return "Barbeiro{" +
                "idBarbeiro=" + idBarbeiro +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", idBarbearia=" + idBarbearia +
                ", horarioBloqueado=" + horarioBloqueado +
                '}';
    }
}
