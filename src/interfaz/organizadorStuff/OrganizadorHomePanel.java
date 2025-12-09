package interfaz.organizadorStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaz.VentanaInicio;

public class OrganizadorHomePanel extends JPanel {

    private VentanaInicio ventanaInicio;
    private JPanel listPanel;

    public OrganizadorHomePanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        
        
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        BarraOrganizador barra = new BarraOrganizador(ventanaInicio);
        add(barra, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        JPanel headerPanel = new JPanel();
        
        
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel lblMisEventos = new JLabel("Mis eventos");
        lblMisEventos.setFont(new Font("Arial", Font.BOLD, 20));

        
        
        
        JButton btnCrearEvento = new JButton("Crear evento");
        btnCrearEvento.setFont(new Font("Arial", Font.BOLD, 13));
        btnCrearEvento.setFocusPainted(false);
        btnCrearEvento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearEvento.setBackground(new Color(120, 81, 169));
        btnCrearEvento.setForeground(Color.WHITE);
        btnCrearEvento.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        
        
        
        headerPanel.add(lblMisEventos);
        headerPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        headerPanel.add(btnCrearEvento);
        headerPanel.add(Box.createHorizontalGlue());

        centerPanel.add(headerPanel, BorderLayout.NORTH);

        
        
        
        
        
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        
        cargarEventosDummy();

        
        
        
        
        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        
        
        
        centerPanel.add(scroll, BorderLayout.CENTER);

        
        add(centerPanel, BorderLayout.CENTER);
        
        
    }

    private void cargarEventosDummy() {
        for (int i = 1; i <= 3; i++) {
            OrganizadorEventCardPanel card = new OrganizadorEventCardPanel(
                ventanaInicio,
                "Evento de este Organizador  " + i,
                "Ganancias Generales: $5000"
            );
            
            
            
            card.setAlignmentX(LEFT_ALIGNMENT);
            
            listPanel.add(card);
            listPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }
    }
}