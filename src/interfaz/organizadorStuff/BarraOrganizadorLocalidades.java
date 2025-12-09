package interfaz.organizadorStuff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaInicio;

/**
 * Barra superior para el panel de "Mis localidades" del Organizador.
 */
public class BarraOrganizadorLocalidades extends JPanel {

    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);
    private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    private VentanaInicio ventanaInicio;

    public BarraOrganizadorLocalidades(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CORPORATE_PURPLE));

        // Título "Organizador"
        JLabel lblTitulo = new JLabel("Organizador");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(40, 40, 40));
        add(lblTitulo);

        // Botones
        JButton btnMarket = crearNavButton("Market Place");
        btnMarket.addActionListener(e -> ventanaInicio.showPanel("clientHome"));
        add(btnMarket);

        JButton btnTienda = crearNavButton("Tienda");
        btnTienda.addActionListener(e -> ventanaInicio.showPanel("clientHome"));
        add(btnTienda);

        JButton btnPeticion = crearNavButton("Crear Peticion");
        // TODO: lógica real de peticiones
        add(btnPeticion);

        JButton btnVolver = crearNavButton("Volver al panel");
        btnVolver.addActionListener(e -> ventanaInicio.showPanel("organizadorHome"));
        add(btnVolver);

        JButton btnLogout = crearNavButton("Log Out");
        btnLogout.addActionListener(e -> {
            ventanaInicio.setSesion(null);
            ventanaInicio.showPanel("login");
        });
        add(btnLogout);
    }

    private JButton crearNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(CORPORATE_PURPLE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return button;
    }
}