package tiquetes;

import eventos.Localidad;

public class TiqueteIndividual extends Tiquete {
	
	private int idAsiento;
	private TiqueteMultiple tiqueteAsociado;
	
	public TiqueteIndividual(String id,	int idAsiento,  Localidad<?> localidadTiquete) {
		super(id, localidadTiquete);
		this.idAsiento = idAsiento;
	}
	
	
	//Para persistencia :p
	public TiqueteIndividual(String id,  double precioVenta, double pagoCargo,
			boolean isContained, int idAsiento) {
		super(id,  precioVenta, pagoCargo, isContained);
		this.idAsiento = idAsiento;
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
	
	
}
