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

import data.Datos;
import persistencia.Persistencia;
import sesion.Sesion;

public class VentanaInicio extends JFrame {

	private CardPanel cardPanel;
	private Datos datos;
	private Sesion sesion;

	
	public VentanaInicio() {
		
		try {
			datos = Persistencia.cargarDatos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle( "BoletaMaster" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        setResizable(false);
        
		cardPanel = new CardPanel();
		
		cardPanel.add(new LoginView(this), "login");
		
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

	
	
	public CardPanel getCardPanel() {
		return cardPanel;
	}

	public Datos getDatos() {
		return datos;
	}

	public void setDatos(Datos datos) {
		this.datos = datos;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	
	public static void main( String[] args ) {
		new VentanaInicio();
	}
	
	
	
}

