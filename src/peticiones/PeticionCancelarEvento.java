package peticiones;
import eventos.Evento;
import usuarios.Cliente;


public class PeticionCancelarEvento extends Peticion{

	private Evento eventoACancelar;

	public PeticionCancelarEvento(String id, Cliente cliente, Evento eventoACancelar) {
		super(id, cliente);
		this.eventoACancelar = eventoACancelar;
	}
	
	@Override
	protected void setTipoPeticion() {
		this.tipoPeticion = "cancelarEvento";
	}
	
	public Evento getEventoACancelar() {
		return eventoACancelar;
	}

	@Override
	public String getIdAsociado() {
		return eventoACancelar.getNombre();
	}
	
	
}
