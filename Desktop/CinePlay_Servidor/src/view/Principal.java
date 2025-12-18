/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.TrataClienteController;
import factory.Conector;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Usuário
 */
public class Principal {
    public static void main(String[] args) {
        try {
            if (Conector.getConnection() != null) {
                System.out.println("Conectado ao banco cineplay!");
            }
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor CinePlay inicializado. Aguardando conexões...");
            int idUnico = 0;
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Novo cliente: " + cliente);
                TrataClienteController tcc = new TrataClienteController(cliente, ++idUnico);
                tcc.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
