package interfaz.clientStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicketCuentaPanel extends JPanel {

    public TicketCuentaPanel(String idTiquete, String asientoTexto) {
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
        botonesPanel.add(crearBotonPlano("Generar/Visualizar QR"));
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(crearBotonPlano("Crear Oferta"));

        add(botonesPanel);
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
}