package tiquetes;

import java.util.TreeMap;

public class PaqueteDeluxe extends Articulo {
	
	private double precio;
	private String mercancia;
	private String beneficios;
	private TreeMap<String, Tiquete> tiquetes;
	public PaqueteDeluxe(String id, double precio, String mercancia, String beneficios) {
		super(id, true);
		this.precio = precio;
		this.mercancia = mercancia;
		this.beneficios = beneficios;
		this.tiquetes = new TreeMap<String, Tiquete>();
	}
	
	
	//Persistencia
	public PaqueteDeluxe(String id, double precio, String mercancia, String beneficios,
			TreeMap<String, Tiquete> tiquetes) {
		super(id, true);
		this.precio = precio;
		this.mercancia = mercancia;
		this.beneficios = beneficios;
		this.tiquetes = tiquetes;
	}



	public double getPrecio() {
		return precio;
	}
	public String getMercancia() {
		return mercancia;
	}
	public String getBeneficios() {
		return beneficios;
	}
	public TreeMap<String, Tiquete> getTiquetes() {
		return tiquetes;
	}
	
	
	
	

}
