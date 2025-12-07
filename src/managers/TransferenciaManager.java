package managers;

import java.util.TreeMap;

import data.Datos;
import sesion.Sesion;
import tiquetes.Tiquete;
import usuarios.Cliente;

public class TransferenciaManager {

	public static void transferirTiquete(Datos datos, Tiquete tiqueteATransferir, Cliente receptor){
		Cliente propietario = tiqueteATransferir.getPropietario();
		TreeMap tiquetesPropietario = propietario.getTiquetes();
		TreeMap tiquetesReceptor = receptor.getTiquetes();	
		String idTiquete = tiqueteATransferir.getId();
		
		tiquetesPropietario.remove(idTiquete);
		tiquetesReceptor.put(idTiquete, tiqueteATransferir);
		
	}
	
}
