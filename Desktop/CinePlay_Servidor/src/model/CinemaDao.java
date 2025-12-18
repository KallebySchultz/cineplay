/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.security.MessageDigest;
import java.util.Optional;
import modelDominio.Cinema;
import java.sql.*;

public class CinemaDao {

    private Connection con;

    public CinemaDao() {
        con = Conector.getConnection();
    }

    public Optional<Cinema> login(Cinema c) {
        try {
            // 1️⃣ Gera o hash da senha digitada
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(c.getSenha().getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x",0xFF & b));
            }
            String senhaCriptografada = sb.toString();

            //Consulta sql usando o hash
            String sql = "SELECT * FROM cinema WHERE cnpj = ? AND senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getCnpj());
            stmt.setString(2, senhaCriptografada);

            ResultSet rs = stmt.executeQuery();

            // se achou --> login ok
            if (rs.next()) {
                Cinema logado = new Cinema(
                        rs.getInt("idCinema"),
                        rs.getString("nomeCinema"),
                        rs.getString("endereco"),
                        rs.getString("cnpj"),
                        rs.getString("senha") // já vem criptografada
                );
                rs.close();
                stmt.close();
                con.close();
                return Optional.of(logado);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean inserir(Cinema c) {
        try {
            String sql = "INSERT INTO cinema (nomeCinema, endereco, cnpj, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNomeCinema());
            ps.setString(2, c.getEndereco());
            ps.setString(3, c.getCnpj());
            ps.setString(4, c.getSenha());
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Cinema c) {
        try {
            String sql = "UPDATE cinema SET nomeCinema=?, endereco=?, cnpj=?, senha=? WHERE idCinema=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNomeCinema());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getCnpj());
            stmt.setString(4, c.getSenha());
            stmt.setInt(5, c.getIdCinema());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
