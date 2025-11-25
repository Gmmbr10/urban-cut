package com.urbancut.models;

import com.urbancut.core.Model;

import java.time.LocalTime;

public class DiaFuncionamento extends Model {
    private Integer idDiaFuncionamento;
    private Integer idBarbearia;
    private String diaSemana;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;

    private DiaFuncionamento(Integer idDiaFuncionamento, Integer idBarbearia, String diaSemana, LocalTime horaAbertura, LocalTime horaFechamento) {
        this.idDiaFuncionamento = idDiaFuncionamento;
        this.idBarbearia = idBarbearia;
        this.diaSemana = diaSemana;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }

    public static final class DiasFuncionamentoBuilder {
        private Integer idDiaFuncionamento;
        private Integer idBarbearia;
        private String diaSemana;
        private LocalTime horaAbertura;
        private LocalTime horaFechamento;

        public DiasFuncionamentoBuilder idDiaFuncionamento(Integer idDiaFuncionamento) {
            this.idDiaFuncionamento = idDiaFuncionamento;
            return this;
        }

        public DiasFuncionamentoBuilder idBarbearia(Integer idBarbearia) {
            this.idBarbearia = idBarbearia;
            return this;
        }

        public DiasFuncionamentoBuilder diaSemana(String diaSemana) {
            this.diaSemana = diaSemana;
            return this;
        }

        public DiasFuncionamentoBuilder horaAbertura(LocalTime horaAbertura) {
            this.horaAbertura = horaAbertura;
            return this;
        }

        public DiasFuncionamentoBuilder horaFechamento(LocalTime horaFechamento) {
            this.horaFechamento = horaFechamento;
            return this;
        }

        public DiaFuncionamento build() {
            return new DiaFuncionamento(idDiaFuncionamento, idBarbearia, diaSemana, horaAbertura, horaFechamento);
        }
    }

    public Integer getIdDiaFuncionamento() {
        return idDiaFuncionamento;
    }

    public Integer getIdBarbearia() {
        return idBarbearia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraAbertura() {
        return horaAbertura;
    }

    public LocalTime getHoraFechamento() {
        return horaFechamento;
    }

    @Override
    public String toString() {
        return "DiasFuncionamento{" + "idDiaFuncionamento=" + idDiaFuncionamento + ", idBarbearia=" + idBarbearia + ", diaSemana='" + diaSemana + '\'' + ", horaAbertura=" + horaAbertura + ", horaFechamento=" + horaFechamento + '}';
    }
}
