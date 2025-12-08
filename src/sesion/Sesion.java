package sesion;

public abstract class Sesion {
	
	protected String login;

	public Sesion(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
	
	
	
}
