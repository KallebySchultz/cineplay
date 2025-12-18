/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.sql.Connection;
import java.sql.*; 

public class Conector {
    private static Connection con;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/cineplay?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String senha = "";
            con = DriverManager.getConnection(url, usuario, senha);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
