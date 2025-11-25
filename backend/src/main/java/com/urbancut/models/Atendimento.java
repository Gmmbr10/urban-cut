package com.urbancut.models;

import com.urbancut.core.Model;

import java.time.LocalDateTime;

public class Atendimento extends Model {
    private Integer idAtendimento;
    private Integer idBarbearia;
    private Integer idBarbeiro;
    private Integer idCliente;
    private LocalDateTime atendimento;

    private Atendimento(Integer idAtendimento, Integer idBarbearia, Integer idBarbeiro, Integer idCliente, LocalDateTime atendimento) {
        this.idAtendimento = idAtendimento;
        this.idBarbearia = idBarbearia;
        this.idBarbeiro = idBarbeiro;
        this.idCliente = idCliente;
        this.atendimento = atendimento;
    }

    public static class AtendimentoBuilder {
        private Integer idAtendimento;
        private Integer idBarbearia;
        private Integer idBarbeiro;
        private Integer idCliente;
        private LocalDateTime atendimento;

        public AtendimentoBuilder idAtendimento(Integer idAtendimento) {
            this.idAtendimento = idAtendimento;
            return this;
        }

        public AtendimentoBuilder idBarbearia(Integer idBarbearia) {
            this.idBarbearia = idBarbearia;
            return this;
        }

        public AtendimentoBuilder idBarbeiro(Integer idBarbeiro) {
            this.idBarbeiro = idBarbeiro;
            return this;
        }

        public AtendimentoBuilder idCliente(Integer idCliente) {
            this.idCliente = idCliente;
            return this;
        }

        public AtendimentoBuilder Atendimento(LocalDateTime atendimento) {
            this.atendimento = atendimento;
            return this;
        }

        public Atendimento build() {
            return new Atendimento(this.idAtendimento, this.idBarbearia, this.idBarbeiro, this.idCliente, this.atendimento);
        }
    }

    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public Integer getIdBarbearia() {
        return idBarbearia;
    }

    public Integer getIdBarbeiro() {
        return idBarbeiro;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public LocalDateTime getAtendimento() {
        return atendimento;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "idAtendimento=" + idAtendimento + ", idBarbearia=" + idBarbearia + ", idBarbeiro=" + idBarbeiro + ", idCliente=" + idCliente + ", atendimento=" + atendimento + '}';
    }
}
