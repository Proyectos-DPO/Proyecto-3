package tiquetes;

import eventos.Localidad;

public abstract class Tiquete extends Articulo{
	
	protected String id;
	protected double precioVenta;
	protected double pagoCargo;
	protected static double cuotaEmision;
	protected boolean isContained;
	protected Localidad<?> localidadTiquete;
	protected String type;
	protected PaqueteDeluxe paqueteAsociado;
	
	
	//Este es para Persistencia :p
	public Tiquete(String id, double precioVenta, double pagoCargo,
			boolean isContained) {
		super(id, false);
		this.precioVenta = precioVenta;
		this.pagoCargo = pagoCargo;
		this.isContained = isContained;
		setTipoTiquete();
	}


	public Tiquete(String id, Localidad<?> localidadTiquete) {
		super(id, false);
		this.precioVenta = 0;
		this.pagoCargo = 0;
		this.isContained = false;
		this.localidadTiquete = localidadTiquete;
		setTipoTiquete();
	}

	
	protected abstract void setTipoTiquete();


	public double getPrecioVenta() {
		return precioVenta;
	}


	protected void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public double getPagoCargo() {
		return pagoCargo;
	}


	protected void setPagoCargo(double pagoCargo) {
		this.pagoCargo = pagoCargo;
	}


	public static double getCuotaEmision() {
		return cuotaEmision;
	}


	public static void setCuotaEmision(double cuotaEmision) {
		Tiquete.cuotaEmision = cuotaEmision;
	}


	protected boolean isContained() {
		return isContained;
	}
	
	protected void setContained(boolean lolxdxdxd) {
		this.isContained = lolxdxdxd;
	}

	public PaqueteDeluxe getPaqueteAsociado() {
		return paqueteAsociado;
	}


	protected void setPaqueteAsociado(PaqueteDeluxe paqueteAsociado) {
		this.paqueteAsociado = paqueteAsociado;
	}


	public Localidad<?> getLocalidadTiquete() {
		return localidadTiquete;
	}


	public String getType() {
		return type;
	}


	public void setLocalidadTiquete(Localidad<?> localidadTiquete) {
		this.localidadTiquete = localidadTiquete;
	}
	
	
	
	

}
