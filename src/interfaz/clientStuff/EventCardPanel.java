package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import eventos.Evento; // Importación correcta del modelo

/**
 * Componente gráfico que representa la tarjeta de un Evento individual.
 * Muestra nombre, fecha, hora, lugar y botones de acción.
 */
public class EventCardPanel extends JPanel {

    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);

    public EventCardPanel(Evento evento) {
        // Configuración del panel tarjeta
        setLayout(new BorderLayout(15, 0));
        setBackground(Color.WHITE);
        // Borde gris sutil y padding interno
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        // Tamaño fijo preferido para uniformidad en la lista
        setPreferredSize(new Dimension(800, 180));
        setMaximumSize(new Dimension(1200, 180)); 

        // 1. Placeholder de Imagen (Izquierda/Oeste)
        JLabel imagePlaceholder = new JLabel("IMG", SwingConstants.CENTER);
        imagePlaceholder.setPreferredSize(new Dimension(150, 150));
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(new Color(240, 240, 240));
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(imagePlaceholder, BorderLayout.WEST);

        // 2. Información del Evento (Centro)
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(2, 0, 5, 0);

        // Título del Evento
        JLabel lblTitle = new JLabel(evento.getNombre());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(CORPORATE_PURPLE);
        gbc.gridx = 0; gbc.gridy = 0;
        infoPanel.add(lblTitle, gbc);

        // Fecha y Hora (Formateadas)
        String fechaStr = formatearFecha(evento.getFecha());
        String horaStr = formatearHora(evento.getHora());
        JLabel lblDate = new JLabel("Fecha: " + fechaStr + " | Hora: " + horaStr);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDate.setForeground(Color.DARK_GRAY);
        gbc.gridy = 1;
        infoPanel.add(lblDate, gbc);

        // Venue (Lugar) y Tipo
        // Usamos evento.getVenue().getNombre()
        String venueNombre = (evento.getVenue() != null) ? evento.getVenue().getNombre() : "Lugar por confirmar";
        JLabel lblVenue = new JLabel("Lugar: " + venueNombre + " (" + evento.getTipoEvento() + ")");
        lblVenue.setFont(new Font("Arial", Font.ITALIC, 14));
        gbc.gridy = 2;
        infoPanel.add(lblVenue, gbc);

        // Descripción (Placeholder porque Evento.java no tiene campo descripcion)
        // Se simula una descripción basada en el tipo para llenar el espacio visualmente
        String descTexto = "Disfruta de este increíble evento " + evento.getTipoEvento().toLowerCase() + 
                           " organizado por " + (evento.getOrganizador() != null ? evento.getOrganizador().getLogin() : "nosotros") + ".";
        
        JLabel lblDesc = new JLabel("<html><body style='width: 300px;'>" + descTexto + "</body></html>");
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDesc.setForeground(Color.GRAY);
        gbc.gridy = 3; gbc.weighty = 1.0; // Ocupar el resto del espacio vertical
        gbc.insets = new Insets(10, 0, 0, 0);
        infoPanel.add(lblDesc, gbc);

        add(infoPanel, BorderLayout.CENTER);

        // 3. Botones de Acción (Derecha/Este)
        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.gridx = 0; gbcBtn.fill = GridBagConstraints.HORIZONTAL;
        gbcBtn.insets = new Insets(5, 0, 5, 0);

        // Botón "Ver Detalles" (Secundario)
        JButton btnInfo = new JButton("Ver Detalles");
        styleButton(btnInfo, false);
        gbcBtn.gridy = 0;
        actionPanel.add(btnInfo, gbcBtn);

        // Botón "Comprar" (Primario)
        JButton btnBuy = new JButton("Comprar Boletas");
        styleButton(btnBuy, true);
        gbcBtn.gridy = 1;
        gbcBtn.weighty = 1.0; 
        gbcBtn.anchor = GridBagConstraints.NORTH; // Alinear arriba
        actionPanel.add(btnBuy, gbcBtn);

        add(actionPanel, BorderLayout.EAST);
    }

    // --- Métodos Auxiliares ---

    private String formatearFecha(LocalDate fecha) {
        if (fecha == null) return "N/D";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String formatearHora(LocalTime hora) {
        if (hora == null) return "N/D";
        return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void styleButton(JButton btn, boolean isPrimary) {
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 40));

        if (isPrimary) {
            btn.setBackground(CORPORATE_PURPLE);
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createEmptyBorder());
        } else {
            btn.setBackground(Color.WHITE);
            btn.setForeground(CORPORATE_PURPLE);
            btn.setBorder(BorderFactory.createLineBorder(CORPORATE_PURPLE, 2));
        }
    }
}