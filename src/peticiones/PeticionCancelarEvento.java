package peticiones;
import eventos.Evento;


public class PeticionCancelarEvento extends Peticion{

	private Evento eventoACancelar;

	public PeticionCancelarEvento(String id, Evento eventoACancelar) {
		super(id);
		this.eventoACancelar = eventoACancelar;
	}
	
	@Override
	protected void setTipoPeticion() {
		this.tipoPeticion = "cancelarEvento";
	}
	
	public Evento getEventoACancelar() {
		return eventoACancelar;
	}
}
