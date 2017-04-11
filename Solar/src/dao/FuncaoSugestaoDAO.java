package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FuncaoSugestao;
import model.Sugestao;

public class FuncaoSugestaoDAO {
	 public int incluir(FuncaoSugestao fSugestao){
	      String sqlInsert = "INSERT INTO sugestao(titulo, id_setor, descricao, aprovado, colaborador_login, setor) VALUES (?, ?, ?, ?, ?, ?)";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
	         stm.setString(1, fSugestao.getTitulo());
	         stm.setInt(2, fSugestao.getSetorid());
	         stm.setString(3, fSugestao.getDescricao());
	         stm.setString(4, "N");
	         stm.setString(5, fSugestao.getColab());
	         stm.setString(6, fSugestao.getSetor());
	         stm.execute();
	      } catch (SQLException e) {
				e.printStackTrace();
			}
		
		return fSugestao.getId();
	}
	   
	   public void aprovar(FuncaoSugestao fSugestao) {
	      String sqlUpdate = "UPDATE sugestao SET aprovado = ? WHERE id = ?";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setString(1, "S");
	         stm.setInt(2, fSugestao.getId());
	         stm.executeUpdate();
	    	} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   public void negar(FuncaoSugestao fSugestao) {
	      String sqlUpdate = "UPDATE sugestao SET aprovado = ? WHERE id = ?";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setString(1, "N");
	         stm.setInt(2, fSugestao.getId());
	         stm.executeUpdate();
	      } catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   public void excluir(int id) {
	      String sqlDelete = "DELETE FROM sugestao WHERE id = ?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
	         stm.setInt(1, id);
	         stm.execute();
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  }
	   }
	   
	   public FuncaoSugestao carregar(int id) {
		   FuncaoSugestao sugestao = new FuncaoSugestao();
		   Sugestao sugs = new Sugestao();
	      String sqlSelect = "SELECT id, titulo, setor, avaliador_login, colaborador_login, aprovado, descricao FROM sugestao WHERE id_setor = ? AND aprovado = 'S'";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	         stm.setInt(1, sugestao.getSetorid());
	        try (ResultSet rs = stm.executeQuery();) {
	            if (rs.next()) {
	            	sugestao.setId(rs.getInt(1));
	            	sugestao.setTitulo(rs.getString(2));
	            	sugestao.setSetor(rs.getString(3));
	            	sugestao.setAval(rs.getString(4));
	            	sugestao.setColab(rs.getString(5));
	            	sugs.setStatus(rs.getString(6));
	            	sugestao.setDescricao(rs.getString(7));
	            }
	         
	        }catch (SQLException e) {
            	e.printStackTrace();
            	}
            	} catch (SQLException e1) {
            	System.out.print(e1.getStackTrace());
            	}
            	return sugestao;
            	
} 
}
