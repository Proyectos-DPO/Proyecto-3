package peticiones;
import eventos.Venue;


public class PeticionNuevoVenue extends Peticion{

	private Venue venueCandidato;

	public PeticionNuevoVenue(String id, Venue venueCandidato) {
		super(id);
		this.venueCandidato = venueCandidato;
	}
	
	@Override
	protected void setTipoPeticion() {
		this.tipoPeticion = "creacionVenue";
	}
	
	public Venue getVenueCandidato() {
		return venueCandidato;
	}
}
