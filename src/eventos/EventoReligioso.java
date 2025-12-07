package eventos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeMap;

import usuarios.EstadosFinancieros;
import usuarios.Organizador;

public class EventoReligioso extends Evento{
	
	private static double cargoPorcentual;

	public EventoReligioso(String nombre, LocalDate fecha, LocalTime hora, double cargoPorcentual,
			Venue venue, Organizador organizador) {

		super(nombre, fecha, hora, venue, organizador);
	}

	//Persisting
	public EventoReligioso(String nombre, LocalDate fecha, LocalTime hora, int cantidadBoletas,
			TreeMap<String, Localidad<?>> localidades, Venue venue, Descuento descuento, Organizador organizador, EstadosFinancieros estados) {
		super(nombre, fecha, hora, cantidadBoletas, localidades, venue, descuento, organizador, estados);
	}
	
	@Override
	protected void setTipoEvento() {
		this.tipoEvento = "Religioso";
	}
	
	@Override
	public double getCargoPorcentual() {
		return cargoPorcentual;
	}

	public void setCargoPorcentual(double nuevoCargo) {
		cargoPorcentual = nuevoCargo;
	}
	
	public static void configurarCargoPorcentual(double nuevoCargo) {
		cargoPorcentual = nuevoCargo;
	}
	
}
