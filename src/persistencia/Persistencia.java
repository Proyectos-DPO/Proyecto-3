package persistencia;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;
import data.Datos;
import eventos.Descuento;
import eventos.Evento;
import eventos.EventoCultural;
import eventos.EventoDeportivo;
import eventos.EventoMusical;
import eventos.EventoReligioso;
import eventos.Localidad;
import eventos.Venue;
import peticiones.Peticion;
import peticiones.PeticionCancelarEvento;
import peticiones.PeticionNuevoVenue;
import peticiones.PeticionRembolsoTiquete;
import tiquetes.PaqueteDeluxe;
import tiquetes.Tiquete;
import tiquetes.TiqueteIndividual;
import tiquetes.TiqueteMultiple;
import usuarios.Admin;
import usuarios.Cliente;
import usuarios.EstadosFinancieros;
import usuarios.GananciasBoleteria;
import usuarios.Notificacion;
import usuarios.Organizador;
import usuarios.Usuario;


public class Persistencia {
	
	public static Datos cargarDatos() throws Exception { 
		Datos datitosUwU = new Datos();
		datitosUwU.setVenues(cargarVenues(new JSONArray(Files.readString(Paths.get("info/venues.json")))));
		datitosUwU.setTiquetes(cargarTiquetesIndividuales(new JSONArray(Files.readString(Paths.get("info/tiquetes_i.json")))));
		datitosUwU.setTiquetesMultiples(cargarTiquetesMultiples(new JSONArray(Files.readString(Paths.get("info/tiquetes_m.json"))), datitosUwU));
		datitosUwU.setPaquetes(cargarPaquetes(new JSONArray(Files.readString(Paths.get("info/paquetes.json"))), datitosUwU ));
		datitosUwU.setUsuarios(cargarUsuarios(new JSONArray(Files.readString(Paths.get("info/usuarios.json"))),datitosUwU));
		datitosUwU.setEventos(cargarEventos(new JSONArray(Files.readString(Paths.get("info/eventos.json"))),datitosUwU));
		datitosUwU.setPeticiones(cargarPeticiones(new JSONArray(Files.readString(Paths.get("info/peticiones.json"))),datitosUwU));
		cargarGananciasBoleteria(new JSONObject(Files.readString(Paths.get("info/gananciasBoleteria.json"))));
		cargarPorcentajeEventos(new JSONObject(Files.readString(Paths.get("info/porcentajeEventos.json"))));
		return datitosUwU;
	}
	
	public static void guardarDatos(Datos datitosUwU) {
		
	}
	
	private static TreeMap<String, Venue> cargarVenues(JSONArray venuesData){
		TreeMap<String, Venue> mapaVenues = new TreeMap<String, Venue>();
		for(Object o: venuesData) {
			JSONObject v = (JSONObject) o;
			JSONArray dispo = v.getJSONArray("disponibilidad");
			HashSet<LocalDate> disponibilidad = new HashSet<LocalDate>();
			for (Object o2: dispo) {
				JSONArray fechaD = (JSONArray) o2 ;
				LocalDate fecha = LocalDate.of(fechaD.getInt(0),fechaD.getInt(1),fechaD.getInt(2) );
				disponibilidad.add(fecha);
			}
			mapaVenues.put(v.getString("nombre"), new Venue(v.getString("nombre"), v.getDouble("latitud"), v.getDouble("longitud"), v.getInt("capacidad"), v.getString("restricciones"), disponibilidad, v.getBoolean("aprobado")));
			
		}
		return mapaVenues;
	}
	
	///private static JSONObject guardarVenues(TreeMap<String, Venue> mapaVenues) {}
	
		
	private static TreeMap<String, Tiquete> cargarTiquetesIndividuales(JSONArray tiquetesIData){
		TreeMap<String, Tiquete> mapaTiquetes = new TreeMap<String, Tiquete>();
		
		for(Object o: tiquetesIData) {
			JSONObject p = (JSONObject) o;
			JSONArray fechaExpedicion = p.getJSONArray("fechaExpedicion");
			LocalDate fechaTrue = null;
			if (fechaExpedicion.length() > 0) {
				fechaTrue = LocalDate.of(fechaExpedicion.getInt(0),fechaExpedicion.getInt(1),fechaExpedicion.getInt(2));
			}
			TiqueteIndividual tiqueteI = new TiqueteIndividual(p.getString("id"), p.getDouble("precioVenta"), p.getDouble("pagoCargo"), p.getBoolean("isContained"), p.getInt("idAsiento"), p.getBoolean("impreso"), fechaTrue);
			mapaTiquetes.put(tiqueteI.getId(), tiqueteI);
		}
		
		return mapaTiquetes;
		
	}
	
