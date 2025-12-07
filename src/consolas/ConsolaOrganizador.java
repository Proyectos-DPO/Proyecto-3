package consolas;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import data.Datos;
import eventos.Evento;
import eventos.EventoMusical;
import eventos.EventoDeportivo;
import eventos.EventoCultural;

import eventos.EventoReligioso;
import eventos.Venue;
import usuarios.Usuario;
import usuarios.Organizador;

import eventos.Localidad;
import tiquetes.TiqueteIndividual;
import tiquetes.TiqueteMultiple;

public class ConsolaOrganizador extends ConsolaBasica {

    private Datos datos;
    private Organizador organizadorActual;

    private final String[] opcionesModoActuacion = new String[] {
        "Actuar como cliente/comprador",
        "Actuar como organizador/promotor",
        "Salir"
    };

    private final String[] opcionesMenuOrganizador = new String[] {
        "Crear eventos",
        "Sugerir venue",
        "Salir"
    };

    private final String[] opcionesTipoEvento = new String[] {
        "Musical",
        "Deportivo",
        "Cultural",
        "Religioso"
    };

    private final String[] opcionesAccesoOrganizador = new String[] {
        "¿Ya tienes cuenta de organizador? ¡Inicia sesión!",
        "¿No tienes cuenta de organizador? ¡Crea una aquí!",
        "Volver"
    };

    public ConsolaOrganizador(Datos datos) {
        this.datos = datos;
        this.organizadorActual = null;
    }

    public void mostrarMenuModoActuacion() {

        int opcionSeleccionada = mostrarMenu("¿CÓMO DESEAS ACTUAR, ORGANIZADOR?", opcionesModoActuacion);

        if (opcionSeleccionada == 1) {
           
            ConsolaCliente consolaCliente = new ConsolaCliente(datos);
            consolaCliente.mostrarMenuPrincipal();

        } else if (opcionSeleccionada == 2) {
            
            mostrarMenuAccesoOrganizador();

        } else if (opcionSeleccionada == 3) {
            System.out.println("Saliendo...");
            System.exit(0);
        }
    }

    
    private void mostrarMenuAccesoOrganizador() {

        int opcion = mostrarMenu("ACCESO ORGANIZADOR / PROMOTOR", opcionesAccesoOrganizador);

        if (opcion == 1) {
            iniciarSesionOrganizador();
        }
        else if (opcion == 2) {
            crearOrganizador();
        }
        else if (opcion == 3) {
            
            mostrarMenuModoActuacion();
        }
    }

    private void iniciarSesionOrganizador() {

        String login = pedirCadenaAlUsuario("Ingresa tu login de organizador");
        String contrasena = pedirCadenaAlUsuario("Ingresa tu contraseña");

        Usuario usuarioEncontrado = datos.getUsuario(login);

        if (usuarioEncontrado == null) {
            System.out.println("No existe un usuario con ese login. Intenta de nuevo.\n");
            iniciarSesionOrganizador();
            return;
        }

        if (!(usuarioEncontrado instanceof Organizador)) {
            System.out.println("El usuario existe, pero no es una cuenta de organizador.\n");
            mostrarMenuAccesoOrganizador();
            return;
        }

        if (!usuarioEncontrado.getContrasena().equals(contrasena)) {
            System.out.println("Contraseña incorrecta. Intenta de nuevo.\n");
            iniciarSesionOrganizador();
            return;
        }

        organizadorActual = (Organizador) usuarioEncontrado;
        System.out.println("\nInicio de sesión exitoso como organizador: " + organizadorActual.getLogin() + "\n");

        mostrarMenuOrganizador();
    }

    
    private void crearOrganizador() {

        System.out.println("\n--- CREAR NUEVA CUENTA DE ORGANIZADOR ---\n");

        
        String login;
        while (true) {
            login = pedirCadenaAlUsuario("Ingresa el login/Username que quieres usar como organizador");

            if (login == null || login.trim().isEmpty()) {
                System.out.println("El login no puede estar vacío.\n");
                continue;
            }

            Usuario existente = datos.getUsuario(login);
            if (existente != null) {
                System.out.println("Ya existe un usuario con ese login. Intenta con otro.\n");
            } else {
                break; 
            }
        }

        String contrasena = pedirCadenaAlUsuario("Ingresa una contraseña");
        String confirmacion = pedirCadenaAlUsuario("Confirma la contraseña");

        while (!contrasena.equals(confirmacion)) {
            System.out.println("Las contraseñas no coinciden. Intenta de nuevo.\n");
            contrasena = pedirCadenaAlUsuario("Ingresa una contraseña");
            confirmacion = pedirCadenaAlUsuario("Confirma la contraseña");
        }

        Organizador nuevoOrganizador = new Organizador(login, contrasena);
        datos.agregarUsuario(nuevoOrganizador);

        organizadorActual = nuevoOrganizador;

        System.out.println("\nCuenta de organizador creada exitosamente. Sesión iniciada como: "
                           + organizadorActual.getLogin() + "\n");

        mostrarMenuOrganizador();
    }

