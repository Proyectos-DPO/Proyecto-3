package usuarios;

public class Admin extends Usuario{

	private static GananciasBoleteria gananciasBoleteria;

	public Admin(String login, String contrasena) {
		super(login, contrasena);
	}
	
	@Override
	protected void setTipoUsuario() {
		this.tipoUsuario = "A";
	}	

	public static GananciasBoleteria getGananciasBoleteria() {
		return gananciasBoleteria;
	}
	
	public static void setGananciasBoleteria(GananciasBoleteria gananciasBoleteria) {
		Admin.gananciasBoleteria = gananciasBoleteria;
	}
	
}
