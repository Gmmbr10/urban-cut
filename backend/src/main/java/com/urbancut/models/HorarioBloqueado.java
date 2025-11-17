package com.urbancut.models;

import com.urbancut.core.Model;

import java.time.LocalTime;

public class HorarioBloqueado extends Model {
    private Integer idHorarioBloqueado;
    private Integer idBarbeiro;
    private LocalTime inicio;
    private LocalTime fim;

    private HorarioBloqueado(Integer idHorarioBloqueado, Integer idBarbeiro, LocalTime inicio, LocalTime fim) {
        this.idHorarioBloqueado = idHorarioBloqueado;
        this.idBarbeiro = idBarbeiro;
        this.inicio = inicio;
        this.fim = fim;
    }

    public static class HorarioBloqueadoBuilder {
        private Integer idHorarioBloqueado = null;
        private Integer idBarbeiro = null;
        private LocalTime inicio = null;
        private LocalTime fim = null;

        public HorarioBloqueadoBuilder idHorarioBloqueado(Integer idHorarioBloqueado) {
            this.idHorarioBloqueado = idHorarioBloqueado;
            return this;
        }

        public HorarioBloqueadoBuilder idBarbeiro(Integer idBarbeiro) {
            this.idBarbeiro = idBarbeiro;
            return this;
        }

        public HorarioBloqueadoBuilder inicio(LocalTime inicio) {
            this.inicio = inicio;
            return this;
        }

        public HorarioBloqueadoBuilder fim(LocalTime fim) {
            this.fim = fim;
            return this;
        }

        public HorarioBloqueado build(){
            return new HorarioBloqueado(idHorarioBloqueado,idBarbeiro,inicio,fim);
        }
    }

    public Integer getIdHorarioBloqueado() {
        return idHorarioBloqueado;
    }

    public Integer getIdBarbeiro() {
        return idBarbeiro;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }

    @Override
    public String toString() {
        return "HorarioBloqueado{" +
                "idHorarioBloqueado=" + idHorarioBloqueado +
                ", idBarbeiro=" + idBarbeiro +
                ", inicio=" + inicio +
                ", fim=" + fim +
                '}';
    }
}
