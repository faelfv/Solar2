package service;
import java.util.List;

import dao.FuncaoColaboradorDAO;

import model.FuncaoColaborador;

public class FuncaoColaboradorService {
FuncaoColaboradorDAO dao = new FuncaoColaboradorDAO();
	
	public String criar(FuncaoColaborador colaborador) {
		return dao.criar(colaborador);
	}
	public void atualizar(FuncaoColaborador colaborador) {
	dao.atualizar(colaborador);
	}
	public void excluir(String login) {
	dao.excluir(login);
	}
	public FuncaoColaborador carregar(String login) {
		return  dao.carregar(login);
	
	}
	public void recuperar(FuncaoColaborador colaborador) {
		dao.recuperar(colaborador);
		
	}
	
	public List<FuncaoColaborador> carregarTodosColab(){
		return dao.carregarTodosColab();
	} 
}
