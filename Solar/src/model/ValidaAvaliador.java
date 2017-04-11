package model;
import java.sql.*;
import javax.swing.*;

public class ValidaAvaliador{
   
   public boolean consultar(Connection conn, String login, String senha) {
      boolean autenticado = false;
      String sql = "SELECT login, senha FROM avaliador WHERE login=? and senha=?";
            
      try (PreparedStatement ps = conn.prepareStatement(sql)){ 
         ps.setString(1, login);
         ps.setString(2, senha);
         ResultSet rs;
         rs = ps.executeQuery();
         if (rs.next()) {
            autenticado = true;
         } 
         else {
            ps.close();
            return autenticado;
         }
      } 
      catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
      }
      return autenticado;
   }
}