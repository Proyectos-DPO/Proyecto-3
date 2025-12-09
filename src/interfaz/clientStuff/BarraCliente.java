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

public class BarraCliente extends JPanel {

    private VentanaInicio ventanaInicio;
    
    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);
    private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    public BarraCliente(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setBackground(Color.WHITE); 
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, CORPORATE_PURPLE));

        
        JButton btnTienda = crearNavButton("Market Place");
        btnTienda.addActionListener(e -> {
        	
            ventanaInicio.showPanel("clientHome");
        });
        
        
        JButton btnCuenta = crearNavButton("Cuenta");
        btnCuenta.addActionListener(e -> {
        	
            System.out.println("Navegar a Perfil");
        });
        
        
        JButton btnPeticion = crearNavButton("Crear Petición");
        btnPeticion.addActionListener(e -> {
        	
            System.out.println("Abrir Crear Petición");
        });

        
        JButton btnCarrito = crearNavButton("Carrito");
        btnCarrito.addActionListener(e -> {
        	
            System.out.println("Ver Carrito");
        });
        
        
        JButton btnLogout = crearNavButton("Log Out");
        btnLogout.addActionListener(e -> {
        	
            ventanaInicio.setSesion(null);
            ventanaInicio.showPanel("login");
        });

        
        add(btnTienda);
        add(btnCuenta);
        add(btnPeticion);
        add(btnCarrito);
        
        btnCarrito.addActionListener(e -> ventanaInicio.mostrarCarrito());
        
        
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new java.awt.Dimension(30, 10));
        spacer.setOpaque(false);
        add(spacer);
        
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