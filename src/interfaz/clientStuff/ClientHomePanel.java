package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Datos;
import eventos.Evento;
import interfaz.VentanaInicio;

/**
 * Panel principal de la vista de Cliente (La Tienda).
 * Muestra 3 columnas: Individuales, Multiples y Paquetes Deluxe.
 */
public class ClientHomePanel extends JPanel {

    private VentanaInicio ventanaInicio;

    private JPanel columnaIndividuales;
    private JPanel columnaMultiples;
    private JPanel columnaPaquetes;

    public ClientHomePanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Barra (no se modifica)
        BarraCliente barra = new BarraCliente(ventanaInicio);
        add(barra, BorderLayout.NORTH);

        // Título general
        JLabel lblTitulo = new JLabel("Tienda (Vista Cliente)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        // Panel con las 3 columnas
        JPanel marketplacePanel = new JPanel();
        marketplacePanel.setBackground(Color.WHITE);
        marketplacePanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        marketplacePanel.setLayout(new java.awt.GridLayout(1, 3, 30, 0));

        columnaIndividuales = crearColumna("Tiquetes Individuales");
        columnaMultiples    = crearColumna("Tiquetes Multiples");
        columnaPaquetes     = crearColumna("Paquetes Deluxe");

        marketplacePanel.add(columnaIndividuales);
        marketplacePanel.add(columnaMultiples);
        marketplacePanel.add(columnaPaquetes);

        // Cargar los productos específicos
        cargarProductosCliente();

        // Scroll vertical
        JScrollPane scrollPane = new JScrollPane(marketplacePanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(lblTitulo, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel crearColumna(String titulo) {
        JPanel col = new JPanel();
        col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
        col.setBackground(Color.WHITE);

        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        col.add(lbl);
        col.add(Box.createRigidArea(new Dimension(0, 5)));

        return col;
    }

    /**
     * Lógica para llenar las 3 columnas con los eventos deseados.
     * Usa solo Datos.getEventosAsList() (no modifica el paquete data).
     */
    private void cargarProductosCliente() {
        Datos datos = ventanaInicio.getDatos();
        if (datos == null) return;

        ArrayList<Evento> eventos = datos.getEventosAsList();
        if (eventos == null || eventos.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay eventos disponibles por el momento.");
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 14));
            columnaIndividuales.add(lblVacio);
            return;
        }

        // Nombres de los eventos tal como los usas en tus datos
        final String EVENTO_ROCK   = "Rock al parque";
        final String EVENTO_GOSPEL = "Bogota Gospel";
        final String EVENTO_PIRI   = "Piriparapu";

        Evento eventoRock   = buscarEventoPorNombre(eventos, EVENTO_ROCK);
        Evento eventoGospel = buscarEventoPorNombre(eventos, EVENTO_GOSPEL);
        Evento eventoPiri   = buscarEventoPorNombre(eventos, EVENTO_PIRI);

        // --- Columna 1: 3 tiquetes individuales de Rock al Parque ---
        if (eventoRock != null) {
            for (int i = 0; i < 3; i++) {
                // 👇 Pasamos también la ventanaInicio
                EventCardPanel card = new EventCardPanel(eventoRock, "Compra ya", ventanaInicio);
                card.setAlignmentX(LEFT_ALIGNMENT);
                columnaIndividuales.add(card);
                columnaIndividuales.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        } else {
            JLabel lbl = new JLabel("No se encontró el evento Rock al parque.");
            lbl.setFont(new Font("Arial", Font.ITALIC, 13));
            columnaIndividuales.add(lbl);
        }

        // --- Columna 2: 1 tiquete múltiple de Bogota Gospel ---
        if (eventoGospel != null) {
            EventCardPanel card = new EventCardPanel(eventoGospel, "Agregar a Carrito", ventanaInicio);
            card.setAlignmentX(LEFT_ALIGNMENT);
            columnaMultiples.add(card);
            columnaMultiples.add(Box.createRigidArea(new Dimension(0, 20)));
        } else {
            JLabel lbl = new JLabel("No se encontró el evento Bogota Gospel.");
            lbl.setFont(new Font("Arial", Font.ITALIC, 13));
            columnaMultiples.add(lbl);
        }

        // --- Columna 3: 1 paquete deluxe de Piriparapu ---
        if (eventoPiri != null) {
            EventCardPanel card = new EventCardPanel(eventoPiri, "Agregar a Carrito", ventanaInicio);
            card.setAlignmentX(LEFT_ALIGNMENT);
            columnaPaquetes.add(card);
            columnaPaquetes.add(Box.createRigidArea(new Dimension(0, 20)));
        } else {
            JLabel lbl = new JLabel("No se encontró el evento Piriparapu.");
            lbl.setFont(new Font("Arial", Font.ITALIC, 13));
            columnaPaquetes.add(lbl);
        }
    }

    /**
     * Busca un evento por nombre (ignorando mayúsculas/minúsculas).
     */
    private Evento buscarEventoPorNombre(ArrayList<Evento> eventos, String nombre) {
        for (Evento e : eventos) {
            if (e.getNombre() != null && e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }
}