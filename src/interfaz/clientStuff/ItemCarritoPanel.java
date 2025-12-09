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

public class ItemCarritoPanel extends JPanel {

    private String titulo;
    private String subtitulo;
    private String extraInfo;

    public ItemCarritoPanel(String titulo, String subtitulo, String extraInfo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.extraInfo = extraInfo;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        setPreferredSize(new Dimension(850, 90));
        setMaximumSize(new Dimension(850, 110));

        // Primera línea: título + subtítulo en una misma fila
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setOpaque(false);

        JLabel lblTitulo = new JLabel(titulo + "   ");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.DARK_GRAY);
        header.add(lblTitulo);

        JLabel lblSub = new JLabel(subtitulo);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSub.setForeground(Color.DARK_GRAY);
        header.add(lblSub);

        add(header);

        // Extra info (ej: "-Asiento: 1")
        if (extraInfo != null && !extraInfo.isBlank()) {
            add(Box.createRigidArea(new Dimension(0, 4)));
            JLabel lblExtra = new JLabel(extraInfo);
            lblExtra.setFont(new Font("Arial", Font.PLAIN, 13));
            lblExtra.setForeground(new Color(120, 120, 120));
            add(lblExtra);
        }

        add(Box.createVerticalGlue());

        // Botón "Sacar del carrito"
        JButton btnSacar = new JButton("Sacar del Carrito");
        btnSacar.setAlignmentX(LEFT_ALIGNMENT);
        styleButton(btnSacar);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(btnSacar);

        // TODO: aquí puedes agregar el ActionListener real para eliminar del carrito
        // btnSacar.addActionListener(e -> carrito.removerItem(...));
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 32));
        btn.setMaximumSize(new Dimension(150, 32));

        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 1, true));
    }
}
