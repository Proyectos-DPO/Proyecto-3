package interfaz.clientStuff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaInicio;

public class BarraCarrito extends JPanel {

    private VentanaInicio ventanaInicio;

    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);
    private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    public BarraCarrito(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CORPORATE_PURPLE));

        
        JLabel lblTitulo = new JLabel("Carrito");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(40, 40, 40));
        add(lblTitulo);

        
        JButton btnRegresar = crearNavButton("Regresar a Tienda");
        btnRegresar.addActionListener(e -> ventanaInicio.showPanel("clientHome"));
        add(btnRegresar);

        
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
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return button;
    }
}
