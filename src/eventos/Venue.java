package eventos;
import java.util.HashSet;
import java.time.LocalDate;

public class Venue {
	
	private String nombre;
	private double latitud;
	private double longitud;
	private int capacidad;
	private String restricciones;
	private HashSet<LocalDate> disponibilidad;
	private boolean aprobado;
	
	
	public Venue(String nombre, double latitud, double longitud, int capacidad, String restricciones, boolean aprobado) {
		super();
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.capacidad = capacidad;
		this.restricciones = restricciones;
		this.disponibilidad = new HashSet<LocalDate>();
		this.aprobado = aprobado;
	}
	
	


	public Venue(String nombre, double latitud, double longitud, int capacidad, String restricciones,
			HashSet<LocalDate> disponibilidad, boolean aprobado) {
		super();
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.capacidad = capacidad;
		this.restricciones = restricciones;
		this.disponibilidad = disponibilidad;
		this.aprobado = aprobado;
	}




	public boolean isAprobado() {
		return aprobado;
	}


	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}


	public String getNombre() {
		return nombre;
	}


	public double getLatitud() {
		return latitud;
	}


	public double getLongitud() {
		return longitud;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public String getRestricciones() {
		return restricciones;
	}


	public HashSet<LocalDate> getDisponibilidad() {
		return disponibilidad;
	}
	
	
	
	

}
