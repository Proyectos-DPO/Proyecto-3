package tiquetes;

import java.time.LocalDate;

import eventos.Localidad;

public class TiqueteIndividual extends Tiquete {
	
	private int idAsiento;
	private TiqueteMultiple tiqueteAsociado;
	private boolean impreso;
	private LocalDate fechaExpedicion;
	
	public TiqueteIndividual(String id,	int idAsiento,  Localidad<?> localidadTiquete) {
		super(id, localidadTiquete);
		this.idAsiento = idAsiento;
		this.impreso = false;
		this.fechaExpedicion = null;
	}
	
	
	//Para persistencia :p
	public TiqueteIndividual(String id,  double precioVenta, double pagoCargo,
			boolean isContained, int idAsiento, boolean impreso, LocalDate fechaExpedicion) {
		super(id,  precioVenta, pagoCargo, isContained);
		this.idAsiento = idAsiento;
		this.impreso = impreso;
		this.fechaExpedicion = fechaExpedicion;
	}



	public TiqueteMultiple getTiqueteAsociado() {
		return tiqueteAsociado;
	}


	public void setTiqueteAsociado(TiqueteMultiple tiqueteAsociado) {
		this.tiqueteAsociado = tiqueteAsociado;
	}

	@Override
	protected void setTipoTiquete() {
		type = "Individual";
		
	}


	public int getIdAsiento() {
		return idAsiento;
	}


	public boolean isImpreso() {
		return impreso;
	}


	public void setImpreso(boolean impreso) {
		this.impreso = impreso;
	}


	public LocalDate getFechaExpedicion() {
		return fechaExpedicion;
	}


	public void setFechaExpedicion(LocalDate fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	
	
	
	
	
	
}
