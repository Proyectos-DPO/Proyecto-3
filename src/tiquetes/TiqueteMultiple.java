package tiquetes;

import java.util.TreeMap;

import eventos.Localidad;

public class TiqueteMultiple extends Tiquete{
	private boolean isCompleto;
	private TreeMap<String, TiqueteIndividual> tiquetes;
	private boolean isInVariosEventos;
	
	
	//Para persistencia :p
	public TiqueteMultiple(String id, double precioVenta, double pagoCargo,
			boolean isContained, boolean isCompleto, TreeMap<String, TiqueteIndividual> tiquetes,
			boolean isInVariosEventos) {
		super(id, precioVenta, pagoCargo, isContained);
		this.isCompleto = isCompleto;
		this.tiquetes = tiquetes;
		this.isInVariosEventos = isInVariosEventos;
	}

	public TiqueteMultiple(String id,  Localidad<?> localidadTiquete, boolean isInVariosEventos) {
		super(id, localidadTiquete);
		this.isCompleto = true;
		this.tiquetes = new TreeMap<String, TiqueteIndividual>();
		this.isInVariosEventos = isInVariosEventos;
	}

	@Override
	protected void setTipoTiquete() {
		type = "Multiple";
	}

	public boolean isCompleto() {
		return isCompleto;
	}

	public void setCompleto(boolean isCompleto) {
		this.isCompleto = isCompleto;
	}

	public TreeMap<String, TiqueteIndividual> getTiquetes() {
		return tiquetes;
	}

	public boolean isInVariosEventos() {
		return isInVariosEventos;
	}
	
	
	

	
}
