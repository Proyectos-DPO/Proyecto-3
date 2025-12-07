package consolas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import java.time.LocalDate;
import java.time.LocalTime;

import data.Datos;
import eventos.Evento;
import eventos.EventoMusical;
import eventos.Localidad;
import eventos.EventoDeportivo;

import eventos.EventoCultural;

import tiquetes.TiqueteMultiple;
import tiquetes.Tiquete;
import tiquetes.TiqueteIndividual;
import tiquetes.PaqueteDeluxe;
import peticiones.Peticion;
import usuarios.Usuario;
import usuarios.Cliente;
import eventos.Venue;
import persistencia.Persistencia;
import usuarios.Organizador;



public class ConsolaCliente extends ConsolaBasica{
	
	private Datos datos;
	
	

    public ConsolaCliente(Datos datos) {
        this.datos = datos;
    }
	
	/**
     * Opciones que se mostrarán en el menú principal
     */
    private final String[] opcionesMenuPrincipal = new String[]{ "¿Ya tienes una cuenta? ¡Inicia sesión!", "¿No tienes cuenta? ¡Crea una aquí!", "Salir" };

    
    public void mostrarMenuPrincipal( ) {
    	
    	int opcionSeleccionada = mostrarMenu( "BIENVENIDO CLIENTE", opcionesMenuPrincipal );
        if( opcionSeleccionada == 1 )
        {
        	System.out.println( "\n Perfecto!!!\n" );
            pediryConfirmarLoginYContrasena();
        }
        else if( opcionSeleccionada == 2 )
        {
            crearCliente();
        }
        else if( opcionSeleccionada == 3 )
        {
        	System.out.println( "Saliendo ..." );
            System.exit( 0 );
        }
        
        
        
    }
    
    private void pediryConfirmarLoginYContrasena() {
    	
    	String login = pedirCadenaAlUsuario("Ingresa tu login/Username");
    	String contrasena = pedirCadenaAlUsuario("Ingresa tu contraseña");
    	
    	Usuario usuarioEncontrado = datos.getUsuario(login);
    	
    	// 1. No existe usuario con ese login
        if (usuarioEncontrado == null) {
            System.out.println("No existe un usuario con ese login. Intenta de nuevo.\n");
            // Puedes volver al menú principal o repetir el proceso
            pediryConfirmarLoginYContrasena();
            return;
        }
        
     // 2. La contraseña no coincide
        else if (!usuarioEncontrado.getContrasena().equals(contrasena)) {
            System.out.println("Contraseña incorrecta. Intenta de nuevo.\n");
            // Igual: puedes volver al menú o repetir
            mostrarMenuPrincipal();
            return;
        }
        
        // 3.La contrasena y el usuario son correctos 
        else {
        	ConsolaEspecificaCliente consolaCliente = new ConsolaEspecificaCliente(datos);
        	consolaCliente.mostrarMenuPrincipalEspecifico();
        }
        
    	
    }
    
    
    private void crearCliente() {

        System.out.println("\n--- CREAR NUEVA CUENTA ---\n");

        // 1. Pedir login único
        String login;
        while (true) {
            login = pedirCadenaAlUsuario("Ingresa el login/Username que quieres usar");

            if (login == null || login.trim().isEmpty()) {
                System.out.println("El login no puede estar vacío.\n");
                continue;
            }

            Usuario existente = datos.getUsuario(login);
            if (existente != null) {
                System.out.println("Ya existe un usuario con ese login. Intenta con otro.\n");
            } else {
                break; // login disponible
            }
        }

        // 2. Pedir y confirmar contraseña
        String contrasena = pedirCadenaAlUsuario("Ingresa una contraseña");
        String confirmacion = pedirCadenaAlUsuario("Confirma la contraseña");

        while (!contrasena.equals(confirmacion)) {
            System.out.println("Las contraseñas no coinciden. Intenta de nuevo.\n");
            contrasena = pedirCadenaAlUsuario("Ingresa una contraseña");
            confirmacion = pedirCadenaAlUsuario("Confirma la contraseña");
        }

        // 3. Crear el cliente y guardarlo en Datos
        Usuario nuevoCliente = new Cliente(login, contrasena);
        datos.agregarUsuario(nuevoCliente);

        System.out.println("\n Cuenta creada exitosamente. Ahora puedes iniciar sesión.\n");

        pediryConfirmarLoginYContrasena();
    }
    
    
   //Estos es para hacer pruebas 
    
