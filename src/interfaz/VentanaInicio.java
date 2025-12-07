package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaInicio extends JFrame {

	private CardPanel cardPanel;

	
	public VentanaInicio() {
		
		setTitle( "BoletaMaster" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        setResizable(false);
        
		cardPanel = new CardPanel();
		
		cardPanel.add(new LoginView(this), "login");
		cardPanel.add(new ClasePrueba(this), "prueba");
		
		add(cardPanel);
		showPanel("login");
		pack();
		setVisible( true );
	}
	
	public void showPanel(String nombre) {
		cardPanel.showCard(nombre);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main( String[] args ) {
		new VentanaInicio();
	}
}

