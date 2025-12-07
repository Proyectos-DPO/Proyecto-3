package eventos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeMap;

import usuarios.EstadosFinancieros;
import usuarios.Organizador;

public abstract class Evento {
	
	protected String nombre;
	protected LocalDate fecha;
	protected LocalTime hora;
	protected String tipoEvento;
	protected int cantidadBoletas;
	protected TreeMap<String, Localidad<?>> localidades; 
	protected Venue venue;
	protected Organizador organizador;
	protected Descuento descuento;
	protected EstadosFinancieros estados;
	
	
	//Persistendoooooooooooooooooooooo
	public Evento(String nombre, LocalDate fecha, LocalTime hora, int cantidadBoletas,
			TreeMap<String, Localidad<?>> localidades, Venue venue, Descuento descuento, Organizador organizador, EstadosFinancieros estados) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.cantidadBoletas = cantidadBoletas;
		this.localidades = localidades;
		this.venue = venue;
		this.descuento = descuento;
		this.estados = estados;
		setTipoEvento();
	}

	public  Evento(String nombre, LocalDate fecha, LocalTime hora, Venue venue, Organizador organizador) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.cantidadBoletas = 0;
		this.localidades = new TreeMap<String, Localidad<?>>();
		this.estados = new EstadosFinancieros();
		this.venue = venue;
		this.organizador = organizador;
		setTipoEvento();
	}
	
	public abstract double getCargoPorcentual();

	
	public abstract void setCargoPorcentual(double nuevoCargo);


	protected abstract void setTipoEvento();
	

	public int getCantidadBoletas() {
		return cantidadBoletas;
	}




	public void setCantidadBoletas(int cantidadBoletas) {
		this.cantidadBoletas = cantidadBoletas;
	}

	public String getNombre() {
		return nombre;
	}


	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}
	
	public String getTipoEvento() {
		return tipoEvento;
	}


	public TreeMap<String, Localidad<?>> getLocalidades() {
		return localidades;
	}
	
	public ArrayList<Localidad<?>> getLocalidadesAsList() {
		return new ArrayList<>(getLocalidades().values());
	}


	public Venue getVenue() {
		return venue;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
		
	}

	public EstadosFinancieros getEstados() {
		return estados;
	}
	
	
	
	
	
}
