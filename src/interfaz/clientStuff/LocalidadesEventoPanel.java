package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import eventos.Evento;
import eventos.Localidad;
import interfaz.VentanaInicio;

public class LocalidadesEventoPanel extends JPanel {

    private Evento evento;
    private VentanaInicio ventanaInicio;

    public LocalidadesEventoPanel(VentanaInicio ventanaInicio, Evento evento) {
        this.evento = evento;
        this.ventanaInicio = ventanaInicio;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblTitulo = new JLabel("  Localidades (" + evento.getNombre() + ")", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 0));
        topBar.add(lblTitulo, BorderLayout.WEST);

        
        JPanel rightButtons = new JPanel();
        rightButtons.setOpaque(false);
        rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.X_AXIS));
        rightButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        JButton btnRegresar = crearBotonChip("Regresar a Tienda");
        btnRegresar.addActionListener(e -> {
            ventanaInicio.showPanel("clientHome");
        });

        rightButtons.add(btnRegresar);
        rightButtons.add(Box.createRigidArea(new Dimension(10, 0)));

        
        JButton btnCarrito = crearBotonChip("Carrito");
        btnCarrito.addActionListener(e -> ventanaInicio.mostrarCarrito());
        rightButtons.add(btnCarrito);
        rightButtons.add(Box.createRigidArea(new Dimension(10, 0)));
        
        rightButtons.add(Box.createRigidArea(new Dimension(10, 0)));
        rightButtons.add(crearBotonChip("Log Out"));

        topBar.add(rightButtons, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        List<Localidad<?>> localidades = evento.getLocalidadesAsList();

        if (localidades == null || localidades.isEmpty()) {
            JLabel lblVacio = new JLabel("Este evento no tiene localidades registradas.");
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 14));
            listPanel.add(lblVacio);
        } else {
            for (Localidad<?> loc : localidades) {
                LocalidadCardPanel card = new LocalidadCardPanel(loc);
                card.setAlignmentX(LEFT_ALIGNMENT);
                listPanel.add(card);
                listPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);
    }

    private JButton crearBotonChip(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setBackground(new Color(120, 81, 169));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        return btn;
    }
}