	private static TreeMap<String, TiqueteMultiple> cargarTiquetesMultiples(JSONArray tiquetesMData, Datos datitosUwU){
		TreeMap<String, TiqueteMultiple> mapaTiquetesM = new TreeMap<String, TiqueteMultiple>();
		for (Object o: tiquetesMData) {
			JSONObject m = (JSONObject)o;
			TreeMap<String, TiqueteIndividual> listica = new TreeMap<String, TiqueteIndividual>();
			for (Object o2: m.getJSONArray("tiquetes")) {
				String ti = (String) o2;
				listica.put(ti, (TiqueteIndividual) datitosUwU.getTiquete(ti));
				
			}
			TiqueteMultiple tiqueteM = new TiqueteMultiple(m.getString("id"), m.getDouble("precioVenta"), m.getDouble("pagoCargo"), m.getBoolean("isContained"),m.getBoolean("isCompleto"), listica, m.getBoolean("isInVariosEventos"));
			if (m.getBoolean("isInVariosEventos")) {
				mapaTiquetesM.put(tiqueteM.getId(), tiqueteM);
			} else {
				datitosUwU.getTiquetes().put(tiqueteM.getId(), tiqueteM);
			}
		}
		return mapaTiquetesM;
	}
	
	private static TreeMap<String, PaqueteDeluxe> cargarPaquetes(JSONArray datosPaquetes, Datos datitosUwU){
		TreeMap<String, PaqueteDeluxe> mapaPaquetes = new TreeMap<String, PaqueteDeluxe>();
		for (Object o: datosPaquetes) {
			JSONObject p = (JSONObject) o;
			TreeMap<String, Tiquete> tiquetes = new TreeMap<String, Tiquete>();
			for (Object o2: p.getJSONArray("tiquetes")) {
				String idT = (String) o2;
				tiquetes.put(idT, datitosUwU.getTiquete(idT));
			}
			PaqueteDeluxe paquetito = new PaqueteDeluxe(p.getString("id"), p.getDouble("precio"), p.getString("mercancia"), p.getString("beneficios"), tiquetes);
			mapaPaquetes.put(paquetito.getId(), paquetito);
		}
		
		return mapaPaquetes;
	}
	
	
	private static TreeMap<String, Usuario> cargarUsuarios(JSONArray usuariosData, Datos datitosUwU){
		TreeMap<String, Usuario> mapaUsuarios = new TreeMap<String, Usuario>();
		for (Object o: usuariosData) {
			JSONObject usu = (JSONObject) o;
			Usuario usuario;
			String tipo = usu.getString("type");
			if (tipo.equals("C") || tipo.equals("CO")) {
				JSONArray tis = usu.getJSONArray("tiquetes");
				TreeMap<String,Tiquete> mapaTiquetes = new TreeMap<String,Tiquete>();
				
				for (Object o2: tis) {
					String idTiquete = (String) o2;
					Tiquete tiqueteDeVeras = null;
					if (datitosUwU.getTiquetes().containsKey(idTiquete)) {
					tiqueteDeVeras = datitosUwU.getTiquete(idTiquete);
					} else {
					tiqueteDeVeras = datitosUwU.getTiqueteMultiple(idTiquete);
					}
					mapaTiquetes.put(idTiquete, tiqueteDeVeras);
				}
				JSONArray packs = usu.getJSONArray("paquetes");
				TreeMap<String,PaqueteDeluxe> mapaPaquetes = new TreeMap<String,PaqueteDeluxe>();
				for (Object o3: packs) {
					String idPaquete = (String) o3;
					PaqueteDeluxe paqueteDeVeras = datitosUwU.getPaqueteDeluxe(idPaquete);
					mapaPaquetes.put(idPaquete, paqueteDeVeras);
				}
				JSONArray msjs = usu.getJSONArray("notificaciones");
				ArrayList<Notificacion> listaNotificaciones = new ArrayList<Notificacion>();
				for (Object o4: msjs) {
					String mensaje = (String) o4;
					Notificacion noti = new Notificacion(mensaje);
					listaNotificaciones.add(noti);
				}
				if (tipo.contentEquals("CO")) {
					JSONObject estadosF = usu.getJSONObject("estados");
					EstadosFinancieros estadosReales = new EstadosFinancieros(estadosF.getDouble("ganancias"), estadosF.getInt("boletasTotales"), estadosF.getInt("boletasVendidas"));
					usuario = new Organizador(usu.getString("login"), usu.getString("contrasena"), usu.getDouble("saldo"), usu.getInt("cantidadPeticiones"), mapaTiquetes, mapaPaquetes, listaNotificaciones, estadosReales);
				} else {
					usuario = new Cliente(usu.getString("login"), usu.getString("contrasena"), usu.getDouble("saldo"), usu.getInt("cantidadPeticiones"), mapaTiquetes, mapaPaquetes, listaNotificaciones);
				}
				for(Tiquete tiquetosoto:mapaTiquetes.values()) {
					tiquetosoto.setPropietario((Cliente) usuario);
				}
				for (PaqueteDeluxe paquetoso: mapaPaquetes.values()) {
					paquetoso.setPropietario((Cliente)usuario);
				}
				
				
			} else {
				usuario = new Admin(usu.getString("login"), usu.getString("contrasena"));	 
			}
			
			mapaUsuarios.put(usuario.getLogin(), usuario);
		}
		return mapaUsuarios;
	}
	
