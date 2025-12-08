package peticiones;

import usuarios.Cliente;

public abstract class Peticion {
	
	protected String id;
	protected String tipoPeticion;
	protected Cliente cliente;
	
	public Peticion(String id) {
		
		this.id = id;
		setTipoPeticion();
	}

	public String getId() {
		return id;
	}

	public String getTipoPeticion() {
		return tipoPeticion;
	}
	
	protected abstract void setTipoPeticion();

	public Cliente getCliente() {
		return cliente;
	}
	
}
