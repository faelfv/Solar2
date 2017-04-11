package model;

import java.io.Serializable;

public class FuncaoAvaliador implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private String login, nome, senha;
	private int setor;
   
   public FuncaoAvaliador() {
   }

   public String getLogin(){
      return login;
   }
   public int getSetor(){
      return setor;
   }
   public String getNome(){
      return nome;
   }
   public String getSenha(){
      return senha;
   }
   
   public void setLogin(String login){
      this.login = login;
   }
   public void setSetor(int setor){
      this.setor = setor;
   }
   public void setNome(String nome){
      this.nome = nome;
   }
   public void setSenha(String senha){
      this.senha = senha;
   }

   

   @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncaoAvaliador other = (FuncaoAvaliador) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (login != other.login)
			return false;
		if (setor != other.setor) {
				return false;
		} 
		return true;
	}

}