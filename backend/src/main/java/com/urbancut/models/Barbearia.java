package com.urbancut.models;

import com.urbancut.core.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Barbearia extends Model {
    private Integer idBarbearia;
    private Integer idDono;
    private String nome;
    private String urlMaps;
    private LocalTime tempoMedioAtendimento;
    private List<Barbeiro> barbeiros = new ArrayList<>();

    private Barbearia(Integer idBarbearia, Integer idDono, String nome, String urlMaps, LocalTime tempoMedioAtendimento) {
        this.idBarbearia = idBarbearia;
        this.idDono = idDono;
        this.nome = nome;
        this.urlMaps = urlMaps;
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    public static class BarbeariaBuilder{
        private Integer idBarbearia;
        private Integer idDono;
        private String nome;
        private String urlMaps;
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

        public BarbeariaBuilder urlMaps(String urlMaps) {
            this.urlMaps = urlMaps;
            return this;
        }

        public BarbeariaBuilder tempoMedioAtendimento(Integer tempoMedioAtendimento) {
            int horas = (int) (tempoMedioAtendimento / 60);
            double restoMinutos = tempoMedioAtendimento % 60;
            int minutos = (int) restoMinutos;
            int segundos = (int) ((restoMinutos - minutos) * 60);

            this.tempoMedioAtendimento = LocalTime.of(horas,minutos,segundos);
            return this;
        }

        public BarbeariaBuilder tempoMedioAtendimento(LocalTime tempoMedioAtendimento) {
            this.tempoMedioAtendimento = tempoMedioAtendimento;
            return this;
        }

        public Barbearia build(){
            return new Barbearia(idBarbearia,idDono,nome,urlMaps,tempoMedioAtendimento);
        }
    }

    public Integer getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(Integer idBarbearia) {
        this.idBarbearia = idBarbearia;
    }

    public Integer getIdDono() {
        return idDono;
    }

    public void setIdDono(Integer idDono) {
        this.idDono = idDono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlMaps() {
        return urlMaps;
    }

    public void setUrlMaps(String urlMaps) {
        this.urlMaps = urlMaps;
    }

    public LocalTime getTempoMedioAtendimento() {
        return tempoMedioAtendimento;
    }

    public void setTempoMedioAtendimento(LocalTime tempoMedioAtendimento) {
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    @Override
    public String toString() {
        return "Barbearia{" +
                "idBarbearia=" + idBarbearia +
                ", idDono=" + idDono +
                ", nome='" + nome + '\'' +
                ", urlMaps='" + urlMaps + '\'' +
                ", tempoMedioAtendimento=" + tempoMedioAtendimento +
                '}';
    }
}
