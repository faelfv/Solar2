package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Sugestao;
import model.Setor;

public class SugestaoDAO {
	 public int incluir(Sugestao sugestao) {
		  Setor setor = new Setor();
	      String sqlInsert = "INSERT INTO sugestao(id, titulo, setor, colaborador_login, avaliador_login, id_setor) VALUES (?,?,?,?,?,?)";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
	         stm.setInt(1, sugestao.getId());
	         stm.setString(2, sugestao.getTitulo());
	         stm.setString(3, sugestao.getSetor());
	         stm.setString(4, sugestao.getColab());
	         stm.setString(5, sugestao.getAval());
	         stm.setInt(6, setor.getIdSetor());
	         stm.execute();
	      } catch (SQLException e) {
				e.printStackTrace();
			}
		
		return sugestao.getId();
	}

	 public void atualizar(Sugestao sugestao) {
	      String sqlUpdate = "UPDATE sugestao SET titulo=?, setor=?, colaborador_login=?, avaliador_login=? WHERE id=?";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setString(1, sugestao.getTitulo());
	         stm.setString(2, sugestao.getSetor());
	         stm.setString(3, sugestao.getColab());
	         stm.setString(4, sugestao.getAval());
	         
	         stm.execute();
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
	   
	   public Sugestao carregar(int id) {
		   Sugestao sugestao = new Sugestao();
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
	            	sugestao.setStatus(rs.getString(6));
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

	   
	   
	   public ArrayList<Sugestao> carregarColaborador(Sugestao sugestao) {
	      String sqlSelect = "SELECT id, titulo, setor, avaliador_login, aprovado, descricao FROM sugestao WHERE colaborador_login = ?";
	      ArrayList<Sugestao> lista = new ArrayList<>();
	   	
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	         stm.setString(1, sugestao.getColab());
	         try (ResultSet rs = stm.executeQuery();) {
	            while (rs.next()) {
	               Sugestao p = new Sugestao();
	               p.setId(rs.getInt("id"));
	               p.setTitulo(rs.getString("titulo"));
	               p.setSetor(rs.getString("setor"));
	               p.setAval(rs.getString("avaliador_login"));
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
	   
	   public ArrayList<Sugestao> carregarAvaliador(Sugestao sugestao) {
	      String sqlSelect = "SELECT id, titulo, colaborador_login, aprovado, descricao FROM sugestao WHERE avaliador_login = ?";
	      ArrayList<Sugestao> lista = new ArrayList<>();
	   	
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	         stm.setString(1, sugestao.getAval());
	         try (ResultSet rs = stm.executeQuery();) {
	            while (rs.next()) {
	               Sugestao p = new Sugestao();
	               p.setId(rs.getInt("id"));
	               p.setTitulo(rs.getString("titulo"));
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
	   
	   public ArrayList<Sugestao> carregarSuperior(Sugestao sugestao) {
	      String sqlSelect = "SELECT id, titulo, setor, avaliador_login, colaborador_login, aprovado, descricao FROM sugestao";
	      ArrayList<Sugestao> lista = new ArrayList<>();
	   	
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
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
