
public class Usuario {

	private String login;
	private String nome;
	private String email;
	
	

	public Usuario(String login, String nome, String email) {
		this.login = login;
		this.nome = nome;
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Login: " + this.login + "\nNome : " + this.nome + "\nEmail: " + this.email;
	}
}

