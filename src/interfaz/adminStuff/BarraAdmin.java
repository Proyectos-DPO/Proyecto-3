package interfaz.adminStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaInicio;
import interfaz.utils.CrearVenues;

public class BarraAdmin extends JPanel implements ActionListener {
	
	private static final String CREAR_VENUE = "VENIU IWACHU ALSDFJALKSÑDF";
	private static final String STATS = "ESTUADUIASTIAUSCAS";
	private static final String LOGOUT = "LOGAUT";
	private static final String PETICIONES  = "ADMNISJDFIAS PETI IONES";
	private static final String MARKETPLACE  = "Es esto el nuevo Polymarkt?";
	private static final String CUOTAS  = "Entre más impuestos, más ricos seremos... pero nuestro pueblo sufrirá más. ¿Valdrá la pena?";
	
	private VentanaInicio papa;
	
	public BarraAdmin(VentanaInicio papa) {
		this.papa = papa;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setBackground(Color.WHITE);

        JLabel logo = new JLabel("Admin: "+papa.getSesion().getLogin());

        add(logo);
        add(Box.createHorizontalGlue());

        JButton botonStats = crearNavButton("Estadísticas");
        botonStats.addActionListener(this);
        botonStats.setActionCommand(STATS);
        add(botonStats);
        add(Box.createRigidArea(new Dimension(10,0)));
        
        JButton botonMK = crearNavButton("Market Place");
        botonMK.addActionListener(this);
        botonMK.setActionCommand(MARKETPLACE);
        add(botonMK);
        add(Box.createRigidArea(new Dimension(10,0)));
       
        
        JButton botonCrearVenue = crearNavButton("Crear Venue");
        botonCrearVenue.addActionListener(this);
        botonCrearVenue.setActionCommand(CREAR_VENUE);
        add(botonCrearVenue);
        add(Box.createRigidArea(new Dimension(10,0)));
        
        JButton botonPeticiones = crearNavButton("Administrar Peticiones");
        botonPeticiones.addActionListener(this);
        botonPeticiones.setActionCommand(PETICIONES);
        add(botonPeticiones);
        add(Box.createRigidArea(new Dimension(10,0)));
        
        
        JButton botonCuotas = crearNavButton("Fija Cuotas");
        botonCuotas.addActionListener(this);
        botonCuotas.setActionCommand(CUOTAS);
        add(botonCuotas);
        add(Box.createRigidArea(new Dimension(10,0)));
        
        
        JButton logout = crearNavButton("Log Out");
        logout.addActionListener(this);
        logout.setActionCommand(LOGOUT);
        add(logout);
	}
	
	private JButton crearNavButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(120, 81, 169));
        button.setBorder(BorderFactory.createEmptyBorder(8,20,8,20));
        return button;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case STATS:
			papa.showPanel("adminHome");
			break;
		case CREAR_VENUE:
			JDialog lol = new JDialog();
			lol.setLayout(new BorderLayout());
			lol.add(new CrearVenues(papa, lol), BorderLayout.CENTER);
			lol.pack();
			lol.setVisible(true);
			break;
		case MARKETPLACE:
			break;
		case PETICIONES:
			if (!papa.getCardPanel().isCardPresent("adminPeticiones")) {
				papa.getCardPanel().add(new AdminPeticionManager(papa), "adminPeticiones");
			}
			papa.showPanel("adminPeticiones");
			break;
		case CUOTAS:
			new AdminDialogFijarCuotas(papa);
			
			break;
		
		case LOGOUT:
			papa.setSesion(null);
			papa.showPanel("login");
		}
		
	}

}
