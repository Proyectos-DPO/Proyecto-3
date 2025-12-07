package tiquetes;

import usuarios.Cliente;

public abstract class Articulo {
	
	protected String id;
	protected boolean isPaquete;
	protected Cliente propietario;
	
	public Articulo(String id, boolean isPaquete) {
		this.id = id;
		this.isPaquete =isPaquete;
		this.propietario = null;
	}

	public String getId() {
		return id;
	}

	public boolean isPaquete() {
		return isPaquete;
	}

	public Cliente getPropietario() {
		return propietario;
	}

	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}
	
	
	
	
}