    /**public static Datos crearDummyDatos() {

        // --------- Colecciones vacías para el resto ---------
        TreeMap<String, TiqueteMultiple> tiquetesMultiples = new TreeMap<>();
        TreeMap<String, Tiquete> tiquetes = new TreeMap<>();
        TreeMap<String, PaqueteDeluxe> paquetes = new TreeMap<>();
        TreeMap<String, Peticion> peticiones = new TreeMap<>();
        TreeMap<String, Evento> eventos = new TreeMap<>();
        TreeMap<String, Venue> venues = new TreeMap<>();

        // --------- USUARIOS DUMMY ---------
        TreeMap<String, Usuario> usuarios = new TreeMap<>();

        Usuario u1 = new Cliente("juan123", "password1");
        Usuario u2 = new Cliente("maria45", "mypass");
        Usuario u3 = new Cliente("pedroXD", "qwerty");
        Usuario u4 = new Cliente("anaTest", "1234");
        Usuario u5 = new Cliente("usuarioDemo", "demo123");

        usuarios.put(u1.getLogin(), u1);
        usuarios.put(u2.getLogin(), u2);
        usuarios.put(u3.getLogin(), u3);
        usuarios.put(u4.getLogin(), u4);
        usuarios.put(u5.getLogin(), u5);

        // --------- VENUES DUMMY ---------
        Venue movistarArena = new Venue(
                "Movistar Arena",
                4.6486,           // latitud dummy
                -74.0789,         // longitud dummy
                12000,            // capacidad
                "No ingreso de armas ni bebidas externas",
                true              // aprobado
        );

        Venue estadioElCampin = new Venue(
                "Estadio El Campín",
                4.6460,
                -74.0785,
                40000,
                "Zonas habilitadas según el evento",
                true
        );

        Venue corferias = new Venue(
                "Corferias",
                4.6295,
                -74.0897,
                20000,
                "Control de aforo por pabellón",
                true
        );

        venues.put(movistarArena.getNombre(), movistarArena);
        venues.put(estadioElCampin.getNombre(), estadioElCampin);
        venues.put(corferias.getNombre(), corferias);

        // --------- ORGANIZADORES (por ahora null) ---------
        Organizador orgNull = null;

        // --------- EVENTOS DUMMY ---------

        // 1. Concierto musical en Movistar Arena
        Evento conciertoRock = new EventoMusical(
                "Rock Fest Bogotá",
                LocalDate.of(2025, 5, 10),
                LocalTime.of(20, 0),
                0.12,
                movistarArena,
                orgNull
        );

        // Localidades del concierto
        Localidad<TiqueteIndividual> rockPlatea =
                new Localidad<>("Platea General", false, 80000, conciertoRock); // simple
        Localidad<TiqueteIndividual> rockSillaNumerada =
                new Localidad<>("Silla Numerada", true, 120000, conciertoRock); // asiento numerado
        Localidad<TiqueteMultiple> rockPalco =
                new Localidad<>("Palco x10 personas", false, 1000000, conciertoRock); // múltiple

        conciertoRock.getLocalidades().put(rockPlatea.getNombre(), rockPlatea);
        conciertoRock.getLocalidades().put(rockSillaNumerada.getNombre(), rockSillaNumerada);
        conciertoRock.getLocalidades().put(rockPalco.getNombre(), rockPalco);

        // 2. Partido de fútbol en El Campín
        Evento partidoClasico = new EventoDeportivo(
                "Clásico Capitalino",
                LocalDate.of(2025, 6, 5),
                LocalTime.of(18, 30),
                0.10,
                estadioElCampin,
                orgNull
        );

        Localidad<TiqueteIndividual> clasicoLateral =
                new Localidad<>("Lateral Popular", false, 60000, partidoClasico); // simple
        Localidad<TiqueteIndividual> clasicoOccidentalNumerada =
                new Localidad<>("Occidental Numerada", true, 150000, partidoClasico); // numerado
        Localidad<TiqueteMultiple> clasicoPalco =
                new Localidad<>("Palco Occidental x10", false, 1200000, partidoClasico); // múltiple

        partidoClasico.getLocalidades().put(clasicoLateral.getNombre(), clasicoLateral);
        partidoClasico.getLocalidades().put(clasicoOccidentalNumerada.getNombre(), clasicoOccidentalNumerada);
        partidoClasico.getLocalidades().put(clasicoPalco.getNombre(), clasicoPalco);

        // 3. Feria cultural en Corferias
        Evento feriaLibro = new EventoCultural(
                "Feria del Libro",
                LocalDate.of(2025, 4, 20),
                LocalTime.of(10, 0),
                0.08,
                corferias,
                orgNull
        );

        Localidad<TiqueteIndividual> feriaEntradaGeneral =
                new Localidad<>("Entrada General", false, 30000, feriaLibro); // simple
        Localidad<TiqueteIndividual> feriaAuditorioNumerado =
                new Localidad<>("Auditorio Principal (Silla)", true, 50000, feriaLibro); // numerado
        Localidad<TiqueteMultiple> feriaSalaVip =
                new Localidad<>("Sala VIP x10", false, 800000, feriaLibro); // múltiple

        feriaLibro.getLocalidades().put(feriaEntradaGeneral.getNombre(), feriaEntradaGeneral);
        feriaLibro.getLocalidades().put(feriaAuditorioNumerado.getNombre(), feriaAuditorioNumerado);
        feriaLibro.getLocalidades().put(feriaSalaVip.getNombre(), feriaSalaVip);

        // --------- Registrar eventos en el mapa ---------
        eventos.put(conciertoRock.getNombre(), conciertoRock);
        eventos.put(partidoClasico.getNombre(), partidoClasico);
        eventos.put(feriaLibro.getNombre(), feriaLibro);

        // --------- Retornar Datos ---------
        return new Datos(
                tiquetesMultiples,
                tiquetes,
                paquetes,
                peticiones,
                usuarios,
                eventos,
                venues
        );
    }**/

    
    
    
    
    /**
     * Este es el método que se utiliza para iniciar la aplicación
     * @param args
     */
    public static void main( String[] args ) throws Exception
    {
    	
    	//Cargar datos
    	//Datos datos = cargarDatosDesdeArchivos();
    	
    	
    	Datos datos = Persistencia.cargarDatos();
    	
        ConsolaCliente c = new ConsolaCliente(datos);
        c.mostrarMenuPrincipal( );
    }

}
