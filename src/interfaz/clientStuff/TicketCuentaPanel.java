package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaInicio;
import tiquetes.TiqueteIndividual;

public class TicketCuentaPanel extends JPanel {

    private final TiqueteIndividual tiquete;
    private final VentanaInicio ventanaInicio;

    public TicketCuentaPanel(String idTiquete, String asientoTexto) {
        this(idTiquete, asientoTexto, null, null);
    }

    public TicketCuentaPanel(String idTiquete,
                             String asientoTexto,
                             TiqueteIndividual tiquete,
                             VentanaInicio ventanaInicio) {
        this.tiquete = tiquete;
        this.ventanaInicio = ventanaInicio;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 224, 224), 1, true),
                BorderFactory.createEmptyBorder(16, 18, 16, 18)
        ));
        setAlignmentX(LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        
        JLabel lblTitulo = new JLabel("Ticket " + idTiquete);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.DARK_GRAY);
        add(lblTitulo);
        add(Box.createRigidArea(new Dimension(0, 4)));

        
        
        JLabel lblAsiento = new JLabel(asientoTexto);
        lblAsiento.setFont(new Font("Arial", Font.PLAIN, 13));
        lblAsiento.setForeground(new Color(110, 110, 110));
        add(lblAsiento);
        add(Box.createRigidArea(new Dimension(0, 10)));

        
        
        
        
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));

        botonesPanel.add(crearBotonPlano("Solicitar Reembolso"));
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        botonesPanel.add(crearBotonPlano("Solicitar Transferencia"));
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton btnQR = crearBotonPlano("Generar/Visualizar QR");
        botonesPanel.add(btnQR);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        botonesPanel.add(crearBotonPlano("Crear Oferta"));

        add(botonesPanel);

        // Si tenemos tiquete real y ventana, conectamos el botón QR
        if (this.tiquete != null && this.ventanaInicio != null) {
            btnQR.addActionListener(e -> abrirDialogoQR());
        }
    }

    private JButton crearBotonPlano(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 1, true));
        btn.setPreferredSize(new Dimension(160, 32));
        btn.setMaximumSize(new Dimension(180, 32));
        return btn;
    }

    private void abrirDialogoQR() {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(new VisualizarQRPanel(ventanaInicio, tiquete, dialog), BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(ventanaInicio);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}