    private void mostrarMenuOrganizador() {

        int opcionSeleccionada = mostrarMenu("MENÚ ORGANIZADOR / PROMOTOR", opcionesMenuOrganizador);

        if (opcionSeleccionada == 1) {
            
            crearEvento();
        }
        else if (opcionSeleccionada == 2) {
        	sugerirVenue();
        }
        else if (opcionSeleccionada == 3) {
        	System.out.println( "Saliendo ..." );
            System.exit( 0 );
        }
    }

    
    private void crearEvento() {

        if (organizadorActual == null) {
            System.out.println("Debes iniciar sesión como organizador antes de crear eventos.\n");
            mostrarMenuAccesoOrganizador();
            return;
        }

        System.out.println("\n--- CREAR NUEVO EVENTO ---\n");


        String nombre = pedirCadenaAlUsuario("Ingresa el nombre del evento");

        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            int anio = pedirEnteroAlUsuario("Ingresa el año del evento (por ejemplo 2027)");
            int mes = pedirEnteroAlUsuario("Ingresa el mes del evento (1-12)");
            int dia = pedirEnteroAlUsuario("Ingresa el día del evento (1-31)");

            try {
                fecha = LocalDate.of(anio, mes, dia);
                fechaValida = true;
            }
            catch (DateTimeException e) {
                System.out.println("La fecha ingresada no es válida. Intenta de nuevo.\n");
            }
        }

        LocalTime hora = null;
        boolean horaValida = false;
        while (!horaValida) {
            int horaInt = pedirEnteroAlUsuario("Ingresa la hora del evento (0-23)");
            int minutoInt = pedirEnteroAlUsuario("Ingresa los minutos del evento (0-59)");

            try {
                hora = LocalTime.of(horaInt, minutoInt);
                horaValida = true;
            }
            catch (DateTimeException e) {
                System.out.println("La hora ingresada no es válida. Intenta de nuevo.\n");
            }
        }

        double cargoPorcentual = pedirNumeroAlUsuario(
                "Ingresa el cargo porcentual (por ejemplo 0.12 para 12%)"
        );

        
        int opcionTipo = mostrarMenu("¿Qué tipo de evento deseas crear?", opcionesTipoEvento);

        
        ArrayList<Venue> listaVenues = datos.getVenuesAsList();
        if (listaVenues.isEmpty()) {
            System.out.println("No hay venues disponibles para asociar al evento.\n");
            return;
        }

        String[] opcionesVenues = new String[listaVenues.size()];
        for (int i = 0; i < listaVenues.size(); i++) {
            opcionesVenues[i] = listaVenues.get(i).getNombre();
        }

        int opcionVenue = mostrarMenu("Selecciona el venue para el evento", opcionesVenues);
        Venue venueSeleccionado = listaVenues.get(opcionVenue - 1);

        
        Evento nuevoEvento = null;

        if (opcionTipo == 1) {
            nuevoEvento = new EventoMusical(
                    nombre,
                    fecha,
                    hora,
                    cargoPorcentual,
                    venueSeleccionado,
                    organizadorActual
            );
        }
        else if (opcionTipo == 2) {
            nuevoEvento = new EventoDeportivo(
                    nombre,
                    fecha,
                    hora,
                    cargoPorcentual,
                    venueSeleccionado,
                    organizadorActual
            );
        }
        else if (opcionTipo == 3) {
            nuevoEvento = new EventoCultural(
                    nombre,
                    fecha,
                    hora,
                    cargoPorcentual,
                    venueSeleccionado,
                    organizadorActual
            );
        }
        else if (opcionTipo == 4) {
            nuevoEvento = new EventoReligioso(
                    nombre,
                    fecha,
                    hora,
                    cargoPorcentual,
                    venueSeleccionado,
                    organizadorActual
            );
        }

        if (nuevoEvento == null) {
            System.out.println("No se pudo crear el evento. Tipo no reconocido.\n");
            return;
        }

        configurarLocalidades(nuevoEvento);

        datos.agregarEvento(nuevoEvento);

