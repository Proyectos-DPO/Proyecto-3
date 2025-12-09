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

public class LocalidadesOrganizadorPanel extends JPanel {

    private VentanaInicio ventanaInicio;
    private JPanel listPanel;

    public LocalidadesOrganizadorPanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        BarraOrganizadorLocalidades barra = new BarraOrganizadorLocalidades(ventanaInicio);
        add(barra, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        
        
        
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel lblMisLocalidades = new JLabel("Mis localidades");
        lblMisLocalidades.setFont(new Font("Arial", Font.BOLD, 20));

        
        
        JButton btnCrearLoc = new JButton("Crear Localidad");
        btnCrearLoc.setFont(new Font("Arial", Font.BOLD, 13));
        btnCrearLoc.setFocusPainted(false);
        btnCrearLoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearLoc.setBackground(new Color(120, 81, 169));
        btnCrearLoc.setForeground(Color.WHITE);
        btnCrearLoc.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        
        headerPanel.add(lblMisLocalidades);
        headerPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        headerPanel.add(btnCrearLoc);
        headerPanel.add(Box.createHorizontalGlue());

        centerPanel.add(headerPanel, BorderLayout.NORTH);

        
        
        
        
        
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        cargarLocalidadesDummy();

        
        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        
        
        centerPanel.add(scroll, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

    }

    private void cargarLocalidadesDummy() {
    	
    	
    	
    	
        String ganancias = "Ganancias Generales: $3500";

        LocalidadOrganizadorCardPanel l1 =
            new LocalidadOrganizadorCardPanel("Platea", ganancias);
        l1.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(l1);
        listPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        
        
        
        LocalidadOrganizadorCardPanel l2 =
            new LocalidadOrganizadorCardPanel("VIP", ganancias);
        l2.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(l2);
        listPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        

        LocalidadOrganizadorCardPanel l3 =
            new LocalidadOrganizadorCardPanel("Gradas", ganancias);
        l3.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(l3);
    }
}