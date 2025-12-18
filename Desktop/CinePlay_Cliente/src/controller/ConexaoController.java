/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelDominio.Cinema;
import modelDominio.Filme;

/**
 *
 * @author Usuário
 */
public class ConexaoController {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Cinema cinemaLogado;

    public Cinema getCinemaLogado() {
        return cinemaLogado;
    }

    public void setCinemaLogado(Cinema c) {
        this.cinemaLogado = c;
    }
 public ConexaoController(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }
    public Cinema cinemaLogin(Cinema c) {
        try {
            out.writeObject("CinemaLogin");
            if (in.readObject().equals("ok")) {
                out.writeObject(c);
                return (Cinema) in.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean cinemaInserir(Cinema c) {
        try {
            out.writeObject("CinemaInserir");
            in.readObject(); // lendo o OK
            out.writeObject(c);
            return (boolean) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public boolean cinemaEditar(Cinema c) {
        try {
            out.writeObject("cinemaEditar");
            in.readObject(); // lendo o OK
            out.writeObject(c);
            boolean res = (boolean) in.readObject();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Filme> filmeLista(int idCinema) {
        try {
            out.writeObject("FilmeLista");
            out.writeObject(idCinema);
            return (ArrayList<Filme>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean filmeInserir(Filme f) {
        try {
            out.writeObject("FilmeInserir");
            in.readObject(); // lendo o OK
            out.writeObject(f);
            boolean res = (boolean) in.readObject();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean FilmeEditar(Filme f) {
        try {
            out.writeObject("filmeEditar");
            in.readObject(); // lendo o OK
            out.writeObject(f);
            boolean res = (boolean) in.readObject();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   
}
