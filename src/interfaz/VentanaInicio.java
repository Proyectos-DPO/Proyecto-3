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

	private JPanel cardPanel;
	private CardLayout cardLO;
	
	public VentanaInicio() {
		
		setTitle( "BoletaMaster" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        setResizable(false);
   
        cardLO = new CardLayout();
		cardPanel = new JPanel( cardLO);
		
		cardPanel.add(new LoginView(this), "login");
	    pack();
		cardPanel.add(new ClasePrueba(this), "prueba");
		
		add(cardPanel);
		showPanel("login");
		setVisible( true );
	}
	
	public void showPanel(String nombre) {
		cardLO.show(cardPanel, nombre);
		pack();
	}

	public static void main( String[] args ) {
		new VentanaInicio();
	}
}

