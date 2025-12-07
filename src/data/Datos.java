package data;

import java.util.ArrayList;
import java.util.TreeMap;

import eventos.Evento;
import eventos.Venue;
import peticiones.Peticion;
import tiquetes.PaqueteDeluxe;
import tiquetes.Tiquete;
import tiquetes.TiqueteMultiple;
import usuarios.Admin;
import usuarios.GananciasBoleteria;
import usuarios.Usuario;

public class Datos {
	
	private TreeMap<String, TiqueteMultiple> tiquetesMultiples; // La diferencia entre tiquetes múltiples y tiquetes normales es que tiquetes multiples no son contenidos por ninguna localidad.
	private TreeMap<String, Tiquete> tiquetes;
	private TreeMap<String, PaqueteDeluxe> paquetes;
	private TreeMap<String, Peticion> peticiones;
	private TreeMap<String, Usuario> usuarios;
	private TreeMap<String, Evento> eventos;
	private TreeMap<String, Venue> venues;
	
	public Datos() {
		super();
	}

	public TreeMap<String, TiqueteMultiple> getTiquetesMultiples() {
		return tiquetesMultiples;
	}

	public ArrayList<TiqueteMultiple> getTiquetesMultiplesAsList() {
		return new ArrayList<>(getTiquetesMultiples().values());
	}
	
	public TreeMap<String, Tiquete> getTiquetes() {
		return tiquetes;
	}
	
	public ArrayList<Tiquete> getTiquetesAsList() {
		return new ArrayList<>(getTiquetes().values());
	}

	public TreeMap<String, PaqueteDeluxe> getPaquetes() {
		return paquetes;
	}

	public ArrayList<PaqueteDeluxe> getPaquetesAsList() {
		return new ArrayList<>(getPaquetes().values());
	}
	
	public TreeMap<String, Peticion> getPeticiones() {
		return peticiones;
	}
	
	public ArrayList<Peticion> getPeticionesAsList() {
		return new ArrayList<>(getPeticiones().values());
	}

	public TreeMap<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	public ArrayList<Usuario> getUsuariosAsList() {
		return new ArrayList<>(getUsuarios().values());
	}

	public TreeMap<String, Evento> getEventos() {
		return eventos;
	}
	
	public ArrayList<Evento> getEventosAsList() {
		return new ArrayList<>(getEventos().values());
	}

	public TreeMap<String, Venue> getVenues() {
		return venues;
	}
	
	public ArrayList<Venue> getVenuesAsList() {
		return new ArrayList<>(getVenues().values());
	}
	
	public TiqueteMultiple getTiqueteMultiple(String id) {
		return tiquetesMultiples.get(id);
	}
	
	public Tiquete getTiquete(String id) {
		return tiquetes.get(id);
	}
	
	public PaqueteDeluxe getPaqueteDeluxe(String id) {
		return paquetes.get(id);
	}
	
	public Peticion getPeticion(String id) {
		return peticiones.get(id);
	}
	
	
	public void agregarUsuario(Usuario usuario) {
	    usuarios.put(usuario.getLogin(), usuario);
	}
	
	public Usuario getUsuario(String id) {
		return usuarios.get(id);
	}
	
	public Evento getEvento(String id) {
		return eventos.get(id);
	}
	
	public void agregarEvento(Evento evento) {
	    eventos.put(evento.getNombre(), evento);
	}
	
	public Venue getVenue(String id) {
		return venues.get(id);
	}


	public void setTiquetesMultiples(TreeMap<String, TiqueteMultiple> tiquetesMultiples) {
		this.tiquetesMultiples = tiquetesMultiples;
	}

	public void setTiquetes(TreeMap<String, Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

	public void setPaquetes(TreeMap<String, PaqueteDeluxe> paquetes) {
		this.paquetes = paquetes;
	}

	public void setPeticiones(TreeMap<String, Peticion> peticiones) {
		this.peticiones = peticiones;
	}

	public void setUsuarios(TreeMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setEventos(TreeMap<String, Evento> eventos) {
		this.eventos = eventos;
	}

	public void setVenues(TreeMap<String, Venue> venues) {
		this.venues = venues;
	}
	
	public GananciasBoleteria getGananciasBoleteria() {
		return Admin.getGananciasBoleteria();
	}
}
