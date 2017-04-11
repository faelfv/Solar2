package service;

import dao.FuncaoAvaliadorDAO;
import model.FuncaoAvaliador;

public class FuncaoAvaliadorService {
	FuncaoAvaliadorDAO dao = new FuncaoAvaliadorDAO();
	
	public String incluir(FuncaoAvaliador avaliador) {
		return dao.incluir(avaliador);
	}
	public void atualizar(FuncaoAvaliador avaliador) {
	dao.atualizar(avaliador);
	}
	public void excluir(String login) {
	dao.excluir(login);
	}
	public FuncaoAvaliador carregar(String login) {
		return  dao.carregar(login);
	
	}



}
