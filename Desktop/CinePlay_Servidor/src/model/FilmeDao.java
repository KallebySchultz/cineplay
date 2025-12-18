package model;

import java.util.ArrayList;
import modelDominio.Filme;
import java.sql.*;
import factory.Conector;

public class FilmeDao {

    private Connection con;

    public FilmeDao() {
        con = Conector.getConnection();
    }

    public ArrayList<Filme> getLista() {
        ArrayList<Filme> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM filme";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Filme f = new Filme(res.getInt("idFilme"), res.getInt("idCinema"),
                        res.getString("titulo"),
                        res.getString("descricao"),
                        res.getString("classificacao"),
                        res.getString("numeroPoltronas"),
                        res.getFloat("preco"),
                        res.getString("dataInicio"),
                        res.getString("dataTermino"));

                System.out.println(f);
                lista.add(f);
            }
            // fechando as conexões
            res.close();
            stmt.close();
            con.close();
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
        //Colocar um Join
            //para chamar
            //idcinema 
    
     public boolean inserir(Filme f){
        try {
               //aqui mais um string sql
               
            String sql = "INSERT INTO filme (idCinema, titulo, descricao, classificacao, numeroPoltronas, preco, dataInicio, dataTermino) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getIdCinema());
            stmt.setString(2, f.getTitulo());
            stmt.setString(3, f.getDescricao());
            stmt.setString(4, f.getClassificacao());
            stmt.setString(5, f.getNumeroPoltronas());
            stmt.setFloat(6, f.getPreco());
            stmt.setString(7, f.getDataInicio());
            stmt.setString(8, f.getDataTermino());
            stmt.execute();
            
            stmt.close();
            con.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Filme f) {
        try {
            String sql = "UPDATE filme SET titulo=?, descricao=?, classificacao=?, numeroPoltronas=?, preco=?, dataInicio=?, dataTermino=? WHERE idFilme=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getTitulo());
            stmt.setString(2, f.getDescricao());
            stmt.setString(3, f.getClassificacao());
            stmt.setString(4, f.getNumeroPoltronas());
            stmt.setFloat(5, f.getPreco());
            stmt.setString(6, f.getDataInicio());
            stmt.setString(7,f.getDataTermino());
            stmt.setInt(8, f.getIdFilme());
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
