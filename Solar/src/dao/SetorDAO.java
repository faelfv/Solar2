package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Sugestao;
import model.Setor;
import service.SugestaoService;

public class SetorDAO {
	
	public int incluir(Setor setor) {
		SugestaoService sSer = new SugestaoService();
		ArrayList<Sugestao> sugestoes = new ArrayList<>();
	      String sqlInsert = "INSERT INTO setor(id, nome, fone) VALUES (?, ?, ?)";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
	         stm.setInt(1, setor.getIdSetor());
	         stm.setString(2, setor.getNome());
	         stm.setString(3, setor.getFone());
	         stm.execute();
	      	//incluir os sugestoes
	         for(Sugestao sugestao:sugestoes){
	            sSer.incluir(sugestao);
	         }
	      } catch (SQLException e) {
				e.printStackTrace();
			}
		
		return setor.getIdSetor();
	}

	public void atualizar(Setor setor) {
	      String sqlUpdate = "UPDATE setor SET nome=?, fone=? WHERE id = ?";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setString(1, setor.getFone());
	         stm.setString(2, setor.getNome());
	         stm.setInt(3, setor.getIdSetor());
	      
	         stm.execute();
	  	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	   @SuppressWarnings("unused")
	public void excluir(int idSetor) {
		   SugestaoService sSer = new SugestaoService();
		   ArrayList<Sugestao> sugestoes = new ArrayList<>();
	      String sqlDelete = "DELETE FROM setor WHERE id = ?";
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
	      	//excluir primeiro os sugestoes
	         for(Sugestao sugestao:sugestoes){
	            sSer.excluir(idSetor);
	         }
			         stm.setInt(1, idSetor);
			         stm.execute();
			      } catch (Exception e) {
			    	  e.printStackTrace();
			    	  }
		   }

	   

	   public Setor carregar(int idSetor) {
		   new ArrayList<>();
		   Setor setor = new Setor();
	      String sqlSelect = "SELECT nome, fone FROM setor WHERE id = ?";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	         stm.setInt(1, setor.getIdSetor());
	         try (ResultSet rs = stm.executeQuery();) {
	         
	            if (rs.next()) {
	            	setor.setNome(rs.getString("nome"));
	            	setor.setFone(rs.getString("fone"));
	            	carregarSugestoes(setor);
	            } 
	         }catch (SQLException e) {
	            	e.printStackTrace();
	            	}
	            	} catch (SQLException e1) {
	            	System.out.print(e1.getStackTrace());
	            	}
	            	return setor;
	            	
	} 
		
	   public ArrayList<Sugestao> carregarSugestoes(Setor setor) {
	      String sqlSelect = "SELECT id, titulo, setor, avaliador_login, colaborador_login, aprovado, descricao FROM sugestao WHERE id_setor = ?";
	      ArrayList<Sugestao> lista = new ArrayList<>();
	   	
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	         stm.setInt(1, setor.getIdSetor());
	         try (ResultSet rs = stm.executeQuery();) {
	         	
	            while (rs.next()) {
	               Sugestao p = new Sugestao();
	               p.setId(rs.getInt("id"));
	               p.setTitulo(rs.getString("titulo"));
	               p.setSetor(rs.getString("setor"));
	               p.setAval(rs.getString("avaliador_login"));
	               p.setColab(rs.getString("colaborador_login"));
	               p.setStatus(rs.getString("aprovado"));
	               p.setDescricao(rs.getString("descricao"));
	               lista.add(p);
	            }
	         } 
	         catch (Exception e) {
	            e.printStackTrace();
	         }
	      } 
	      catch (SQLException e1) {
	         System.out.print(e1.getStackTrace());
	      }
	      return lista;
	   }

}
