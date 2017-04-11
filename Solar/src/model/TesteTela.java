package model;
import java.sql.*;

import dao.ConnectionFactory;

public class TesteTela{
   public static void main(String[] args){
   // obtem conexao com o banco, que sera usada todo o tempo
      
      try{
    	  Connection conn = ConnectionFactory.obtemConexao();
         new Mural(conn);
      } catch (SQLException e){
         e.printStackTrace();
      }
   }
}