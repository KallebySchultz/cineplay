/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cineplay_novo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 *
 * @author Usuário
 */
public class ConexaoController {

    private ObjectOutputStream out;
    private ObjectInputStream in;

 public ConexaoController(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
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



   
}
