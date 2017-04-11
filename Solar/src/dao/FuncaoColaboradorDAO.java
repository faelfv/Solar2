package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.FuncaoColaborador;

public class FuncaoColaboradorDAO {
	
	
	public String criar(FuncaoColaborador colaborador){
	      String sqlInsert = "INSERT INTO colaborador(login, nome, cpf, sexo, tel, email, senha ) VALUES (?, ?, ?, ?, ?, ?, ?)";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
	    	 stm.setString(1, colaborador.getLogin());
	    	 stm.setString(2, colaborador.getNome());
	         stm.setString(3, colaborador.getCpf());
	         stm.setString(4, colaborador.getSexo());
	         stm.setString(5, colaborador.getTel());
	         stm.setString(6, colaborador.getEmail());
	         stm.setString(7, colaborador.getSenha());
	         stm.execute();
	       
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return colaborador.getLogin();
		}
	   
	   public void atualizar(FuncaoColaborador colaborador) {
		      String sqlUpdate = "UPDATE colaborador SET tel = ?, email = ?, senha = ? WHERE login = ?";
		   
		      try (Connection conn = ConnectionFactory.obtemConexao();
		    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
		         stm.setString(1, colaborador.getTel());
		         stm.setString(2, colaborador.getEmail());
		         stm.setString(3, colaborador.getSenha());
		         stm.setString(4, colaborador.getLogin());
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
	   
		public FuncaoColaborador carregar(String login) {
			FuncaoColaborador colaborador = new FuncaoColaborador();
			colaborador.setLogin(login);
			String sqlSelect = "SELECT nome, cpf, sexo, tel, email, senha FROM colaborador WHERE colaborador.login = ?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, colaborador.getLogin());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						colaborador.setNome(rs.getString("nome"));
						colaborador.setCpf(rs.getString("cpf"));
						colaborador.setSexo(rs.getString("sexo"));
						colaborador.setTel(rs.getString("tel"));
						colaborador.setEmail(rs.getString("email"));
						colaborador.setSenha(rs.getString("senha"));
						
					} else {
						colaborador.setNome(null);
						colaborador.setCpf(null);
						colaborador.setSexo(null);
						colaborador.setTel(null);
						colaborador.setEmail(null);
						colaborador.setSenha(null);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return colaborador;
		}
	
	  public void recuperar(FuncaoColaborador colaborador) {
	      String sqlUpdate = "UPDATE colaborador SET senha = ? WHERE login = ?";
	   
	      try (Connection conn = ConnectionFactory.obtemConexao();
	    		  PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
	         stm.setString(1,colaborador.getSenha());
	         stm.setString(2, colaborador.getLogin());
	         stm.executeUpdate();
	   	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	  }
	  public List<FuncaoColaborador> carregarTodosColab() {
		  FuncaoColaborador colaborador;
			
			List<FuncaoColaborador> lista = new ArrayList<FuncaoColaborador>();
			
			String sqlSelect = "SELECT login, nome, cpf, sexo, tel, email, senha FROM colaborador;";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						
						colaborador = new FuncaoColaborador();
						
						colaborador.setLogin(rs.getString("login"));
						colaborador.setNome(rs.getString("nome"));
						colaborador.setCpf(rs.getString("cpf"));
						colaborador.setSexo(rs.getString("sexo"));
						colaborador.setTel(rs.getString("tel"));
						colaborador.setEmail(rs.getString("email"));
						colaborador.setSenha(rs.getString("senha"));
						
						lista.add(colaborador);
						
					} 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return lista;
		}
}