        System.out.println("\nEvento creado exitosamente.");
        System.out.println("Nombre: " + nuevoEvento.getNombre());
        System.out.println("Tipo: " + nuevoEvento.getTipoEvento());
        System.out.println("Fecha: " + nuevoEvento.getFecha());
        System.out.println("Hora: " + nuevoEvento.getHora());
        System.out.println("Venue: " + nuevoEvento.getVenue().getNombre());
        System.out.println("Organizador/Promotor: " + nuevoEvento.getOrganizador().getLogin());
        System.out.println("Cantidad total de tiquetes: " + nuevoEvento.getCantidadBoletas() + "\n");
    }

    
    
    private void configurarLocalidades(Evento evento) {

        boolean alMenosUnaLocalidad = false;
        boolean salir = false;

        while (!salir) {

            String[] opcionesLocalidades;

            if (!alMenosUnaLocalidad) {
                opcionesLocalidades = new String[] {
                    "Crear localidad"
                };
            } else {
                opcionesLocalidades = new String[] {
                    "Crear otra localidad",
                    "Terminar configuración de localidades"
                };
            }

            int opcion = mostrarMenu("CONFIGURACIÓN DE LOCALIDADES PARA EL EVENTO", opcionesLocalidades);

            if (!alMenosUnaLocalidad) {
                if (opcion == 1) {
                    crearLocalidadParaEvento(evento);
                    alMenosUnaLocalidad = true;
                }
            } else {
                if (opcion == 1) {
                    crearLocalidadParaEvento(evento);
                } else if (opcion == 2) {
                    salir = true;
                }
            }
        }
    }
    
    
    
    
    
    private void crearLocalidadParaEvento(Evento evento) {

        System.out.println("\n--- CREAR LOCALIDAD PARA EL EVENTO: " + evento.getNombre() + " ---\n");

        String nombreLocalidad = pedirCadenaAlUsuario("Ingresa el nombre de la localidad");

        while (nombreLocalidad == null || nombreLocalidad.trim().isEmpty()) {
            System.out.println("El nombre de la localidad no puede estar vacío.\n");
            nombreLocalidad = pedirCadenaAlUsuario("Ingresa el nombre de la localidad");
        }

        if (evento.getLocalidades().containsKey(nombreLocalidad)) {
            System.out.println("Ya existe una localidad con ese nombre en este evento. Intenta con otro nombre.\n");
            return;
        }

        
        String[] opcionesTipoLocalidad = new String[] {
            "Tiquetes individuales generales (no numerados)",
            "Tiquetes individuales numerados (asientos)",
            "Tiquetes múltiples (palcos, combos)"
        };

        int opcionTipoLoc = mostrarMenu("Selecciona el tipo de tiquetes para esta localidad", opcionesTipoLocalidad);


        double precio = pedirNumeroAlUsuario("Ingresa el precio por tiquete para esta localidad");

        while (precio <= 0) {
            System.out.println("El precio debe ser un número positivo.\n");
            precio = pedirNumeroAlUsuario("Ingresa el precio por tiquete para esta localidad");
        }


        int cantidadTiquetes = pedirEnteroAlUsuario("Ingresa el número de tiquetes que se venderán en esta localidad");

        while (cantidadTiquetes <= 0) {
            System.out.println("La cantidad de tiquetes debe ser un entero positivo.\n");
            cantidadTiquetes = pedirEnteroAlUsuario("Ingresa el número de tiquetes que se venderán en esta localidad");
        }

        Localidad<?> nuevaLocalidad = null;

        if (opcionTipoLoc == 1) {

            Localidad<TiqueteIndividual> locGeneral =
                    new Localidad<>(nombreLocalidad, false, precio, evento);
            nuevaLocalidad = locGeneral;
        }
        else if (opcionTipoLoc == 2) {

            Localidad<TiqueteIndividual> locNumerada =
                    new Localidad<>(nombreLocalidad, true, precio, evento);
            nuevaLocalidad = locNumerada;
        }
        else if (opcionTipoLoc == 3) {

            Localidad<TiqueteMultiple> locMultiple =
                    new Localidad<>(nombreLocalidad, false, precio, evento);
            nuevaLocalidad = locMultiple;
        }

        if (nuevaLocalidad == null) {
            System.out.println("No se pudo crear la localidad. Tipo no reconocido.\n");
            return;
        }

        evento.getLocalidades().put(nombreLocalidad, nuevaLocalidad);


        int totalActual = evento.getCantidadBoletas();
        evento.setCantidadBoletas(totalActual + cantidadTiquetes);

        System.out.println("\nLocalidad creada exitosamente.");
        System.out.println("Evento: " + evento.getNombre());
        System.out.println("Localidad: " + nombreLocalidad);
        System.out.println("Tipo: " + opcionesTipoLocalidad[opcionTipoLoc - 1]);
        System.out.println("Precio por tiquete: " + precio);
        System.out.println("Cantidad de tiquetes en esta localidad: " + cantidadTiquetes);
        System.out.println("Cantidad total de tiquetes del evento ahora es: " + evento.getCantidadBoletas() + "\n");
    }


    
    
    
    
    private void sugerirVenue() {

        System.out.println("\n--- SUGERIR NUEVO VENUE ---\n");

        String nombreVenue = pedirCadenaAlUsuario("Ingresa el nombre del venue que deseas sugerir");

        while (nombreVenue == null || nombreVenue.trim().isEmpty()) {
            System.out.println("El nombre del venue no puede estar vacío.\n");
            nombreVenue = pedirCadenaAlUsuario("Ingresa el nombre del venue que deseas sugerir");
        }

        System.out.println("\nTu sugerencia de venue \"" + nombreVenue + "\" ha sido enviada al administrador.");
        System.out.println("El administrador se encargará de revisarla y decidir si la aprueba o no.\n");
    }
    
    
    

    public static void main( String[] args )
    {
        Datos datos = ConsolaCliente.crearDummyDatos();

        ConsolaOrganizador c = new ConsolaOrganizador(datos);
        c.mostrarMenuModoActuacion();
    }
}
