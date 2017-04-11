package model;
import java.io.Serializable;


public class FuncaoColaborador implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private String nome, cpf, sexo, tel, email, login, senha;

   public FuncaoColaborador() {
   }

   public String getNome(){
      return nome;
   }
   public String getCpf(){
      return cpf;
   }
   public String getSexo(){
      return sexo;
   }
   public String getTel(){
      return tel;
   }
   public String getEmail(){
      return email;
   }
   public String getLogin(){
      return login;
   }
   public String getSenha(){
      return senha;
   }
   
   public void setNome(String nome){
      this.nome = nome;
   }
   public void setCpf(String cpf){
      this.cpf = cpf;
   }
   public void setSexo(String sexo){
      this.sexo = sexo;
   }
   public void setTel(String tel){
      this.tel = tel;
   }
   public void setEmail(String email){
      this.email = email;
   }
   public void setLogin(String login){
      this.login = login;
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
		FuncaoColaborador other = (FuncaoColaborador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (login != other.login)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
   }
}