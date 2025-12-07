package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaInicio extends JFrame {

	private LoginView login;
	
	public VentanaInicio() {
		
		setLayout( new BorderLayout());
		
		login = new LoginView();
		login.setBorder(BorderFactory.createEmptyBorder(50, 230, 350, 230));
		add(login, BorderLayout.CENTER);
		
		JPanel header = new JPanel(new BorderLayout());
		JLabel headerLabel = new JLabel("LOG IN");
		headerLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 250));
		header.setPreferredSize(new Dimension(50,64));
		header.add(headerLabel, BorderLayout.WEST);
		add(header, BorderLayout.NORTH);
		
		
		setTitle( "Login" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 700 );
        setLocationRelativeTo( null );
        setVisible( true );
        setResizable(false);
	}
	
	public static void main( String[] args ) {
		new VentanaInicio();
	}
}

