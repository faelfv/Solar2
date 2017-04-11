package service;

import java.util.ArrayList;

import dao.SetorDAO;
import model.Setor;
import model.Sugestao;

public class SetorService {
SetorDAO dao = new SetorDAO();
	
	public int incluir(Setor setor) {
		return dao.incluir(setor);
	}
	public void atualizar(Setor setor) {
	dao.atualizar(setor);
	}
	public void excluir(int idSetor) {
	dao.excluir(idSetor);
	}
	public Setor carregar(int idSetor) {
		return  dao.carregar(idSetor);
	
	}
	public ArrayList<Sugestao> carregarSugestoes(Setor setor) {
		return dao.carregarSugestoes(setor);
		
	}
	
	

}
