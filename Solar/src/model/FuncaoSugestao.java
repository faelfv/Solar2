package model;
import java.io.Serializable;

public class FuncaoSugestao implements Serializable{

	private static final long serialVersionUID = 1L;
private int id, setorid;
   private String titulo, setor, colab, aval, descricao, aprovado;
   
   public FuncaoSugestao() {
   }
   
   public FuncaoSugestao(int id, int setorid, String titulo, String setor, String colab, String aval, String descricao, String aprovado){
      this.id = id;
      this.setorid = setorid;
      this.titulo = titulo;
      this.setor = setor;
      this.colab = colab;
      this.aval = aval;
      this.descricao = descricao;
      this.aprovado = aprovado;
   }
   
   public int getId(){
      return id;
   }
   public int getSetorid(){
      return setorid;
   }
   public String getTitulo(){
      return titulo;
   }
   public String getSetor(){
      return setor;
   }
   public String getColab(){
      return colab;
   }
   public String getAval(){
      return aval;
   }
   public String getDescricao(){
      return descricao;
   }
   public String getAprovado(){
      return aprovado;
   }
   
   public void setId(int id){
      this.id = id;
   }
   public void setSetorid(int setorid){
      this.setorid = setorid;
   }
   public void setTitulo(String titulo){
      this.titulo = titulo;
   }   
   public void setSetor(String setor){
      this.setor = setor;
   }
   public void setColab(String colab){
      this.colab = colab;
   }
   public void setAval(String aval){
      this.aval = aval;
   }   
   public void setDescricao(String descricao){
      this.descricao = descricao;
   } 
   public void setAprovado(String aprovado){
      this.aprovado = aprovado;
   }   
   
   
   
  
}