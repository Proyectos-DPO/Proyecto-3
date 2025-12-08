package peticiones;
import tiquetes.Tiquete;
import usuarios.Cliente;


public class PeticionRembolsoTiquete extends Peticion{

	private Tiquete tiqueteACancelar;

	public PeticionRembolsoTiquete(String id, Cliente cliente, Tiquete tiqueteACancelar) {
		super(id, cliente);
		this.tiqueteACancelar = tiqueteACancelar;
	}
	
	@Override
	protected void setTipoPeticion() {
		this.tipoPeticion = "reembolsarTiquete";
	}
	
	
	public Tiquete getTiqueteACancelar() {
		return tiqueteACancelar;
	}

	@Override
	public String getIdAsociado() {
		return tiqueteACancelar.getId();
	}
	
	
}
