/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ConexaoController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Usuário
 */
public class Principal {

    public static ConexaoController ccont;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // com o OUT eu posso enviar coisas para o SERVIDOR
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // com o IN eu posso receber coisas do SERVIDOR
            ccont = new ConexaoController(out, in);
           TelaEntrada entrada = new TelaEntrada();
           entrada.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
