package view;

import controller.ConexaoController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class Principal {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 12345;
    
    public static ConexaoController ccont;

    public static void main(String[] args) {
        try {
            System.out.println("Conectando ao servidor CinePlay...");
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // com o OUT eu posso enviar coisas para o SERVIDOR
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // com o IN eu posso receber coisas do SERVIDOR
            ccont = new ConexaoController(out, in);
            System.out.println("✓ Conectado ao servidor com sucesso!");
            
            TelaEntrada entrada = new TelaEntrada();
            entrada.setVisible(true);
        } catch (java.net.ConnectException e) {
            System.err.println("✗ ERRO: Não foi possível conectar ao servidor!");
            System.err.println("  Verifique se o servidor está rodando em " + SERVER_HOST + ":" + SERVER_PORT);
            String errorMessage = String.format(
                "Não foi possível conectar ao servidor!\n\n" +
                "Verifique se:\n" +
                "1. O servidor está rodando em %s:%d\n" +
                "2. A porta %d está disponível\n" +
                "3. Não há firewall bloqueando a conexão",
                SERVER_HOST, SERVER_PORT, SERVER_PORT);
            JOptionPane.showMessageDialog(null, 
                errorMessage,
                "Erro de Conexão",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("✗ ERRO inesperado:");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Erro inesperado ao conectar:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
