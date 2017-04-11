package service;

import java.util.ArrayList;

import dao.SugestaoDAO;
import model.Sugestao;

public class SugestaoService {
	SugestaoDAO dao = new SugestaoDAO();
	public int incluir(Sugestao sugestao) {
		return dao.incluir(sugestao);
	}
	public void atualizar(Sugestao sugestao) {
	dao.atualizar(sugestao);
	}
	public void excluir(int id) {
	dao.excluir(id);
	}
	public Sugestao carregar(int id) {
		return  dao.carregar(id);
	
	}
	public ArrayList<Sugestao> carregarColaborador(Sugestao sugestao){
		return dao.carregarColaborador(sugestao);
	}
	public ArrayList<Sugestao> carregarAvaliador(Sugestao sugestao) {
		return dao.carregarAvaliador(sugestao);
	}
		public ArrayList<Sugestao> carregarSuperior(Sugestao sugestao) {
			return dao.carregarSuperior(sugestao);
	}
	
}
