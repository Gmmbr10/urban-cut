package com.urbancut.models;

import com.urbancut.core.Model;

import java.time.LocalTime;
import java.util.Arrays;

public class Barbearia extends Model {
    private Integer idBarbearia;
    private Integer idDono;
    private String nome;
    private Integer idEndereco;
    private LocalTime tempoMedioAtendimento;
    private Barbeiro[] barbeiros;
    private DiaFuncionamento[] diasFuncionamento;

    private Barbearia(Integer idBarbearia, Integer idDono, String nome, Integer idEndereco, LocalTime tempoMedioAtendimento) {
        this.idBarbearia = idBarbearia;
        this.idDono = idDono;
        this.nome = nome;
        this.idEndereco = idEndereco;
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    private Barbearia(Integer idBarbearia, Integer idDono, String nome, Integer idEndereco, LocalTime tempoMedioAtendimento, Barbeiro[] barbeiros, DiaFuncionamento[] diasFuncionamento) {
        this.idBarbearia = idBarbearia;
        this.idDono = idDono;
        this.nome = nome;
        this.idEndereco = idEndereco;
        this.tempoMedioAtendimento = tempoMedioAtendimento;
        this.barbeiros = barbeiros;
        this.diasFuncionamento = diasFuncionamento;
    }

    public static class BarbeariaBuilder {
        private Integer idBarbearia;
        private Integer idDono;
        private String nome;
        private Integer idEndereco;
        private LocalTime tempoMedioAtendimento;

        public BarbeariaBuilder idBarbearia(Integer idBarbearia) {
            this.idBarbearia = idBarbearia;
            return this;
        }

        public BarbeariaBuilder idDono(Integer idDono) {
            this.idDono = idDono;
            return this;
        }

        public BarbeariaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public BarbeariaBuilder idEndereco(Integer idEndereco) {
            this.idEndereco = idEndereco;
            return this;
        }

        public BarbeariaBuilder tempoMedioAtendimento(Integer tempoMedioAtendimento) {
            int horas = (int) (tempoMedioAtendimento / 60);
            double restoMinutos = tempoMedioAtendimento % 60;
            int minutos = (int) restoMinutos;
            int segundos = (int) ((restoMinutos - minutos) * 60);

            this.tempoMedioAtendimento = LocalTime.of(horas, minutos, segundos);
            return this;
        }

        public BarbeariaBuilder tempoMedioAtendimento(LocalTime tempoMedioAtendimento) {
            this.tempoMedioAtendimento = tempoMedioAtendimento;
            return this;
        }

        public Barbearia build() {
            return new Barbearia(idBarbearia, idDono, nome, idEndereco, tempoMedioAtendimento);
        }
    }

    public Integer getIdBarbearia() {
        return idBarbearia;
    }

    public Integer getIdDono() {
        return idDono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public LocalTime getTempoMedioAtendimento() {
        return tempoMedioAtendimento;
    }

    public void setTempoMedioAtendimento(LocalTime tempoMedioAtendimento) {
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    public Barbeiro[] getBarbeiros() {
        return barbeiros;
    }

    public void setBarbeiros(Barbeiro[] barbeiros) {
        this.barbeiros = barbeiros;
    }

    public DiaFuncionamento[] getDiasFuncionamento() {
        return diasFuncionamento;
    }

    public void setDiasFuncionamento(DiaFuncionamento[] diasFuncionamento) {
        this.diasFuncionamento = diasFuncionamento;
    }

    @Override
    public String toString() {
        return "Barbearia{" + "idBarbearia=" + idBarbearia + ", idDono=" + idDono + ", nome='" + nome + '\'' + ", idEndereco=" + idEndereco + ", tempoMedioAtendimento=" + tempoMedioAtendimento + ", barbeiros=" + Arrays.toString(barbeiros) + ", diasFuncionamento=" + Arrays.toString(diasFuncionamento) + '}';
    }
}
