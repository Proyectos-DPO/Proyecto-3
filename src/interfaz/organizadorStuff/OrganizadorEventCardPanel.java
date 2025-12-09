package interfaz.organizadorStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaInicio;


public class OrganizadorEventCardPanel extends JPanel {

    private String nombreEvento;
    private String gananciasTexto;
    private VentanaInicio ventanaInicio;

    public OrganizadorEventCardPanel(VentanaInicio ventanaInicio,
                                     String nombreEvento,
                                     String gananciasTexto) {
        this.ventanaInicio = ventanaInicio;
        this.nombreEvento = nombreEvento;
        this.gananciasTexto = gananciasTexto;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        setPreferredSize(new Dimension(900, 110));
        setMaximumSize(new Dimension(900, 130));

        JLabel lblTitulo = new JLabel(nombreEvento);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.DARK_GRAY);
        add(lblTitulo);

        add(Box.createRigidArea(new Dimension(0, 4)));

        JLabel lblGanancias = new JLabel(gananciasTexto);
        lblGanancias.setFont(new Font("Arial", Font.PLAIN, 14));
        lblGanancias.setForeground(new Color(100, 100, 100));
        add(lblGanancias);

        add(Box.createVerticalGlue());

        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));

        JButton btnLocalidades = crearBotonPlano("Administrar Localidades");
        JButton btnDescuento   = crearBotonPlano("Crear Descuento");

        btnLocalidades.addActionListener(e -> ventanaInicio.mostrarLocalidadesOrganizador());

        botonesPanel.add(btnLocalidades);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(btnDescuento);

        add(Box.createRigidArea(new Dimension(0, 12)));
        add(botonesPanel);

    }

    private JButton crearBotonPlano(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 1, true));
        btn.setPreferredSize(new Dimension(170, 32));
        btn.setMaximumSize(new Dimension(170, 32));
        return btn;
    }
}