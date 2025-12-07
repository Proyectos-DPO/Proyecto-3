package eventos;
import java.time.LocalDate;

public class Descuento {
	
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	private double descuentoPorcentaje;
	
	public Descuento(LocalDate fechaInicio, LocalDate fechaFinal, double descuentoPorcentaje) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}
	
	

}
