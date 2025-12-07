package consolas;

import java.util.ArrayList;

import data.Datos;
import eventos.Evento;
import eventos.Venue;
import managers.EventoManager;
import managers.PeticionManager;
import sesion.SesionAdmin;
import peticiones.Peticion;
import peticiones.PeticionNuevoVenue;
import usuarios.Admin;
import usuarios.Usuario;

public class ConsolaAdmin extends ConsolaBasica {

    private Datos datos;
    private Admin adminActual;
    private SesionAdmin sesionAdmin;


    private final String[] opcionesAccesoAdmin = new String[] {
        "¿Ya tienes cuenta de administrador? ¡Inicia sesión!",
        "¿No tienes cuenta de administrador? ¡Crea una aquí!",
        "Salir"
    };


    private final String[] opcionesMenuPrincipalAdmin = new String[] {
        "Aprobar / rechazar venues sugeridos",
        "Cancelar evento (de oficio)",
        "Crear venue directamente",
        "Salir"
    };

    public ConsolaAdmin(Datos datos) {
        this.datos = datos;
        this.adminActual = null;
        this.sesionAdmin = null;
    }


    public void mostrarMenuAccesoAdmin() {

        int opcion = mostrarMenu("ACCESO ADMINISTRADOR", opcionesAccesoAdmin);

        if (opcion == 1) {
            iniciarSesionAdmin();
        }
        else if (opcion == 2) {
            crearAdmin();
        }
        else if (opcion == 3) {
            System.out.println("Saliendo...");
            System.exit(0);
        }
    }


    private void iniciarSesionAdmin() {

        String login = pedirCadenaAlUsuario("Ingresa tu login de administrador");
        String contrasena = pedirCadenaAlUsuario("Ingresa tu contraseña");

        Usuario usuarioEncontrado = datos.getUsuario(login);

        if (usuarioEncontrado == null) {
            System.out.println("No existe un usuario con ese login. Intenta de nuevo.\n");
            iniciarSesionAdmin();
            return;
        }

        if (!(usuarioEncontrado instanceof Admin)) {
            System.out.println("El usuario existe, pero no es una cuenta de administrador.\n");
            mostrarMenuAccesoAdmin();
            return;
        }

        if (!usuarioEncontrado.getContrasena().equals(contrasena)) {
            System.out.println("Contraseña incorrecta. Intenta de nuevo.\n");
            iniciarSesionAdmin();
            return;
        }

        adminActual = (Admin) usuarioEncontrado;
        sesionAdmin = new SesionAdmin(adminActual.getLogin());

        System.out.println("\n✅ Inicio de sesión exitoso como administrador: " + adminActual.getLogin() + "\n");

        mostrarMenuPrincipalAdmin();
    }

