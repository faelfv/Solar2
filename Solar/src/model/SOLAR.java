package model;
import java.sql.*;


import dao.ConnectionFactory;

public class SOLAR{
   public static void main(String[] args){
      try{
         //conecta e abre tela
         Connection conn = ConnectionFactory.obtemConexao();
         new TelaLogin(conn);
      } 
      catch (SQLException e){
         e.printStackTrace();
      }
   }
}