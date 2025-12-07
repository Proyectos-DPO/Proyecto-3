package eventos;

import java.util.ArrayList;
import java.util.TreeMap;

import tiquetes.Tiquete;
import tiquetes.TiqueteMultiple;
import usuarios.EstadosFinancieros;

public class Localidad<T extends Tiquete> {

	private String nombre;
	private boolean isNumerado;
	private int counter;
	private double precioBoletas;
	private Evento evento;
	private TreeMap<String, T> tiquetesLocalidad;
	private boolean isMultiple;
	private EstadosFinancieros estados;
	private TreeMap<String, T> tiquetesVendidos;
	
	public Localidad(String nombre, boolean isNumerado, double precioBoletas, Evento evento, boolean isMultiple) {
		this.nombre = nombre;
		this.counter = 0;
		this.isNumerado = isNumerado;
		this.precioBoletas = precioBoletas;
		this.evento = evento;
		this.tiquetesLocalidad = new TreeMap<String, T>();
		this.estados = new EstadosFinancieros();
		this.isMultiple = isMultiple;
	}
	
	//Persistencia
	public Localidad(String nombre, boolean isNumerado, int counter, double precioBoletas,
			TreeMap<String, T> tiquetesLocalidad, boolean isMultiple, EstadosFinancieros estados) {
		super();
		this.nombre = nombre;
		this.isNumerado = isNumerado;
		this.counter = counter;
		this.precioBoletas = precioBoletas;
		this.tiquetesLocalidad = tiquetesLocalidad;
		this.isMultiple = isMultiple;
		this.estados = estados;
	}

	public double getPrecioBoletas() {
		return precioBoletas;
	}

	

	public void setPrecioBoletas(double precioBoletas) {
		this.precioBoletas = precioBoletas;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isNumerado() {
		return isNumerado;
	}

	public int getCounter() {
		return counter;
	}

	public Evento getEvento() {
		return evento;
	}

	public TreeMap<String, T> getTiquetesLocalidad() {
		return tiquetesLocalidad;
	}

	public boolean isMultiple() {
		return isMultiple;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public EstadosFinancieros getEstados() {
		return estados;
	}
	
	public ArrayList<Tiquete> getTiquetesLocalidadAsList() {
		return new ArrayList<>(getTiquetesLocalidad().values());
	}

	public TreeMap<String, T> getTiquetesVendidos() {
		return tiquetesVendidos;
	}
	public ArrayList<Tiquete> getTiquetesVendidosAsList() {
		return new ArrayList<>(getTiquetesVendidos().values());
	}

}
