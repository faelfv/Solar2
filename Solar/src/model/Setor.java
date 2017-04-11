package model;
import java.io.Serializable;
import java.util.ArrayList;


public class Setor implements Serializable{
 
	private static final long serialVersionUID = 1L;
	public int idSetor;
	private String nome;
	private String fone;
	private ArrayList<Sugestao> sugestoes;

   public Setor() {
      sugestoes = new ArrayList<>();
   }

   public Setor(int idSetor) {
      this.idSetor = idSetor;
      sugestoes = new ArrayList<>();
   }

   public Setor(int idSetor, String nome, String fone) {
      this.idSetor = idSetor;
      this.nome = nome;
      this.fone = fone;
      sugestoes = new ArrayList<>();
   }
   
 

   public int getIdSetor() {
      return idSetor;
   }

   public void setIdSetor(int idSetor) {
      this.idSetor = idSetor;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getFone() {
      return fone;
   }

   public void setFone(String fone) {
      this.fone = fone;
   }

   public ArrayList<Sugestao> getSugestoes() {
      return sugestoes;
   }

   public void setSugestoes(ArrayList<Sugestao> sugestoes) {
      this.sugestoes = sugestoes;
   }
	
   public void adicionarSugestao(Sugestao sugestao){
      sugestoes.add(sugestao);
   }

   
	public String listarSugestoes(){
      String listagem = "";
      for(Sugestao sugestao:sugestoes){
         listagem += "\n\t" + sugestao.toString();
      }
      return listagem;
   }
	@Override
   public String toString() {
      return "Setor [idSetor=" + idSetor + ", nome=" + nome + ", fone="
         	+ fone + "]"+listarSugestoes();
   }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (fone == null) {
			if (other.fone != null)
				return false;
		} else if (!fone.equals(other.fone))
			return false;
		if (idSetor != other.idSetor){
			return false;
	}
		return true;
   }
   
 
}
