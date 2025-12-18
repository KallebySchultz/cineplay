package factory;

import java.sql.Connection;
import java.sql.*; 

public class Conector {
    private static Connection con;

    public static Connection getConnection() {
        try {
            // Close existing connection if it's closed or invalid
            if (con != null && con.isClosed()) {
                con = null;
            }
            
            // Create new connection if needed
            if (con == null) {
                String url = "jdbc:mysql://localhost:3306/cineplay?useSSL=false&serverTimezone=UTC";
                String usuario = "root";
                String senha = "";
                con = DriverManager.getConnection(url, usuario, senha);
                
                // Test the connection
                if (con != null && !con.isClosed()) {
                    System.out.println("Conexão com banco de dados estabelecida com sucesso!");
                }
            }
            return con;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            System.err.println("- Verifique se o MySQL está rodando");
            System.err.println("- Verifique se o banco 'cineplay' existe");
            System.err.println("- Verifique as credenciais (usuário: root, senha: vazia)");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao conectar ao banco de dados:");
            e.printStackTrace();
            return null;
        }
    }
    
    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexão com banco de dados fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com banco de dados:");
            e.printStackTrace();
        }
    }
}
