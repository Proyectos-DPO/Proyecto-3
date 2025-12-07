package interfaz;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class VentanaInicio extends JFrame {
	public VentanaInicio() {
		CardLayout cl = new CardLayout();
		setLayout(cl);
		setSize(new Dimension(600, 300));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaInicio();
		
	}
}
