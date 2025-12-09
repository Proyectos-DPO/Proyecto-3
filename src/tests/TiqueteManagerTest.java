package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import data.Datos;
import managers.TiqueteManager;
import persistencia.Persistencia;
import tiquetes.TiqueteIndividual;

class TiqueteManagerTest {
	
	private Datos datitosUwUwU;
	private TiqueteIndividual tiqueteMegaPro;

	@BeforeEach
	public void setUpBeforeClass() throws Exception {
		
		datitosUwUwU = Persistencia.cargarDatos();
		tiqueteMegaPro = (TiqueteIndividual) datitosUwUwU.getTiquete("A");
	}

	@Test
	void test() {
		try {
			
			BufferedImage img = MatrixToImageWriter.toBufferedImage(TiqueteManager.generarImagenTiquete(datitosUwUwU, tiqueteMegaPro));
			JLabel laImagen = new JLabel(new ImageIcon(img));
			JFrame pruebita = new JFrame();
			pruebita.setLayout(new BorderLayout());
			pruebita.add(laImagen, BorderLayout.CENTER);
			pruebita.setSize(new Dimension(250,250));
			pruebita.setVisible(true);
			Thread.sleep(10000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("lol");
		}
	}

}
