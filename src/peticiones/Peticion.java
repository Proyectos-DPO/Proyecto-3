package peticiones;

import data.Datos;
import usuarios.Cliente;

public abstract class Peticion {
	
	protected String id;
	protected String tipoPeticion;
	protected Cliente cliente;
	
	public Peticion(String id, Cliente cliente) {
		
		this.id = id;
		this.cliente = cliente;
		setTipoPeticion();
	}
	
	public abstract String getIdAsociado();

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