	private static TreeMap<String, Evento> cargarEventos(JSONArray eventosData, Datos datitosUwU){
		TreeMap<String, Evento> mapaEventos = new TreeMap<String, Evento> ();
		for(Object o: eventosData) {
			JSONObject e = (JSONObject) o;
			JSONArray fechitaDatos = e.getJSONArray("fecha");
			LocalDate fechita = LocalDate.of(fechitaDatos.getInt(0), fechitaDatos.getInt(1), fechitaDatos.getInt(2));
			JSONArray horitaDatos = e.getJSONArray("hora");
			LocalTime horita = LocalTime.of(horitaDatos.getInt(0), horitaDatos.getInt(1));
			TreeMap<String, Localidad<?>> mapaLocalidades = new TreeMap<String, Localidad<?>>();
			for (Object o2: e.getJSONArray("localidades")) {
				JSONObject loc = (JSONObject) o2;
				boolean isMult = loc.getBoolean("isMultiple");
				Localidad<?> localidad;
				JSONObject estadosFL = loc.getJSONObject("estados");
				EstadosFinancieros estadosRealesLoc = new EstadosFinancieros(estadosFL.getDouble("ganancias"), estadosFL.getInt("boletasTotales"), estadosFL.getInt("boletasVendidas"));
				if (isMult) {
					TreeMap<String, TiqueteMultiple> tiquetes = new TreeMap<String, TiqueteMultiple>();
					for (Object o3: loc.getJSONArray("tiquetes")) {
						String ti = (String) o3;
						TiqueteMultiple tiqueteM =(TiqueteMultiple) datitosUwU.getTiquete(ti);
						
						tiquetes.put(ti, tiqueteM);
						}
					
					localidad = new Localidad<TiqueteMultiple>(loc.getString("nombre"), loc.getBoolean("isNumerado"), loc.getInt("counter"), loc.getDouble("precioBoletas"), tiquetes, isMult, estadosRealesLoc);
				} else {
						TreeMap<String, TiqueteIndividual> tiquetes = new TreeMap<String, TiqueteIndividual>();
						for (Object o3: loc.getJSONArray("tiquetes")) {
							String ti = (String) o3;
							TiqueteIndividual tiqueteM =(TiqueteIndividual) datitosUwU.getTiquete(ti);
							tiquetes.put(ti, tiqueteM);
					}
					localidad = new Localidad<TiqueteIndividual>(loc.getString("nombre"), loc.getBoolean("isNumerado"), loc.getInt("counter"), loc.getDouble("precioBoletas"), tiquetes, isMult, estadosRealesLoc);
				}
				for(Tiquete tiquetoso: localidad.getTiquetesLocalidad().values()) {
					tiquetoso.setLocalidadTiquete(localidad);
				}
				mapaLocalidades.put(localidad.getNombre(), localidad);
				
			  
			}
			
			//Descuento
			JSONObject desc = e.getJSONObject("descuento");
			Descuento descuento = null;
			if (!desc.toString().equals(new JSONObject().toString())){
				 JSONArray fIni = desc.getJSONArray("fechaInicio");
				 LocalDate fechaInicio = LocalDate.of(fIni.getInt(0), fIni.getInt(1), fIni.getInt(2));
				 JSONArray fFin = desc.getJSONArray("fechaFinal");
				 LocalDate fechaFinal = LocalDate.of(fFin.getInt(0), fFin.getInt(1), fFin.getInt(2));
				 double porcentaje =  desc.getDouble("descuentoPorcentaje");
				 descuento = new Descuento(fechaInicio, fechaFinal, porcentaje);	 
			}
			
			
			//EstadosF
			JSONObject estadosF = e.getJSONObject("estados");
			EstadosFinancieros estadosRealesE = new EstadosFinancieros(estadosF.getDouble("ganancias"), estadosF.getInt("boletasTotales"), estadosF.getInt("boletasVendidas"));
			
			//Organizador
			Organizador organizador = (Organizador) datitosUwU.getUsuario(e.getString("organizador"));
			
			
			Evento eventito = null;
			switch (e.getString("type")) {
			case "Musical":
				eventito = new EventoMusical(e.getString("nombre"), fechita, horita, e.getInt("cantidadBoletas"), mapaLocalidades, datitosUwU.getVenue(e.getString("venue")), descuento, organizador, estadosRealesE );
				break;
			case "Cultural":
				eventito = new EventoCultural(e.getString("nombre"), fechita, horita, e.getInt("cantidadBoletas"), mapaLocalidades, datitosUwU.getVenue(e.getString("venue")), descuento, organizador, estadosRealesE );
				break;
			case "Deportivo":
				eventito = new EventoDeportivo(e.getString("nombre"), fechita, horita, e.getInt("cantidadBoletas"), mapaLocalidades, datitosUwU.getVenue(e.getString("venue")), descuento, organizador, estadosRealesE );
				break;
			case "Religioso":
				eventito = new EventoReligioso(e.getString("nombre"), fechita, horita, e.getInt("cantidadBoletas"), mapaLocalidades, datitosUwU.getVenue(e.getString("venue")), descuento, organizador, estadosRealesE );
				break;
			
			}
			organizador.getEventos().put(eventito.getNombre(), eventito);
			mapaEventos.put(eventito.getNombre(), eventito);
		}
		
		return mapaEventos;
	}	
	