    private void crearAdmin() {

        System.out.println("\n--- CREAR NUEVA CUENTA DE ADMINISTRADOR ---\n");


        String login;
        while (true) {
            login = pedirCadenaAlUsuario("Ingresa el login/Username que quieres usar como administrador");

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

        Admin nuevoAdmin = new Admin(login, contrasena); 
        datos.agregarUsuario(nuevoAdmin);

        adminActual = nuevoAdmin;
        sesionAdmin = new SesionAdmin(adminActual.getLogin());

        System.out.println("\nCuenta de administrador creada exitosamente. Sesión iniciada como: "
                           + adminActual.getLogin() + "\n");

        mostrarMenuPrincipalAdmin();
    }

    private void mostrarMenuPrincipalAdmin() {

        int opcion = mostrarMenu("MENÚ PRINCIPAL ADMINISTRADOR", opcionesMenuPrincipalAdmin);

        if (opcion == 1) {

            administrarPeticionesVenues();
            mostrarMenuPrincipalAdmin();
        }
        else if (opcion == 2) {

            cancelarEventoDeOficio();
            mostrarMenuPrincipalAdmin();
        }
        else if (opcion == 3) {

            crearVenueDirectamente();
            mostrarMenuPrincipalAdmin();
        }
        else if (opcion == 4) {
            System.out.println("Cerrando sesión de administrador...");
            adminActual = null;
            sesionAdmin = null;
            mostrarMenuAccesoAdmin();
        }
    }

 
    private void administrarPeticionesVenues() {

        ArrayList<Peticion> peticiones = datos.getPeticionesAsList();
        ArrayList<PeticionNuevoVenue> peticionesVenue = new ArrayList<>();


        for (Peticion p : peticiones) {
            if ("creacionVenue".equals(p.getTipoPeticion())) {
                peticionesVenue.add((PeticionNuevoVenue) p);
            }
        }

        if (peticionesVenue.isEmpty()) {
            System.out.println("\nNo hay peticiones de nuevos venues pendientes.\n");
            return;
        }

        System.out.println("\n--- PETICIONES DE NUEVOS VENUES ---\n");

        for (PeticionNuevoVenue peticion : peticionesVenue) {

            String id = peticion.getId();
            Venue venueCandidato = peticion.getVenueCandidato();

            System.out.println("ID Petición: " + id);
            System.out.println("Nombre venue propuesto: " + venueCandidato.getNombre());
            System.out.println("Latitud: " + venueCandidato.getLatitud());
            System.out.println("Longitud: " + venueCandidato.getLongitud());
            System.out.println("Capacidad: " + venueCandidato.getCapacidad());
            System.out.println("Restricciones: " + venueCandidato.getRestricciones());
            System.out.println("Aprobado actualmente: " + venueCandidato.isAprobado());
            System.out.println();

            boolean decision = pedirConfirmacionAlUsuario(
                    "¿Desea APROBAR esta solicitud de venue? (si = aprobar, no = rechazar)"
            );

            PeticionManager.ejecutarDecision(id, datos, decision);

            if (decision) {
                System.out.println("Venue \"" + venueCandidato.getNombre() + "\" ha sido APROBADO.\n");
            } else {
                System.out.println("Venue \"" + venueCandidato.getNombre() + "\" ha sido RECHAZADO y eliminado.\n");
            }
        }
    }


    private void cancelarEventoDeOficio() {

        ArrayList<Evento> listaEventos = datos.getEventosAsList();

        if (listaEventos.isEmpty()) {
            System.out.println("\nNo hay eventos registrados en el sistema.\n");
            return;
        }

        String[] opcionesEventos = new String[listaEventos.size() + 1];

        for (int i = 0; i < listaEventos.size(); i++) {
            Evento e = listaEventos.get(i);
            String venueName = (e.getVenue() != null) ? e.getVenue().getNombre() : "Sin venue";
            opcionesEventos[i] = e.getNombre() + " | " + e.getFecha() + " " + e.getHora() + " | " + venueName;
        }
        opcionesEventos[listaEventos.size()] = "Volver";

        int opcion = mostrarMenu("SELECCIONE EL EVENTO A CANCELAR", opcionesEventos);

        if (opcion == opcionesEventos.length) {

            return;
        }

        Evento eventoSeleccionado = listaEventos.get(opcion - 1);

        System.out.println("\nHas seleccionado el evento:");
        System.out.println("Nombre: " + eventoSeleccionado.getNombre());
        System.out.println("Fecha: " + eventoSeleccionado.getFecha());
        System.out.println("Hora: " + eventoSeleccionado.getHora());
        System.out.println("Venue: " + (eventoSeleccionado.getVenue() != null ? eventoSeleccionado.getVenue().getNombre() : "Sin venue"));
        System.out.println();

        boolean confirmar = pedirConfirmacionAlUsuario(
                "¿Estás seguro de que deseas CANCELAR este evento? Esta acción no se puede deshacer."
        );

        if (!confirmar) {
            System.out.println("\nOperación cancelada. El evento NO fue cancelado.\n");
            return;
        }

        EventoManager.cancelarEventoAdmin(datos, eventoSeleccionado);

        System.out.println("\nEl evento \"" + eventoSeleccionado.getNombre() + "\" ha sido cancelado por el administrador.\n");
    }

    private void crearVenueDirectamente() {

        if (sesionAdmin == null) {
            System.out.println("Debes iniciar sesión como administrador antes de crear venues.\n");
            mostrarMenuAccesoAdmin();
            return;
        }

        System.out.println("\n--- CREAR NUEVO VENUE (ADMIN) ---\n");

        String nombre = pedirCadenaAlUsuario("Ingresa el nombre del venue");

        while (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre del venue no puede estar vacío.\n");
            nombre = pedirCadenaAlUsuario("Ingresa el nombre del venue");
        }

        double latitud = pedirNumeroAlUsuario("Ingresa la latitud del venue (por ejemplo 4.6486)");
        double longitud = pedirNumeroAlUsuario("Ingresa la longitud del venue (por ejemplo -74.0789)");

        int capacidad = pedirEnteroAlUsuario("Ingresa la capacidad máxima del venue (entero positivo)");
        while (capacidad <= 0) {
            System.out.println("La capacidad debe ser un entero positivo.\n");
            capacidad = pedirEnteroAlUsuario("Ingresa la capacidad máxima del venue (entero positivo)");
        }

        String restricciones = pedirCadenaAlUsuario("Ingresa las restricciones del venue (por ejemplo: 'No ingreso de armas')");

        try {

            sesionAdmin.crearVenue(datos, nombre, latitud, longitud, capacidad, restricciones);
            System.out.println("\nVenue \"" + nombre + "\" creado y APROBADO exitosamente.\n");
        }
        catch (Exception e) {
            System.out.println("\nNo se pudo crear el venue: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {

        Datos datos = ConsolaCliente.crearDummyDatos();

        ConsolaAdmin consolaAdmin = new ConsolaAdmin(datos);
        consolaAdmin.mostrarMenuAccesoAdmin();
    }
}
