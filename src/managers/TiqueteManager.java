package managers;

import java.time.LocalDate;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import data.Datos;
import tiquetes.Tiquete;
import tiquetes.TiqueteIndividual;

public class TiqueteManager {
	
	public static final int width = 250;
	public static final int height = 250;

	public static void fijarCuotaEmision(double nuevaCuota) {
		Tiquete.setCuotaEmision(nuevaCuota);
	}
	
	public static BitMatrix generarImagenTiquete(Datos datos, TiqueteIndividual tiquete) throws WriterException {
		
		if (!tiquete.isImpreso()) {
			tiquete.setFechaExpedicion(LocalDate.now());
			tiquete.setImpreso(true);
		}
		
		String texto = "Evento: "+tiquete.getLocalidadTiquete().getEvento().getNombre()+"\n"+
				"ID: "+tiquete.getId()+"\n"+
				"Fecha Evento: "+tiquete.getLocalidadTiquete().getEvento().getFecha()+"\n"+
				"Fecha Expedición: "+tiquete.getFechaExpedicion();
		
		return new MultiFormatWriter().encode(texto, BarcodeFormat.QR_CODE, width, height);
	}
	
}
