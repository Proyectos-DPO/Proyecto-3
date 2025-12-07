package usuarios;

import java.time.LocalDate;
import java.util.TreeMap;

public class GananciasBoleteria {

	private double generales;
	private TreeMap <LocalDate, Double> porFecha;
	private TreeMap<String, Double> porEvento;
	private TreeMap<String, Double> porOrganizador;
	
	public GananciasBoleteria(double generales, TreeMap<LocalDate, Double> porFecha, TreeMap<String, Double> porEvento,
			TreeMap<String, Double> porOrganizador) {
		super();
		this.generales = generales;
		this.porFecha = porFecha;
		this.porEvento = porEvento;
		this.porOrganizador = porOrganizador;
	}
	
	public double getGenerales() {
		return generales;
	}
	public void setGenerales(double generales) {
		this.generales = generales;
	}
	public TreeMap<LocalDate, Double> getPorFecha() {
		return porFecha;
	}
	public void setPorFecha(TreeMap<LocalDate, Double> porFecha) {
		this.porFecha = porFecha;
	}
	public TreeMap<String, Double> getPorEvento() {
		return porEvento;
	}
	public void setPorEvento(TreeMap<String, Double> porEvento) {
		this.porEvento = porEvento;
	}
	public TreeMap<String, Double> getPorOrganizador() {
		return porOrganizador;
	}
	public void setPorOrganizador(TreeMap<String, Double> porOrganizador) {
		this.porOrganizador = porOrganizador;
	}
	
	
}
