package view;

import controller.TrataClienteController;
import factory.Conector;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

/**
 *
 * @author Usuário
 */
public class Principal {
    private static final int SERVER_PORT = 12345;
    
    public static void main(String[] args) {
        ServerSocket servidor = null;
        try {
            // Test database connection first
            System.out.println("=== Iniciando Servidor CinePlay ===");
            System.out.println("Verificando conexão com banco de dados...");
            
            Connection testCon = Conector.getConnection();
            if (testCon != null && !testCon.isClosed()) {
                System.out.println("✓ Conectado ao banco cineplay com sucesso!");
            } else {
                System.err.println("✗ ERRO: Não foi possível conectar ao banco de dados!");
                System.err.println("  Verifique se o MySQL está rodando e se o banco 'cineplay' existe.");
                System.exit(1);
            }
            
            // Start server socket
            System.out.println("Iniciando servidor na porta " + SERVER_PORT + "...");
            servidor = new ServerSocket(SERVER_PORT);
            System.out.println("✓ Servidor CinePlay inicializado com sucesso!");
            System.out.println("Aguardando conexões de clientes...");
            System.out.println("=====================================");
            
            int idUnico = 0;
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("✓ Novo cliente conectado: " + cliente.getInetAddress().getHostAddress());
                TrataClienteController tcc = new TrataClienteController(cliente, ++idUnico);
                tcc.start();
            }
        } catch (java.net.BindException e) {
            System.err.println("✗ ERRO: A porta " + SERVER_PORT + " já está em uso!");
            System.err.println("  Verifique se outro servidor já está rodando.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ ERRO ao iniciar servidor:");
            e.printStackTrace();
        } finally {
            if (servidor != null && !servidor.isClosed()) {
                try {
                    servidor.close();
                    System.out.println("Servidor encerrado.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
