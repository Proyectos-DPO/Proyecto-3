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

import eventos.Localidad;

public class LocalidadCardPanel extends JPanel {

    private Localidad<?> localidad;

    public LocalidadCardPanel(Localidad<?> localidad) {
        this.localidad = localidad;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        setPreferredSize(new Dimension(850, 90));
        setMaximumSize(new Dimension(850, 100));

        
        
        JLabel lblTitle = new JLabel(localidad.getNombre());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.DARK_GRAY);
        add(lblTitle);

        add(Box.createRigidArea(new Dimension(0, 8)));

        
        
        
        JLabel lblDesc = new JLabel("Descripcion evento");
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(120, 120, 120));
        add(lblDesc);

        add(Box.createVerticalGlue());

        
        JButton btnIr = new JButton("Ir a localidad");
        btnIr.setAlignmentX(CENTER_ALIGNMENT);
        styleButton(btnIr);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(btnIr);

    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 32));
        btn.setMaximumSize(new Dimension(140, 32));

        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 1, true));
    }
}
