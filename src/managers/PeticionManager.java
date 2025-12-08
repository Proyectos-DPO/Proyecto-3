package managers;

import java.util.ArrayList;

import data.Datos;
import eventos.Evento;
import eventos.Venue;
import peticiones.Peticion;
import peticiones.PeticionCancelarEvento;
import peticiones.PeticionNuevoVenue;
import peticiones.PeticionRembolsoTiquete;
import tiquetes.Tiquete;
import usuarios.Cliente;
import usuarios.Notificacion;

public class PeticionManager {
	
	public static ArrayList<Peticion> mostrarPeticiones(Datos datos) {
		
		ArrayList<Peticion> peticiones = datos.getPeticionesAsList();
		
		return peticiones;
	}
	
	public static void crearPeticionReembolso(String login, Datos datos, Tiquete tiqueteAReembolsar) {
		String id = login + "RT" + ((Cliente)datos.getUsuario(login)).getCantidadPeticiones();
		PeticionRembolsoTiquete peticion = new PeticionRembolsoTiquete(id, (Cliente) datos.getUsuario(login),tiqueteAReembolsar);
		datos.getPeticiones().put(id,peticion);
	}
	
	public static void crearPeticionCancelarEvento(String login, Datos datos, Evento eventoACancelar) {
		String id = login + "CE" + ((Cliente)datos.getUsuario(login)).getCantidadPeticiones();
		PeticionCancelarEvento peticion = new PeticionCancelarEvento(id, (Cliente) datos.getUsuario(login),eventoACancelar);
		datos.getPeticiones().put(id, peticion);
	}
	
	public static void crearPeticionNuevoVenue(String login, Datos datos, String nombre, double latitud, double longitud, int capacidad, String restricciones) {
		String id = login + "NV" + ((Cliente)datos.getUsuario(login)).getCantidadPeticiones();
		Venue venuePeticion = new Venue(nombre, latitud, longitud, capacidad, restricciones, false);
		datos.getVenues().put(nombre, venuePeticion);
		PeticionNuevoVenue peticion = new PeticionNuevoVenue(id, (Cliente) datos.getUsuario(login),venuePeticion);
		datos.getPeticiones().put(id, peticion);
	}
	
	public static void ejecutarDecision(String id, Datos datos, boolean decision) {
		String type = datos.getPeticion(id).getTipoPeticion();
		
		if (!decision) {
			// No se acepto tu peticion, igual depende del tipo
			
			if (type.equals("creacionVenue")) {
				PeticionNuevoVenue peticion = (PeticionNuevoVenue)datos.getPeticion(id);
				VenueManager.eliminarVenue(datos, peticion.getVenueCandidato());
			}
		}
		else {
			// Depende del tipo de peticion se ejecuta una cosa diferente
			if (type.equals("reembolsarTiquete"))  {
				PeticionRembolsoTiquete peticion = (PeticionRembolsoTiquete)datos.getPeticion(id);
				ReembolsoManager.ejecutarReembolso("Calamidad",peticion.getTiqueteACancelar());
			}
			else if (type.equals("cancelarEvento")) {
				PeticionCancelarEvento peticion = (PeticionCancelarEvento)datos.getPeticion(id);
				EventoManager.cancelarEventoOrganizador(datos, peticion.getEventoACancelar());
			}
			else if (type.equals("creacionVenue")) {
				PeticionNuevoVenue peticion = (PeticionNuevoVenue)datos.getPeticion(id);
				VenueManager.cambiarEstadoAprobado(peticion.getVenueCandidato());
			}
		
			// Se le notifica al usuario la decision tomada
			Notificacion notif = new Notificacion(id, decision);
			datos.getPeticion(id).getCliente().getNotificaciones().add(notif);
		}
		datos.getPeticiones().remove(id); 
	}
	
}
