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

import eventos.Evento;
import interfaz.VentanaInicio;

public class EventCardPanel extends JPanel {

    private final Evento evento;
    private final VentanaInicio ventanaInicio;

    // --- Constructores de conveniencia ---

    // Versión clásica: botón "Compra ya" y con ventanaInicio
    public EventCardPanel(Evento evento, VentanaInicio ventanaInicio) {
        this(evento, "Compra ya", ventanaInicio);
    }

    // Versión clásica sin ventanaInicio (compatibilidad con otros usos)
    public EventCardPanel(Evento evento) {
        this(evento, "Compra ya", null);
    }

    // Versión con texto de botón pero sin ventanaInicio (compatibilidad)
    public EventCardPanel(Evento evento, String buttonText) {
        this(evento, buttonText, null);
    }

    // --- Constructor principal que realmente hace el trabajo ---
    public EventCardPanel(Evento evento, String buttonText, VentanaInicio ventanaInicio) {
        this.evento = evento;
        this.ventanaInicio = ventanaInicio;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Borde tipo tarjeta
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        // Tamaño aproximado al mockup
        setPreferredSize(new Dimension(260, 140));
        setMaximumSize(new Dimension(260, 160));

        // Título (nombre del evento)
        JLabel lblTitle = new JLabel(evento.getNombre());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setForeground(Color.DARK_GRAY);
        add(lblTitle);

        add(Box.createRigidArea(new Dimension(0, 8)));

        // Descripción placeholder
        JLabel lblDesc = new JLabel("Descripcion evento");
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(120, 120, 120));
        add(lblDesc);

        add(Box.createVerticalGlue());

        // Botón principal
        JButton btnAction = new JButton(buttonText);
        btnAction.setAlignmentX(CENTER_ALIGNMENT);
        styleButton(btnAction);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(btnAction);

        // 👉 Si es "Compra ya" y tenemos referencia a VentanaInicio,
        // le pedimos que cambie al panel de localidades de este evento.
        if (buttonText.equalsIgnoreCase("Compra ya") && this.ventanaInicio != null) {
            btnAction.addActionListener(e -> {
                // Este método lo debes crear en VentanaInicio
                // y hacer que use tu CardLayout/CardPanel para mostrar
                // el nuevo LocalidadesEventoPanel.
                ventanaInicio.mostrarLocalidadesEvento(this.evento);
            });
        }
        // Si el botón dice "Agregar a Carrito" u otra cosa, por ahora no hace nada;
        // luego puedes agregarle lógica para el carrito aquí.
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



