/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.CinemaDao;
import model.FilmeDao;
import modelDominio.Cinema;
import modelDominio.Filme;

/**
 *
 * @author Usuário
 */
public class TrataClienteController extends Thread {

    private final Socket cliente;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final int idUnico;

    public TrataClienteController(Socket cliente, int idUnico) {
        this.cliente = cliente;
        this.idUnico = idUnico;
        try {
            this.in = new ObjectInputStream(cliente.getInputStream());
            this.out = new ObjectOutputStream(cliente.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String comando;
        System.out.println("Esperando comandos do cliente " + idUnico);
        try {
            // esperando comando do cliente
            comando = (String) in.readObject();
            // enquanto o comando for diferente de "FIM" o servidor
            // fica dentro do looping
            while (!comando.equalsIgnoreCase("fim")) {
                System.out.println("Cliente " + idUnico + " enviou: " + comando);

                if (comando.equalsIgnoreCase("CinemaLogin")) {
                    // enviando OK para cliente
                    out.writeObject("ok");
                    // lendo o cinema que veio do Cliente
                    Cinema c = (Cinema) in.readObject();
                    System.out.println(c);
                    // Criando o UsuarioDao para chamar o método de login
                    CinemaDao cDao = new CinemaDao();
                    Cinema logado = cDao.login(c).orElse(null);
                    out.writeObject(logado);

                } else if (comando.equalsIgnoreCase("CinemaInserir")) {
                    out.writeObject("ok");
                    Cinema c = (Cinema) in.readObject();
                    CinemaDao cDao = new CinemaDao();
                    boolean res = cDao.inserir(c);
                    out.writeObject(res);

                } else if (comando.equalsIgnoreCase("CinemaEditar")) {
                    out.writeObject("ok");
                    Cinema c = (Cinema) in.readObject();
                    CinemaDao cDao = new CinemaDao();
                    boolean res = cDao.editar(c);
                    out.writeObject(res);

                } else if (comando.equalsIgnoreCase("FilmeLista")) {
                    int idCinema = (Integer) in.readObject();
                    FilmeDao fDao = new FilmeDao();
                    ArrayList<Filme> lista = fDao.getLista();
                    out.writeObject(lista);

                } else if (comando.equalsIgnoreCase("FilmeInserir")) {
                    out.writeObject("ok");
                    Filme f = (Filme) in.readObject();
                    FilmeDao fDao = new FilmeDao();
                    boolean res = fDao.inserir(f);
                    out.writeObject(res);

                } else if (comando.equalsIgnoreCase("FilmeEditar")) {
                    out.writeObject("ok");
                    Filme f = (Filme) in.readObject();
                    FilmeDao fDao = new FilmeDao();
                    boolean res = fDao.editar(f);
                    out.writeObject(res);
                } 

                comando = (String) in.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Cliente " + idUnico + " fechou a conexão");
            in.close();
            out.close();
            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
