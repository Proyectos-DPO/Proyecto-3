package interfaz.Crear;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaz.VentanaInicio;
import interfaz.utils.CrearVenues;

public class CrearPeticion extends JPanel implements ActionListener {

	private static final String SEND = "Send";
    private static final String FONT = "Trebuchet MS";
	
    private JButton btnCrear;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboObjeto;
    private CrearVenues panelVenue;
    private JPanel card;
    private JLabel lblObjeto;
    
    private String[] listaBoletos;
    private String[] listaEventos;
    private VentanaInicio papa;
    
	public CrearPeticion(VentanaInicio papa, String[] listaBoletos, String[] listaEventos) {
		
		this.papa = papa;
		
		setLayout(new BorderLayout());
        setBackground(new Color(243,243,243));
        this.listaBoletos = listaBoletos;
        this.listaEventos = listaEventos;

        card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(224,224,224)),
                        BorderFactory.createEmptyBorder(20, 24, 24, 24)
                )
        );

  
        JLabel lblTitulo = new JLabel("Crear Peticion");
        lblTitulo.setFont(new Font(FONT, Font.BOLD, 18));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(48));
       
        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setFont(new Font(FONT, Font.PLAIN, 12));
        lblTipo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTipo);
        card.add(Box.createVerticalStrut(4));

        comboTipo = new JComboBox<>(new String[] { "Seleccione...", "Crear Venue", "Cancelar Evento", "Solicitar Reembolso", "Ingreso a Marketplace" });
        comboTipo.setFont(new Font(FONT, Font.PLAIN, 12));
        comboTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboTipo.setAlignmentX(LEFT_ALIGNMENT);
        comboTipo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        comboTipo.addActionListener(this);
        card.add(comboTipo);
        card.add(Box.createVerticalStrut(16));

        lblObjeto = new JLabel("Objeto de la petición");
        lblObjeto.setFont(new Font(FONT, Font.PLAIN, 12));
        lblObjeto.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblObjeto);
        card.add(Box.createVerticalStrut(4));

        comboObjeto = new JComboBox<>(new String[] { "Seleccione un tipo primero" });
        comboObjeto.setFont(new Font(FONT, Font.PLAIN, 12));
        comboObjeto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboObjeto.setAlignmentX(LEFT_ALIGNMENT);
        card.add(comboObjeto);
        card.add(Box.createVerticalStrut(24));
        
     
        
        // no visible
        //panelVenue = new CrearVenues(papa, this);
        panelVenue.hideTitle();
        panelVenue.hideButton();
        panelVenue.setAlignmentX(LEFT_ALIGNMENT);
        panelVenue.setVisible(false);   // solo se verá cuando Tipo = "Venue"
        card.add(panelVenue);
        card.add(Box.createVerticalStrut(24));
        card.add(panelVenue);
        card.add(Box.createVerticalStrut(24));
        
        btnCrear = new JButton("Enviar Peticion");
        btnCrear.setFont(new Font(FONT, Font.BOLD, 14));
        btnCrear.setFocusPainted(false);
        btnCrear.setBackground(new Color(51,51,51));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnCrear.addActionListener(this);
        btnCrear.setActionCommand(SEND);
        card.add(btnCrear);

        btnCrear.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCrear.getPreferredSize().height));

        add(card, BorderLayout.NORTH);
	}
	
	private void actualizarSegunTipo(String tipo) {
		switch (tipo) {
		case "Crear Venue": 
			comboObjeto.setVisible(false);
			panelVenue.setVisible(true);
			lblObjeto.setVisible(true);
			break;
		case "Cancelar Evento":
			comboObjeto.setModel(new DefaultComboBoxModel<>(listaEventos));
			comboObjeto.setVisible(true);
			panelVenue.setVisible(false);
			lblObjeto.setVisible(true);
			break;
		case "Solicitar Reembolso":
			comboObjeto.setModel(new DefaultComboBoxModel<>(listaBoletos));
			comboObjeto.setVisible(true);
			panelVenue.setVisible(false);
			lblObjeto.setVisible(true);
			
			break;
		case "Ingreso a Marketplace":
			comboObjeto.setVisible(false);
			panelVenue.setVisible(false);
			lblObjeto.setVisible(false);
			break;
		case "Seleccione...":
			comboObjeto.setModel(new DefaultComboBoxModel<>(new String[] { "Seleccione un tipo primero" }));
			comboObjeto.setVisible(false);
			panelVenue.setVisible(false);
			lblObjeto.setVisible(false);
			
			break;
		
		}
		card.setSize(getPreferredSize());
		card.revalidate();
		card.repaint();
		papa.pack();
		papa.setLocationRelativeTo(null);
	}
	
	@Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width = 360;
        return d;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
        if (source == comboTipo) {
            String tipo = (String) comboTipo.getSelectedItem();
            actualizarSegunTipo(tipo);
        }

        if (SEND.equals(e.getActionCommand())) {
        }
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

	public String[] getListaBoletos() {
		return listaBoletos;
	}

	public void setListaBoletos(String[] listaBoletos) {
		this.listaBoletos = listaBoletos;
	}

	public String[] getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(String[] listaEventos) {
		this.listaEventos = listaEventos;
	}
	
	
}
