package com.urbancut.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResumoDia {
    private LocalDate data;
    private LocalTime horario;
    private Cliente cliente;

    private ResumoDia(LocalDate data, LocalTime horario, Cliente cliente) {
        this.data = data;
        this.horario = horario;
        this.cliente = cliente;
    }

    public static class ResumoDiaBuilder {
        private LocalDate data;
        private LocalTime horario;
        private Cliente cliente;

        public ResumoDiaBuilder data(LocalDate data) {
            this.data = data;
            return this;
        }

        public ResumoDiaBuilder horario(LocalTime horario) {
            this.horario = horario;
            return this;
        }

        public ResumoDiaBuilder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public ResumoDia build() {
            return new ResumoDia(data, horario, cliente);
        }
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "ResumoDia{" + "data=" + data + ", horario=" + horario + ", cliente=" + cliente + '}';
    }
}
