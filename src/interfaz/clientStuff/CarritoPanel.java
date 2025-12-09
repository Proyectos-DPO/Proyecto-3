package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaz.VentanaInicio;

public class CarritoPanel extends JPanel {

    private VentanaInicio ventanaInicio;
    private JPanel listPanel;

    public CarritoPanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        
        BarraCarrito barra = new BarraCarrito(ventanaInicio);
        add(barra, BorderLayout.NORTH);

        
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        
        cargarItemsDummy();

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        
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

    
    private void cargarItemsDummy() {
    	
        ItemCarritoPanel item1 = new ItemCarritoPanel(
            "Tiquete Individual",
            "Id de Tiquete Individual",
            "-Asiento: 1"
        );
        item1.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(item1);
        listPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        
        ItemCarritoPanel item2 = new ItemCarritoPanel(
            "Paquete",
            "Id de paquete",
            ""
        );
        item2.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.add(item2);
        listPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        
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