package usuarios;

public class Notificacion {
	
	private String mensaje;
	
	public String getMensaje() {
		return mensaje;
	}
	
	//Persisting
	public Notificacion(String msg) {
		this.mensaje = msg;
	}
	
	
	public Notificacion(String idPeticion, boolean decision) {
		String resultado = "";
		if (decision){
			resultado = " fue aceptada.";
		} else {
			resultado = " fue denegada.";
		}
		String msg = "Peticion" + idPeticion + resultado;
		this.mensaje = msg;
	}
}