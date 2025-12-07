package eventos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeMap;

import usuarios.EstadosFinancieros;
import usuarios.Organizador;

public class EventoCultural extends Evento{

	private static double cargoPorcentual;
	
	public EventoCultural(String nombre, LocalDate fecha, LocalTime hora, double cargoPorcentual,
			Venue venue, Organizador organizador) {

		super(nombre, fecha, hora,  venue, organizador);

	}
	
	
	
	
	//Persisting
	public EventoCultural(String nombre, LocalDate fecha, LocalTime hora, int cantidadBoletas,
			TreeMap<String, Localidad<?>> localidades, Venue venue, Descuento descuento, Organizador organizador, EstadosFinancieros estados) {
		super(nombre, fecha, hora, cantidadBoletas, localidades, venue, descuento, organizador, estados);
	}



	@Override
	protected void setTipoEvento() {
		this.tipoEvento = "Cultural";
	}
	
	@Override
	public double getCargoPorcentual() {
		// TODO Auto-generated method stub
		return cargoPorcentual;
	}

	public void setCargoPorcentual(double nuevoCargo) {
		cargoPorcentual = nuevoCargo;
	}
	
	public static void configurarCargoPorcentual(double nuevoCargo) {
		cargoPorcentual = nuevoCargo;
	}

}
