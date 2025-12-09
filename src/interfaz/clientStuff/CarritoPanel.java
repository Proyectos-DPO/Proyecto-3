package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import interfaz.VentanaInicio;

public class CarritoPanel extends JPanel {

    private VentanaInicio ventanaInicio;
    private JPanel listPanel;

    public CarritoPanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ----------- BARRA SUPERIOR -----------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblTitulo = new JLabel("  Carrito", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 0));
        topBar.add(lblTitulo, BorderLayout.WEST);

        add(topBar, BorderLayout.NORTH);

        // ----------- LISTA DE ITEMS -----------
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        // Por ahora, datos "dummy" como en el mockup.
        // Luego puedes reemplazar este método por uno que lea de CarritoCompra.
        cargarItemsDummy();

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        // ----------- BOTONES DE PAGO -----------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        bottomPanel.add(Box.createHorizontalGlue());

        JButton btnPasarela = crearBotonPago("Pagar con Pasarela");
        bottomPanel.add(btnPasarela);
        bottomPanel.add(Box.createRigidArea(new Dimension(80, 0)));

        JButton btnSaldo = crearBotonPago("Pagar con Saldo");
        bottomPanel.add(btnSaldo);

        bottomPanel.add(Box.createHorizontalGlue());

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Por ahora llenamos el carrito con tres items "mock" para replicar tu diseño.
     * Más adelante puedes cambiar esto para que lea del CarritoCompra de la sesión.
     */
    private void cargarItemsDummy() {
        // Tiquete individual
        ItemCarritoPanel item1 = new ItemCarritoPanel(
            "Tiquete Individual",
            "Id de Tiquete Individual",
            "-Asiento: 1"
        );
        item1.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(item1);
        listPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Paquete deluxe
        ItemCarritoPanel item2 = new ItemCarritoPanel(
            "Paquete",
            "Id de paquete",
            ""
        );
        item2.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(item2);
        listPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Tiquete múltiple
        ItemCarritoPanel item3 = new ItemCarritoPanel(
            "Tickete Multiple",
            "Id de Tiquete multiple",
            ""
        );
        item3.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(item3);
    }

    private JButton crearBotonPago(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 1, true));
        btn.setPreferredSize(new Dimension(180, 36));
        btn.setMaximumSize(new Dimension(180, 36));
        return btn;
    }
}
