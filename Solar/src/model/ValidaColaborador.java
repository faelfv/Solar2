package model;
import java.sql.*;
import javax.swing.*;

public class ValidaColaborador{
   //verifica o login no banco
   public boolean consultar(Connection conn, String login, String senha) {
      boolean autenticado = false;
      String sql = "SELECT login, senha FROM colaborador WHERE login=? and senha=?";
            
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