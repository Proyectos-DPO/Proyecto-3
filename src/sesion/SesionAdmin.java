package sesion;

import java.util.ArrayList;

import data.Datos;
import eventos.Evento;
import managers.EventoManager;
import managers.PeticionManager;
import managers.TiqueteManager;
import managers.VenueManager;
import peticiones.Peticion;

public class SesionAdmin extends Sesion {

	public SesionAdmin(String login) {
		super(login);
	}
	
	public ArrayList<Peticion> administrarPeticiones(Datos datos) {
		return PeticionManager.mostrarPeticiones(datos);
	}
	
	public void cancelarEvento(Datos datos, Evento eventoACancelar) {
		EventoManager.cancelarEventoAdmin(datos, eventoACancelar);
	}
	
	public void crearVenue(Datos datos, String nombre, double latitud, double longitud, int capacidad, String restricciones) throws Exception{
		try {
			VenueManager.crearNuevaVenue(datos, nombre, latitud, longitud, capacidad, restricciones, true);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void fijarPorcentajeServicio(double nuevoPorcentaje, String tipo) {
		EventoManager.fijarPorcentajeServicio(nuevoPorcentaje, tipo);
	}
	
	public void fijarCuotaFijaEmision(double nuevaCuota) {
		TiqueteManager.fijarCuotaEmision(nuevaCuota);
	}
	
	public void verGananciasBoleteria(Datos datos) {
		datos.getGananciasBoleteria();
	}
	
}
