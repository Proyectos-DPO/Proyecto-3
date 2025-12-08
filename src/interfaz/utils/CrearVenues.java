package interfaz.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaz.VentanaInicio;
import sesion.SesionAdmin;

import javax.swing.JScrollPane;

public class CrearVenues extends JPanel implements ActionListener{

	private static final String SEND = "Send";
    private static final String FONT = "Trebuchet MS";

    private JTextField txtNombre;
    private JTextField txtLatitud;
    private JTextField txtLongitud;
    private JTextField txtCapacidad;
    private JTextArea txtRestricciones;
    private JLabel lblTitulo;
    private JPanel card;
    private JButton btnCrear;
    private VentanaInicio papa;
    private JDialog capsula;
    
    public CrearVenues(VentanaInicio papa, JDialog capsula) {
    	
    	this.papa = papa;
    	this.capsula = capsula;
    	
        setLayout(new BorderLayout());
        setBackground(new Color(243,243,243));

        card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(224,224,224)),
                        BorderFactory.createEmptyBorder(20, 24, 24, 24)
                )
        );

  
        lblTitulo = new JLabel("Crear Venue");
        lblTitulo.setFont(new Font(FONT, Font.BOLD, 18));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(48));

        
        txtNombre = crearCampoLabeled(card, "Nombre");
        txtLatitud = crearCampoLabeled(card, "Latitud");
        txtLongitud = crearCampoLabeled(card, "Longitud");
        txtCapacidad = crearCampoLabeled(card, "Capacidad");

        
        JLabel lblRestricciones = new JLabel("Restricciones");
        lblRestricciones.setFont(new Font(FONT, Font.PLAIN, 12));
        card.add(lblRestricciones);
        card.add(Box.createVerticalStrut(4));

        txtRestricciones = new JTextArea(3, 20);
        txtRestricciones.setLineWrap(true);
        txtRestricciones.setWrapStyleWord(true);
        txtRestricciones.setFont(new Font(FONT, Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(txtRestricciones);  //Como el que se uso en PanelLista en el Taller 6 (comentario de Sara)
        scroll.setAlignmentX(LEFT_ALIGNMENT);
        card.add(scroll);
        card.add(Box.createVerticalStrut(20));

        btnCrear = new JButton("Crear Venue");
        btnCrear.setFont(new Font(FONT, Font.BOLD, 14));
        btnCrear.setFocusPainted(false);
        btnCrear.setBackground(new Color(51,51,51));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnCrear.addActionListener(this);
        btnCrear.setActionCommand(SEND);
        card.add(btnCrear);
        
        setSize(new Dimension(360, 700));
        
        btnCrear.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCrear.getPreferredSize().height));
        
        add(card, BorderLayout.NORTH);
    }

    private JTextField crearCampoLabeled(JPanel papa, String labelText) {
    	
        JLabel label = new JLabel(labelText);
        label.setFont(new Font(FONT, Font.PLAIN, 12));

        JTextField textField = new JTextField();
        textField.setFont(new Font(FONT, Font.PLAIN, 12));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221,221,221)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));

        papa.add(label);
        papa.add(Box.createVerticalStrut(4));
        textField.setAlignmentX(LEFT_ALIGNMENT);
        papa.add(textField);
        papa.add(Box.createVerticalStrut(16));

        return textField;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width = 360;
        return d;
    }
    
    public void hideTitle() {
    	lblTitulo.setVisible(false);
        card.revalidate();
        card.repaint();
    }
    
    public void hideButton() {
    	btnCrear.setVisible(false);
    	card.revalidate();
    	card.repaint();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if (comando.equals(SEND)) {
			SesionAdmin sesion = (SesionAdmin) papa.getSesion();
			try {
				sesion.crearVenue(papa.getDatos(), txtNombre.getText(), Double.valueOf(txtLatitud.getText()), Double.valueOf(txtLongitud.getText()), Integer.valueOf(txtCapacidad.getText()), txtRestricciones.getText());
				capsula.dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(
				        capsula,
				        "Error: pusiste mal los datos, pendejo",
				        "Error",
				        JOptionPane.ERROR_MESSAGE
				);
			}
			
		}
	}

	
}
