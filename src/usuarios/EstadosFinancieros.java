package usuarios;

public class EstadosFinancieros {
	
	private double ganacias;
	private int boletasTotales;
	private int boletasVendidas;
	
	
	public EstadosFinancieros() {
		this.ganacias = 0;
		this.boletasTotales = 0;
		this.boletasVendidas = 0;
	}
	
	
	//Persistiendo como el persistidor
	public EstadosFinancieros(double ganacias, int boletasTotales, int boletasVendidas) {
		super();
		this.ganacias = ganacias;
		this.boletasTotales = boletasTotales;
		this.boletasVendidas = boletasVendidas;
	}
	

	public double getGanacias() {
		return ganacias;
	}

	public void setGanacias(double ganacias) {
		this.ganacias = ganacias;
	}


	public int getBoletasTotales() {
		return boletasTotales;
	}


	public void setBoletasTotales(int boletasTotales) {
		this.boletasTotales = boletasTotales;
	}


	public int getBoletasVendidas() {
		return boletasVendidas;
	}


	public void setBoletasVendidas(int boletasVendidas) {
		this.boletasVendidas = boletasVendidas;
	}
	
	
	
}
