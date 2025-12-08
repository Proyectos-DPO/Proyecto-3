package peticiones;
import eventos.Venue;
import usuarios.Cliente;


public class PeticionNuevoVenue extends Peticion{

	private Venue venueCandidato;

	public PeticionNuevoVenue(String id, Cliente cliente, Venue venueCandidato) {
		super(id, cliente);
		this.venueCandidato = venueCandidato;
	}
	
	@Override
	protected void setTipoPeticion() {
		this.tipoPeticion = "creacionVenue";
	}
	
	public Venue getVenueCandidato() {
		return venueCandidato;
	}

	@Override
	public String getIdAsociado() {
		return venueCandidato.getNombre();
	}
	
}
