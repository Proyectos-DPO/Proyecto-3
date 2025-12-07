package managers;

import java.util.TreeMap;

import data.Datos;
import eventos.Venue;

public class VenueManager {
	
	public static void crearNuevaVenue(Datos datos, String nombre, double latitud, double longitud, int capacidad, String restricciones, Boolean aprobado) throws Exception{
		TreeMap<String,Venue> venues = datos.getVenues();
		
		if (!venues.containsKey(nombre)) {
			
			Venue nuevaVenue = new Venue(nombre, latitud, longitud, capacidad, restricciones, aprobado);
			venues.put(nombre, nuevaVenue);
			
		}
		else {
			throw new Exception("Ya existe una venue con el mismo nombre!");
		}	
	}
	
	public static void eliminarVenue(Datos datos, Venue venue) {
		datos.getVenues().remove(venue.getNombre());
	}
	
	public static void cambiarEstadoAprobado(Venue venue) {
		venue.setAprobado(false);
	}
	
	
}
