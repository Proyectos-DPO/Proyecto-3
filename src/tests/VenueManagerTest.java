package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import data.Datos;
import managers.VenueManager;
import persistencia.Persistencia;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VenueManagerTest {
	
	private Datos datos;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		datos = Persistencia.cargarDatos();
	}

	@Test
	@Order(1)
	void evitarDuplicados() {
		
		
		try {
			VenueManager.crearNuevaVenue(datos, "La Casa de Sara", 0, 0, 0, "asdfa", true);
			fail("Debería haber evitado la formación del duplicado");
			
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Ya existe una venue con el mismo nombre!", "No identificó el error correcto");
		}
	}
	
	@Test
	@Order(2)
	void eliminacionVenues() {
		VenueManager.eliminarVenue(datos, datos.getVenue("La Casa de Sara"));
		assertTrue(datos.getVenue("La casa de Sara") == null);
	}
	
	@Test
	@Order(3)
	void cambioEstadoVenues() {
		VenueManager.cambiarEstadoAprobado(datos.getVenue("La Casa de Sara"));
		assertTrue(datos.getVenue("La Casa de Sara").isAprobado());
	}

}
