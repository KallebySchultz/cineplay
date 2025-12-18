/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view_util;

import java.util.ArrayList;
import javax.swing.JComboBox;
import modelDominio.Filme;
import view.Principal;


/**
 *
 * @author Usuário
 */
public class ProgramaComboBox {
   public static ArrayList<Filme> carregarFilmesComboBox(JComboBox<String> combo) {
        int idCinema = Principal.ccont.getCinemaLogado().getIdCinema();
        ArrayList<Filme> listaFilmes = Principal.ccont.filmeLista(idCinema);

        combo.removeAllItems();
        for (Filme f : listaFilmes) {
            combo.addItem(f.getTitulo());
        }
        return listaFilmes; // retorna a lista para quem chamou
    }
}
    

