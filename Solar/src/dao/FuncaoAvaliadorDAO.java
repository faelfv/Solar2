package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FuncaoAvaliador;


public class FuncaoAvaliadorDAO {
	 
	   
	   public String incluir(FuncaoAvaliador avaliador){
	      String sqlInsert = "INSERT INTO avaliador(login, setor_id, nome, senha) VALUES (?, ?, ?, ?)";
		      try (Connection conn = ConnectionFactory.obtemConexao();
		    		  PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
		    	 stm.setString(1, avaliador.getLogin());
		    	 stm.setString(2, avaliador.getNome());
		         stm.setInt(3, avaliador.getSetor());
		         stm.setString(4, avaliador.getSenha());
		      
		         stm.execute();
		       
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return avaliador.getLogin();
			}

	   
	   public void atualizar(FuncaoAvaliador avaliador) {
		      String sqlUpdate = "UPDATE avaliador SET senha = ?, nome = ?, setor_id = ? WHERE login = ?";
		   
		      try (Connection conn = ConnectionFactory.obtemConexao();
		    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
		         stm.setString(1, avaliador.getSenha());
		         stm.setString(2, avaliador.getNome());
		         stm.setInt(3, avaliador.getSetor());
		         stm.setString(4, avaliador.getLogin());
		         stm.executeUpdate();
		  	} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 	
	   public void excluir(String login) {
		      String sqlDelete = "DELETE FROM avaliador WHERE login = ?";
		      try (Connection conn = ConnectionFactory.obtemConexao();
		    		  PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
		         stm.setString(1, login);
		         stm.execute();
		      } catch (Exception e) {
		    	  e.printStackTrace();
		    	  }
	   }

	   public FuncaoAvaliador carregar(String login) {
		   FuncaoAvaliador avaliador = new FuncaoAvaliador();
	      String sqlSelect = "SELECT nome, senha FROM avaliador WHERE login = ?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	         stm.setString(1, login);
	         try (ResultSet rs = stm.executeQuery();) {
	            if (rs.next()) {
	            	avaliador.setNome(rs.getString("nome"));
	            	avaliador.setSenha(rs.getString("senha"));
	            }
	         }catch (SQLException e) {
	            	e.printStackTrace();
	            	}
	            	} catch (SQLException e1) {
	            	System.out.print(e1.getStackTrace());
	            	}
	            	return avaliador;
	            	
	} 
	  
}
