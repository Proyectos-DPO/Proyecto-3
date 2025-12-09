package interfaz.Crear;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaz.VentanaInicio;
import sesion.SesionOrganizador;

public class CrearEvento extends JPanel implements ActionListener {

    private static final String SEND = "SEND_EVENTO";
    private static final String FONT = "Trebuchet MS";

    private JTextField txtNombre;
    private JComboBox<String> comboFecha;
    private JComboBox<String> comboHora;
    private JTextField txtCargo;
    private JTextField txtVenue;

    private JLabel lblTitulo;
    private JPanel card;
    private JButton btnCrear;
    private VentanaInicio papa;
    private JDialog capsula;

    public CrearEvento(VentanaInicio papa, JDialog capsula) {

        this.papa = papa;
        this.capsula = capsula;

        setLayout(new BorderLayout());
        setBackground(new Color(243, 243, 243));

        card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 224, 224)),
                BorderFactory.createEmptyBorder(20, 24, 24, 24)
            )
        );

        
        lblTitulo = new JLabel("Crear Evento");
        lblTitulo.setFont(new Font(FONT, Font.BOLD, 18));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(32));

        
        txtNombre = crearCampoLabeled(card, "Nombre");

        
        JLabel lblInicio = new JLabel("Inicio del Evento");
        lblInicio.setFont(new Font(FONT, Font.PLAIN, 12));
        lblInicio.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblInicio);
        card.add(Box.createVerticalStrut(4));

        JPanel panelFechaHora = new JPanel();
        panelFechaHora.setOpaque(false);
        panelFechaHora.setLayout(new BoxLayout(panelFechaHora, BoxLayout.X_AXIS));

        comboFecha = new JComboBox<>(new String[] {
            "2025-01-01",
            "2025-02-01",
            "2025-03-01"
            
        });
        comboFecha.setFont(new Font(FONT, Font.PLAIN, 12));

        comboHora = new JComboBox<>(new String[] {
            "18:00",
            "20:00",
            "22:00"
            
        });
        comboHora.setFont(new Font(FONT, Font.PLAIN, 12));

        panelFechaHora.add(comboFecha);
        panelFechaHora.add(Box.createHorizontalStrut(8));
        panelFechaHora.add(comboHora);
        panelFechaHora.setAlignmentX(LEFT_ALIGNMENT);

        card.add(panelFechaHora);
        card.add(Box.createVerticalStrut(16));

        
        txtCargo = crearCampoLabeled(card, "Cargo Porcentual");

        
        txtVenue = crearCampoLabeled(card, "Venue");

        
        btnCrear = new JButton("Crear Evento");
        btnCrear.setFont(new Font(FONT, Font.BOLD, 14));
        btnCrear.setFocusPainted(false);
        btnCrear.setBackground(new Color(51, 51, 51));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnCrear.addActionListener(this);
        btnCrear.setActionCommand(SEND);
        btnCrear.setAlignmentX(LEFT_ALIGNMENT);

        card.add(Box.createVerticalStrut(24));
        card.add(btnCrear);

        setSize(new Dimension(360, 600));
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
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SEND.equals(e.getActionCommand())) {
            try {
                String nombre = txtNombre.getText().trim();
                String fecha = (String) comboFecha.getSelectedItem();
                String hora  = (String) comboHora.getSelectedItem();
                String venue = txtVenue.getText().trim();
                double cargo = Double.parseDouble(txtCargo.getText().trim());

                
                SesionOrganizador sesion = (SesionOrganizador) papa.getSesion();

                
                System.out.println("CrearEvento -> nombre=" + nombre +
                                   ", fecha=" + fecha +
                                   ", hora=" + hora +
                                   ", cargo=" + cargo +
                                   ", venue=" + venue);

                capsula.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    capsula,
                    "Error al crear el evento. Revisa los datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        }
    }
}
