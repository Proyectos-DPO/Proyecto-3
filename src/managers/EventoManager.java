package managers;

import java.util.ArrayList;
import java.util.TreeMap;

import data.Datos;
import eventos.Evento;
import eventos.EventoCultural;
import eventos.EventoDeportivo;
import eventos.EventoMusical;
import eventos.EventoReligioso;
import eventos.Localidad;
import tiquetes.Tiquete;

public class EventoManager {

	public static void crearEvento(Datos datos, String type) {
		if (type.equals("Musical")) {
			
		}
		else if (type.equals("Cultural")) {
			
		}
		else if (type.equals("Deportivo")) {
			
		}
		else if (type.equals("Religioso")) {
			
		}
	}
	
	public static void cancelarEventoAdmin(Datos datos, Evento eventoACancelar) { // Se reembolsa precio - costo de emision (CE)
		ArrayList<Localidad<?>> localidades = eventoACancelar.getLocalidadesAsList();
				
		for (Localidad<?> localidad: localidades) {
			ArrayList<Tiquete> tiquetesVendidos = localidad.getTiquetesVendidosAsList();
			
			for (Tiquete tiquete : tiquetesVendidos) {
				ReembolsoManager.ejecutarReembolso("Admin", tiquete);
			}
			
		}
		
		datos.getEventos().remove(eventoACancelar.getNombre());
		eventoACancelar.getVenue().getDisponibilidad().remove(eventoACancelar.getFecha());
	}
	
	public static void cancelarEventoOrganizador(Datos datos, Evento eventoACancelar) { // Se reembolsa precio - (CE + Porcentaje Servicio) 
		ArrayList<Localidad<?>> localidades = eventoACancelar.getLocalidadesAsList();
				
		for (Localidad<?> localidad: localidades) {
			ArrayList<Tiquete> tiquetesVendidos = localidad.getTiquetesVendidosAsList();
			
			for (Tiquete tiquete : tiquetesVendidos) {
				ReembolsoManager.ejecutarReembolso("Organizador", tiquete);
			}
			
		}
		
		datos.getEventos().remove(eventoACancelar.getNombre());
		eventoACancelar.getVenue().getDisponibilidad().remove(eventoACancelar.getFecha());
	}
	
	
	public static void fijarPorcentajeServicio(double nuevoPorcentaje, String tipoEvento) {
		if("Musical".equals(tipoEvento)) {
			EventoMusical.configurarCargoPorcentual(nuevoPorcentaje);
		}
		else if("Deportivo".equals(tipoEvento)) {
			EventoDeportivo.configurarCargoPorcentual(nuevoPorcentaje);
		}
		else if("Religioso".equals(tipoEvento)) {
			EventoReligioso.configurarCargoPorcentual(nuevoPorcentaje);
		}
		else if("Cultural".equals(tipoEvento))  {
			EventoCultural.configurarCargoPorcentual(nuevoPorcentaje);
		}
		
	}
	
}
