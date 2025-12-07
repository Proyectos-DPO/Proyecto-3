package usuarios;

import java.util.ArrayList;
import java.util.TreeMap;

import tiquetes.PaqueteDeluxe;
import tiquetes.Tiquete;

public class Cliente extends Usuario{
	
	private double saldo;
	private int cantidadPeticiones;
	private TreeMap<String,Tiquete> tiquetes;
	private TreeMap<String,PaqueteDeluxe> paquetes;
	private ArrayList<Notificacion> notificaciones;
	
	

	public Cliente(String login, String contrasena) {
		super(login, contrasena);
		this.tiquetes = new TreeMap<String,Tiquete>();
		this.paquetes = new TreeMap<String,PaqueteDeluxe>();
		this.notificaciones = new ArrayList<Notificacion>();
	}
		
	//Persisting bro
	public Cliente(String login, String contrasena, double saldo, int cantidadPeticiones,
			TreeMap<String, Tiquete> tiquetes, TreeMap<String, PaqueteDeluxe> paquetes,
			ArrayList<Notificacion> notificaciones) {
		super(login, contrasena);
		this.saldo = saldo;
		this.cantidadPeticiones = cantidadPeticiones;
		this.tiquetes = tiquetes;
		this.paquetes = paquetes;
		this.notificaciones = notificaciones;
	}

	@Override
	protected void setTipoUsuario() {
		this.tipoUsuario = "C";
	}
	
	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public int getCantidadPeticiones() {
		return cantidadPeticiones;
	}

	public TreeMap<String, Tiquete> getTiquetes() {
		return tiquetes;
	}

	public TreeMap<String, PaqueteDeluxe> getPaquetes() {
		return paquetes;
	}
	
	

	
}
