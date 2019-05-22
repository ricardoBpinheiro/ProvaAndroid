package com.example.provaandroid;

public class Paciente {

    private String nome;
    private int idade;
    private double leucocitos;
    private double glicemia;
    private double asttgo;
    private double ldh;
    private boolean litiase;
    private int pontos;
    private double mortalidade;

    public Paciente(String nome, int idade, double leucocitos, double glicemia, double asttgo, double ldh, boolean litiase, int pontos, double mortalidade) {
        this.nome = nome;
        this.idade = idade;
        this.leucocitos = leucocitos;
        this.glicemia = glicemia;
        this.asttgo = asttgo;
        this.ldh = ldh;
        this.litiase = litiase;
        this.pontos = pontos;
        this.mortalidade = mortalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getLeucocitos() {
        return leucocitos;
    }

    public void setLeucocitos(double leucocitos) {
        this.leucocitos = leucocitos;
    }

    public double getGlicemia() {
        return glicemia;
    }

    public void setGlicemia(double glicemia) {
        this.glicemia = glicemia;
    }

    public double getAsttgo() {
        return asttgo;
    }

    public void setAsttgo(double asttgo) {
        this.asttgo = asttgo;
    }

    public double getLdh() {
        return ldh;
    }

    public void setLdh(double ldh) {
        this.ldh = ldh;
    }

    public boolean isLitiase() {
        return litiase;
    }

    public void setLitiase(boolean litiase) {
        this.litiase = litiase;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public double getMortalidade() {
        return mortalidade;
    }

    public void setMortalidade(double mortalidade) {
        this.mortalidade = mortalidade;
    }
}
