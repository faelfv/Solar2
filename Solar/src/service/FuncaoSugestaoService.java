package service;

import model.FuncaoSugestao;
import dao.FuncaoSugestaoDAO;

public class FuncaoSugestaoService {
	FuncaoSugestaoDAO dao = new FuncaoSugestaoDAO();
	
	public int incluir(FuncaoSugestao fSugestao){
		return dao.incluir(fSugestao);
	}
	 public void aprovar(FuncaoSugestao fSugestao) {
		 dao.aprovar(fSugestao);
	 }
	 public void negar(FuncaoSugestao fSugestao) {
		 dao.negar(fSugestao);
	 }
	 public void excluir(int id) {
		 dao.excluir(id);
	 }
	 public FuncaoSugestao carregar(int id) {
			return  dao.carregar(id);
		
		}

}
