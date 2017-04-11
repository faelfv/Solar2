package model;
import java.sql.*;
import java.util.ArrayList;

public class Categoria {
   private int idCategoria;
   private String descr;

   public Categoria() {
   }

   public Categoria(int idCategoria) {
      this.idCategoria = idCategoria;
   }

   public Categoria(int idCategoria, String descr) {
      this.idCategoria = idCategoria;
      this.descr = descr;
   }

   public int getIdCategoria() {
      return idCategoria;
   }

   public void setIdCategoria(int idCategoria) {
      this.idCategoria = idCategoria;
   }

   public String getDescr() {
      return descr;
   }

   public void setDescr(String descr) {
      this.descr = descr;
   }

   public ArrayList<Categoria> carregar(Connection conn) {
   
      ArrayList<Categoria> lista = new ArrayList<Categoria>();
      
      String sqlSelect = "SELECT id, nome FROM setor";
   
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         ResultSet rs = stm.executeQuery();
         while (rs.next()) {
            Categoria c = new Categoria();
            c.setIdCategoria(rs.getInt("id"));
            c.setDescr(rs.getString("nome"));
            lista.add(c);
         }
      } 
      catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      } 
      return lista;
   }
}