package data;

import sesion.Sesion;
import sesion.SesionAdmin;
import sesion.SesionCliente;
import sesion.SesionOrganizador;
import usuarios.Admin;
import usuarios.Cliente;
import usuarios.Organizador;
import usuarios.Usuario;

public class Autenticador {
	
	
	public static Sesion iniciarSesion(Datos datos, String login, String contrasena) throws Exception{
		Usuario usuarioEncontrado = datos.getUsuario(login);
		if (usuarioEncontrado == null) {
			throw new Exception("El usuario no existe");
		}
		if (!usuarioEncontrado.getContrasena().equals(contrasena)) {
			throw new Exception("Contraseña incorrecta");
		}
		
		switch (usuarioEncontrado.getTipoUsuario()) {
		
		case "A":
			return new SesionAdmin(usuarioEncontrado.getLogin());
		case "CO":
			return new SesionOrganizador(usuarioEncontrado.getLogin());
		default:
			return new SesionCliente(usuarioEncontrado.getLogin());
		}
		
		
	}
	
	public static void crearUsuario(Datos datos, String login, String contrasena, String tipo) throws Exception{
		Usuario usuarioEncontrado = datos.getUsuario(login);
		if (! (usuarioEncontrado == null)) {
			throw new Exception("El usuario ya existe");
		}
		Usuario nuevoUsuario;
		switch (tipo) {
		case "A":
			nuevoUsuario = new Admin(login, contrasena);
		case "CO":
			nuevoUsuario = new Organizador(login, contrasena);
		default:
			nuevoUsuario = new Cliente(login, contrasena);
		}
		
		
		datos.agregarUsuario(nuevoUsuario);
	}
}
