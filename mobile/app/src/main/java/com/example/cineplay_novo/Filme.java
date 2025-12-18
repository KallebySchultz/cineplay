/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cineplay_novo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Usuário
 */
// Essa classe implementa Serializable para que seus objetos possam ser transmitidos 
// através de Sockets
public class Filme implements Serializable {
    // O serialVersionUID é utilizado para controlar a versão da classe
    // e garantir que o servidor e cliente tenham a mesma versão.
    private static final long serialVersionUID = 123456789L;
    
    
    private int idFilme, idCinema;
    private String titulo, descricao, classificacao, numeroPoltronas;
    private float preco;
    private Date dataInicio, dataTermino;
    // Construtores, getters, setters, toString

    public Filme(int idFilme, int idCinema, String numeroPoltronas, String titulo, String descricao, String classificacao, float preco, Date dataInicio, Date dataTermino) {
        this.idFilme = idFilme;
        this.idCinema = idCinema;
        this.numeroPoltronas = numeroPoltronas;
        this.titulo = titulo;
        this.descricao = descricao;
        this.classificacao = classificacao;
        this.preco = preco;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Filme(int idCinema, String titulo, String descricao, String classificacao, String numeroPoltronas, float preco, Date dataInicio, Date dataTermino) {
        this.idCinema = idCinema;
        this.titulo = titulo;
        this.descricao = descricao;
        this.classificacao = classificacao;
        this.numeroPoltronas = numeroPoltronas;
        this.preco = preco;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

  

    

    
    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getNumeroPoltronas() {
        return numeroPoltronas;
    }

    public void setNumeroPoltronas(String numeroPoltronas) {
        this.numeroPoltronas = numeroPoltronas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void Date(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    @Override
    public String toString() {
        return "Filme{" + "idFilme=" + idFilme + ", idCinema=" + idCinema + ", numeroPoltronas=" + numeroPoltronas + ", titulo=" + titulo + ", descricao=" + descricao + ", classificacao=" + classificacao + ", preco=" + preco + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + '}';
    }

    
    
}
