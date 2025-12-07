package consolas;

import data.Datos;
import eventos.Evento;
import eventos.Localidad;
import usuarios.Usuario;

import java.util.ArrayList;

import consolas.ConsolaCliente;
import consolas.ConsolaBasica;

public class ConsolaEspecificaCliente extends ConsolaBasica {
    
    private Datos datos;
    private ArrayList<Evento> listaEventos;
    private String[] opcionesCatalogoEventos;
    
    private ArrayList<Localidad<?>> listaLocalidades;
    private String[] opcionesCatalogoLocalidades;

    public ConsolaEspecificaCliente(Datos datos) {
        this.datos = datos;

        listaEventos = datos.getEventosAsList();

        opcionesCatalogoEventos = new String[ listaEventos.size() ];
        for (int i = 0; i < listaEventos.size(); i++) {
            opcionesCatalogoEventos[i] = listaEventos.get(i).getNombre();
        }
        
        
        
        
    }
    
    public void mostrarMenuPrincipalEspecifico( ) {
    	
    	int tamanoListaEventos = listaEventos.size();
        
        int opcionSeleccionada = mostrarMenu( "CATALOGO DE EVENTOS", opcionesCatalogoEventos );
        
        if( opcionSeleccionada <= tamanoListaEventos )
        {
        	Evento eventoSeleccionado = listaEventos.get(opcionSeleccionada-1);
        	
        	mostrarInformacionEvento(eventoSeleccionado);
        }
    }
    
    
    
    private void mostrarInformacionEvento(Evento evento) {
    	System.out.println( "\n---------------------" );
        System.out.println( "INFORMACIÓN DEL EVENTO" );
        System.out.println( "---------------------" );
        System.out.println("Nombre: " + evento.getNombre());
        System.out.println("Tipo: " + evento.getTipoEvento());
        System.out.println("Fecha: " + evento.getFecha());
        System.out.println("Hora: " + evento.getHora());
        System.out.println("Venue: " + (evento.getVenue()).getNombre()+ "\n");
        mostrarMenuLocalidades(evento);
        
    }
    
    
    private void mostrarMenuLocalidades(Evento evento) {
    	
    	listaLocalidades = evento.getLocalidadesAsList();

        opcionesCatalogoLocalidades = new String[ listaLocalidades.size() ];
        for (int i = 0; i < listaLocalidades.size(); i++) {
            opcionesCatalogoLocalidades[i] = listaLocalidades.get(i).getNombre();
        }
    	
        int tamanoListaLocalidades = listaLocalidades.size();
        
        int opcionSeleccionada = mostrarMenu( "LOCALIDADES DEL EVENTO", opcionesCatalogoLocalidades );
        
        if( opcionSeleccionada <= tamanoListaLocalidades )
        {
        	Localidad<?> localidadSeleccionada = listaLocalidades.get(opcionSeleccionada-1);
        	
        	mostrarInformacionLocalidad(localidadSeleccionada);
        }
        
        
        
    	
    }

	private void mostrarInformacionLocalidad(Localidad<?> localidadSeleccionada) {
		
		System.out.println( "\n---------------------" );
        System.out.println( "INFORMACIÓN DE LA LOCALIDAD" );
        System.out.println( "---------------------" );
        System.out.println("Nombre: " + localidadSeleccionada.getNombre());
        System.out.println("Precio Tiquetes: " + localidadSeleccionada.getPrecioBoletas());
        procesarCompra(localidadSeleccionada);
		
	}
	
	
	private void procesarCompra(Localidad<?> localidadSeleccionada) {
	    
	    System.out.println();
	    System.out.println("¿Cuántos tiquetes de la localidad '" + localidadSeleccionada.getNombre() + "' deseas comprar?");
	    
	    int cantidad = pedirEnteroAlUsuario("Ingrese la cantidad de tiquetes");

	    
	    if (cantidad <= 0) {
	        System.out.println("La cantidad debe ser un número entero positivo.\n");
	        
	        procesarCompra(localidadSeleccionada);
	        return;
	    }

	    System.out.println("\n-----------------------------------");
	    System.out.println("Gracias por tu compra de " + cantidad + " tiquete(s)");
	    System.out.println("Localidad: " + localidadSeleccionada.getNombre());
	    System.out.println("Precio por tiquete: " + localidadSeleccionada.getPrecioBoletas());
	    System.out.println("Total (sin cargos adicionales): " + (cantidad * localidadSeleccionada.getPrecioBoletas()));
	    System.out.println("-----------------------------------\n");
	}
    
}
