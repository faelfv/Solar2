package model;
import java.io.Serializable;

public class Sugestao implements Serializable{
   
	private static final long serialVersionUID = 1L;
private int id, setorid;
   private String titulo, setor, colab, aval, descricao, status;

   public Sugestao() {
   }

   public Sugestao(int id) {
      this.id = id;
   }   

   public Sugestao(int id, int setorid, String titulo, String setor, String colab, String aval, String descricao, String status){
      this.id = id;
      this.setorid = setorid;
      this.titulo = titulo;
      this.setor = setor;
      this.colab = colab;
      this.aval = aval;
      this.descricao = descricao;
      this.status = status;
   }

   public int getId() {
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
   public String getStatus(){
      return status;
   }
   
   public void setId(int id) {
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
   public void setStatus(String status){
      this.status = status;
   }
   @Override
   public String toString() {
      return "Sugestao: " + descricao;
   } 
  
}