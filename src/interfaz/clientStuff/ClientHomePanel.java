package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
 * Contiene la BarraCliente y la lista scrollable de eventos.
 */
public class ClientHomePanel extends JPanel {

    private VentanaInicio ventanaInicio;
    private JPanel listContainer; // Panel interno que tendrá las tarjetas

    public ClientHomePanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 1. Agregar Barra de Navegación
        BarraCliente barra = new BarraCliente(ventanaInicio);
        add(barra, BorderLayout.NORTH);

        // 2. Título de la sección
        JLabel lblTitulo = new JLabel("Eventos Disponibles");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 3. Contenedor de la lista de tarjetas (BoxLayout Vertical)
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(Color.WHITE);
        listContainer.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30)); // Márgenes laterales

        // Cargar los eventos dinámicamente
        cargarEventos();

        // 4. ScrollPane para manejar el desbordamiento
        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.setBorder(null); // Sin borde extra
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll más suave
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Panel central para unir título y lista
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(lblTitulo, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Obtiene la lista de eventos desde Datos y genera las tarjetas.
     */
    private void cargarEventos() {
        Datos datos = ventanaInicio.getDatos();
        if (datos == null) return;

        ArrayList<Evento> listaEventos = datos.getEventosAsList();

        if (listaEventos.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay eventos disponibles por el momento.");
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 16));
            lblVacio.setAlignmentX(Component.LEFT_ALIGNMENT);
            listContainer.add(lblVacio);
        } else {
            for (Evento evento : listaEventos) {
                // Crear tarjeta
                EventCardPanel card = new EventCardPanel(evento);
                card.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear a la izquierda

                // Añadir al contenedor
                listContainer.add(card);
                
                // Espacio entre tarjetas
                listContainer.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }
    }
}