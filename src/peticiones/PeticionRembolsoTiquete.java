package peticiones;
import tiquetes.Tiquete;


public class PeticionRembolsoTiquete extends Peticion{

	private Tiquete tiqueteACancelar;

	public PeticionRembolsoTiquete(String id, Tiquete tiqueteACancelar) {
		super(id);
		this.tiqueteACancelar = tiqueteACancelar;
	}
	
	@Override
	protected void setTipoPeticion() {
		this.tipoPeticion = "reembolsarTiquete";
	}
	
	
	public Tiquete getTiqueteACancelar() {
		return tiqueteACancelar;
	}

}
