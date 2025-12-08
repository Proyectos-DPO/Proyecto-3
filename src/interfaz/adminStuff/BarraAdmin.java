package interfaz.adminStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaInicio;

public class BarraAdmin extends JPanel implements ActionListener {
	
	private static final String CREAR_VENUE = "VENIU IWACHU ALSDFJALKSÑDF";
	private static final String STATS = "ESTUADUIASTIAUSCAS";
	private static final String LOGOUT = "LOGAUT";
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
        
        
        add(crearNavButton("Market Place"));
        add(Box.createRigidArea(new Dimension(10,0)));
        
        JButton botonCrearVenue = crearNavButton("Crear Venue");
        botonCrearVenue.addActionListener(this);
        botonCrearVenue.setActionCommand(CREAR_VENUE);
        
        add(botonCrearVenue);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(crearNavButton("Administrar Peticiones"));
        add(Box.createRigidArea(new Dimension(10,0)));
        add(crearNavButton("Fijar Cuotas"));
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
			break;
		case LOGOUT:
			papa.setSesion(null);
			papa.showPanel("login");
		}
		
	}

}