	private static TreeMap<String, Peticion> cargarPeticiones(JSONArray peticionesData, Datos datitosUwU){
		TreeMap<String, Peticion> mapaPeticiones = new TreeMap<String, Peticion>();
		for(Object o: peticionesData) {
			JSONObject p = (JSONObject) o;
			Peticion peticionita = null;
			switch(p.getString("type")){
			case "Cancel":
				peticionita = new PeticionCancelarEvento(p.getString("id"), (Cliente) datitosUwU.getUsuario(p.getString("id").split("-")[0]),datitosUwU.getEvento(p.getString("idAsociado")) );
				break;
			case "Venue":
				peticionita = new PeticionNuevoVenue(p.getString("id"), (Cliente) datitosUwU.getUsuario(p.getString("id").split("-")[0]),datitosUwU.getVenue(p.getString("idAsociado")) );
				break;
			case "Reembolso":
				Tiquete tiquete = null;
				if (datitosUwU.getTiquetes().containsKey(p.getString("idAsociado"))) {
					tiquete = datitosUwU.getTiquete(p.getString("idAsociado"));
				} else {
					tiquete = datitosUwU.getTiqueteMultiple(p.getString("idAsociado"));
				}
				peticionita = new PeticionRembolsoTiquete(p.getString("id"), (Cliente) datitosUwU.getUsuario(p.getString("id").split("-")[0]),tiquete );
				break;
			}
			mapaPeticiones.put(p.getString("id"), peticionita);
		}
		return mapaPeticiones;
		
	}
	
	private static void cargarGananciasBoleteria(JSONObject gan) {
		TreeMap <LocalDate, Double> porFecha = new TreeMap<LocalDate, Double>();
		
		JSONArray pf = gan.getJSONArray("porFecha");
		for (Object o: pf) {
			JSONArray listaDatos1 = (JSONArray) o;
			JSONArray fechaDatos = listaDatos1.getJSONArray(0);
			LocalDate queMamera = LocalDate.of(fechaDatos.getInt(0), fechaDatos.getInt(1), fechaDatos.getInt(2));
			porFecha.put(queMamera, listaDatos1.getDouble(1));
		}
		
		TreeMap<String, Double> porEvento = new TreeMap<String, Double>();
		JSONArray pe = gan.getJSONArray("porEvento");
		for (Object o2: pe) {
			JSONArray listaDatos2 = (JSONArray) o2;
			 porEvento.put(listaDatos2.getString(0), listaDatos2.getDouble(1));
		}
		
		TreeMap<String, Double> porOrganizador = new TreeMap<String, Double>();
		JSONArray po = gan.getJSONArray("porOrganizador");
		for (Object o3: po) {
			JSONArray listaDatos3 = (JSONArray) o3;
			porOrganizador.put(listaDatos3.getString(0), listaDatos3.getDouble(1));
		}
		
		//Hago esto aquí porque no se me ocurre otro lugar
		Tiquete.setCuotaEmision(gan.getDouble("cuotaEmision"));
		
		Admin.setGananciasBoleteria(new GananciasBoleteria(gan.getDouble("generales"), porFecha, porEvento, porOrganizador));
	}
	
	
	private static void cargarPorcentajeEventos(JSONObject gane) {
		EventoMusical.configurarCargoPorcentual(gane.getDouble("eventoMusical"));
		EventoCultural.configurarCargoPorcentual(gane.getDouble("eventoCultural"));
		EventoDeportivo.configurarCargoPorcentual(gane.getDouble("eventoDeportivo"));
		EventoReligioso.configurarCargoPorcentual(gane.getDouble("eventoReligioso"));
	}
	
}
