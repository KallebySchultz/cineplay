/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author Usuário
 */
// Essa classe implementa Serializable para que seus objetos possam ser transmitidos 
// através de Sockets
public class Cinema implements Serializable {

    // O serialVersionUID é utilizado para controlar a versão da classe
    // e garantir que o servidor e cliente tenham a mesma versão.
    private static final long serialVersionUID = 123456789L;

    private int idCinema;
    private String nomeCinema;
    private String endereco;
    private String cnpj;
    private String senha;
    // Construtores, getters, setters, toString

    public Cinema(int idCinema, String nomeCinema, String endereco, String cnpj, String senha) {
        this.idCinema = idCinema;
        this.nomeCinema = nomeCinema;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.senha = senha;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getNomeCinema() {
        return nomeCinema;
    }

    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    // o toString é essencial para podermos realizar debug de código através da
    // utilização de prints do objeto

    @Override
    public String toString() {
        return "Cinema{" + "idCinema=" + idCinema + ", nomeCinema=" + nomeCinema + ", endereco=" + endereco + ", cnpj=" + cnpj + ", senha=" + senha + '}';
    }
    //Construtor de login

    public Cinema(String cnpj, String senha) {
        this.cnpj = cnpj;
        this.senha = senha;
    }
    // construtor sem o código é utilizado para inserir (INSERTS)
    public Cinema(String nomeCinema, String endereco, String cnpj, String senha) {
        this.nomeCinema = nomeCinema;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.senha = senha;
    }

    // construtor somente com o código é utilizado para apagar (DELETES)
    public Cinema(int idCinema) {
        this.idCinema = idCinema;
    }

}
