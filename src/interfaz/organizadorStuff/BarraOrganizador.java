package interfaz.organizadorStuff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import interfaz.VentanaInicio;

public class BarraOrganizador extends JPanel {

    private VentanaInicio ventanaInicio;

    public BarraOrganizador(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JButton btnMarket   = crearBotonChip("Market Place");
        JButton btnTienda   = crearBotonChip("Tienda");
        JButton btnPeticion = crearBotonChip("Crear Peticion");
        JButton btnLogout   = crearBotonChip("Log Out");

        add(btnMarket);
        add(btnTienda);
        add(btnPeticion);
        add(btnLogout);

        // Navegación básica (ajústala a tu gusto):
        // Market Place / Tienda → vista cliente
        btnMarket.addActionListener(e -> ventanaInicio.showPanel("clientHome"));
        btnTienda.addActionListener(e -> ventanaInicio.showPanel("clientHome"));
        // Crear Petición / LogOut los dejamos para conectar más adelante
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