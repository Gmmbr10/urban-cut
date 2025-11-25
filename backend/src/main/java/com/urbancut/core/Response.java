package com.urbancut.core;

public class Response<T> {
    private Integer statusCode = null;
    private String mensagem = "";
    private T dado = null;

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response(int statusCode, String mensagem) {
        this.statusCode = statusCode;
        this.mensagem = mensagem;
    }

    public Response(int statusCode, T dado) {
        this.statusCode = statusCode;
        this.dado = dado;
    }

    public Response(int statusCode, String mensagem, T dado) {
        this.statusCode = statusCode;
        this.mensagem = mensagem;
        this.dado = dado;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return "Response{" + "statusCode=" + statusCode + ", mensagem='" + mensagem + '\'' + ", dado=" + dado + '}';
    }
}
