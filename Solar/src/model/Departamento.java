package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Departamento{

   public void listarSetores(Connection conn){
      ArrayList<Setor> lista = buscarSetores(conn);
      for(Setor setor:lista){
         System.out.println(setor);
      }
   }
   
   public ArrayList<Setor> buscarSetores(Connection conn){
      String sqlSelect = "SELECT id, nome, fone FROM setor";
      ArrayList<Setor> lista = new ArrayList<>();
      
      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);
         ResultSet rs = stm.executeQuery();){
         //veja que desta vez foi possivel usar o mesmo try
         while(rs.next()){
            Setor setor = new Setor();
            setor.setIdSetor(rs.getInt("id"));
            setor.setNome(rs.getString("nome"));
            setor.setFone(rs.getString("fone"));
            lista.add(setor);
         }
      } 
      catch(Exception e){
         e.printStackTrace();
      }
      return lista;
   }  
}