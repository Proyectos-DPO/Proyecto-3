package usuarios;

public abstract class Usuario {

	protected String tipoUsuario;
	protected String login;
	protected String contrasena;
	
	public Usuario(String login, String contrasena) {
		super();
		this.login = login;
		this.contrasena = contrasena;
		setTipoUsuario();
	}
	
	
	protected abstract void setTipoUsuario();


	public String getTipoUsuario() {
		return tipoUsuario;
	}


	public String getLogin() {
		return login;
	}

	
	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	
}
