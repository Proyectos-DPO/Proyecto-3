package usuarios;

import java.util.ArrayList;
import java.util.TreeMap;

import eventos.Evento;
import tiquetes.PaqueteDeluxe;
import tiquetes.Tiquete;

public class Organizador extends Cliente{
	
	private EstadosFinancieros estadosFinancieros;
	private TreeMap<String, Evento> eventos;
	
	public Organizador(String login, String contrasena) {
		super(login, contrasena);
		this.estadosFinancieros = new EstadosFinancieros();
		this.eventos = new TreeMap<String, Evento>();
	}
	
	
	
	public Organizador(String login, String contrasena, double saldo, int cantidadPeticiones,
			TreeMap<String, Tiquete> tiquetes, TreeMap<String, PaqueteDeluxe> paquetes,
			ArrayList<Notificacion> notificaciones, EstadosFinancieros estadosFinancieros) {
		super(login, contrasena, saldo, cantidadPeticiones, tiquetes, paquetes, notificaciones);
		this.estadosFinancieros = estadosFinancieros;
		this.eventos = new TreeMap<String, Evento>();
	}



	@Override
	protected void setTipoUsuario() {
		this.tipoUsuario = "CO";
	}

	public EstadosFinancieros getEstadosFinancieros() {
		return estadosFinancieros;
	}



	public TreeMap<String, Evento> getEventos() {
		return eventos;
	}



	public void setEventos(TreeMap<String, Evento> eventos) {
		this.eventos = eventos;
	}
	
	

}
