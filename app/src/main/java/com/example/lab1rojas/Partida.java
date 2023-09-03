package com.example.lab1rojas;

public class Partida {
    private int numeroPartida;
    private String resultado;
    private long tiempoTranscurrido;

    public Partida(int numeroPartida, String resultado, long tiempoTranscurrido) {
        this.numeroPartida = numeroPartida;
        this.resultado = resultado;
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public int getNumeroPartida() {
        return numeroPartida;
    }

    public void setNumeroPartida(int numeroPartida) {
        this.numeroPartida = numeroPartida;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public long getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(long tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }
}