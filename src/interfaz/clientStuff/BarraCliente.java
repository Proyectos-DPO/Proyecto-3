package interfaz.clientStuff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import interfaz.VentanaInicio;

/**
 * Barra de navegación superior exclusiva para usuarios tipo Cliente.
 * Mantiene el estilo visual de BarraAdmin pero con opciones de Cliente.
 */
public class BarraCliente extends JPanel {

    private VentanaInicio ventanaInicio;

    // Color morado corporativo consistente con el diseño
    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);
    private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    public BarraCliente(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        // Configuración del panel (FlowLayout alineado a la izquierda)
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setBackground(Color.WHITE); 
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CORPORATE_PURPLE));

        // --- Botones de Navegación ---
        
        // 1. Market Place (Tienda)
        JButton btnTienda = crearNavButton("Market Place");
        btnTienda.addActionListener(e -> {
            // Navegar al panel principal de la tienda
            ventanaInicio.showPanel("clientHome");
        });

        // 2. Cuenta / Perfil
        JButton btnCuenta = crearNavButton("Cuenta");
        btnCuenta.addActionListener(e -> {
            // TODO: Implementar navegación al perfil
            System.out.println("Navegar a Perfil");
        });
        
        // 3. Crear Petición
        JButton btnPeticion = crearNavButton("Crear Petición");
        btnPeticion.addActionListener(e -> {
            // TODO: Mostrar diálogo o panel de petición
            System.out.println("Abrir Crear Petición");
        });

        // 4. Carrito
        JButton btnCarrito = crearNavButton("Carrito");
        btnCarrito.addActionListener(e -> {
            // TODO: Navegar al carrito
            System.out.println("Ver Carrito");
        });
        
        // 5. Log Out (Visualmente distinto o al final)
        JButton btnLogout = crearNavButton("Log Out");
        btnLogout.setBackground(Color.GRAY); // Color diferente para distinguir la acción de salida
        btnLogout.addActionListener(e -> {
            // Cerrar sesión
            ventanaInicio.setSesion(null);
            ventanaInicio.showPanel("login");
        });

        // Añadir botones al panel en orden
        add(btnTienda);
        add(btnCuenta);
        add(btnPeticion);
        add(btnCarrito);
        
        // Separador visual antes del logout
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new java.awt.Dimension(30, 10));
        spacer.setOpaque(false);
        add(spacer);
        
        add(btnLogout);
    }

    /**
     * Helper para crear botones con el estilo visual unificado.
     */
    private JButton crearNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(CORPORATE_PURPLE);
        button.setFocusPainted(false); // Quitar el borde de foco al hacer clic
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding interno
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return button;
    }
}