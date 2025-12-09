package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.zxing.common.BitMatrix;

import interfaz.VentanaInicio;
import managers.TiqueteManager;
import tiquetes.TiqueteIndividual;

public class VisualizarQRPanel extends JPanel {

    private final VentanaInicio ventanaInicio;
    private final TiqueteIndividual tiquete;
    private final JDialog capsula;

    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);

    public VisualizarQRPanel(VentanaInicio ventanaInicio,
                             TiqueteIndividual tiquete,
                             JDialog capsula) {
        this.ventanaInicio = ventanaInicio;
        this.tiquete = tiquete;
        this.capsula = capsula;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        construirHeader();
        construirContenido();
    }

    private void construirHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblLogo = new JLabel("Ꝃ");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblTitulo = new JLabel(" Visualización QR");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.X_AXIS));
        left.add(lblLogo);
        left.add(lblTitulo);

        header.add(Box.createRigidArea(new Dimension(20, 0)));
        header.add(left);
        header.add(Box.createHorizontalGlue());
        
        header.add(crearChip("Crear Peticion"));
        header.add(Box.createRigidArea(new Dimension(10, 0)));
        header.add(crearChip("Crear Peticion")); // segundo chip como en el mockup
        header.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton btnLogout = crearChip("Log Out");
        btnLogout.addActionListener(e -> {
            ventanaInicio.setSesion(null);
            capsula.dispose();
            ventanaInicio.showPanel("login");
        });
        header.add(btnLogout);
        header.add(Box.createRigidArea(new Dimension(20, 0)));

        add(header, BorderLayout.NORTH);
    }

    private JButton crearChip(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(CORPORATE_PURPLE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return btn;
    }

    private void construirContenido() {
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));

        JLabel qrLabel = new JLabel();
        try {
            BitMatrix matrix = TiqueteManager.generarImagenTiquete(
                    ventanaInicio.getDatos(), tiquete);
            BufferedImage img = bitMatrixToBufferedImage(matrix);
            qrLabel.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
            qrLabel.setText("Error generando QR");
        }

        qrLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
        content.add(qrLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        String nombre = "Tiquete";
        try {
            if (tiquete.getLocalidadTiquete() != null) {
                nombre = tiquete.getLocalidadTiquete().getNombre();
            }
        } catch (Exception ignored) {}

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 22));
        lblNombre.setAlignmentX(LEFT_ALIGNMENT);
        infoPanel.add(lblNombre);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        JLabel lblBadge = new JLabel("Precio por Tiquete");
        lblBadge.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBadge.setOpaque(true);
        lblBadge.setBackground(new Color(198, 239, 206)); // verde suave
        lblBadge.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        lblBadge.setAlignmentX(LEFT_ALIGNMENT);
        infoPanel.add(lblBadge);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        double precio = 0;
        try {
            precio = tiquete.getPrecioVenta();
        } catch (Exception ignored) {}

        JLabel lblPrecio = new JLabel("$" + (int) precio);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 32));
        lblPrecio.setAlignmentX(LEFT_ALIGNMENT);
        infoPanel.add(lblPrecio);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        JLabel lblDesc = new JLabel("Descripcion del Tiquete");
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(110, 110, 110));
        lblDesc.setAlignmentX(LEFT_ALIGNMENT);
        infoPanel.add(lblDesc);

        content.add(infoPanel);
        add(content, BorderLayout.CENTER);
    }

    private BufferedImage bitMatrixToBufferedImage(BitMatrix matrix) {
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int onColor  = Color.BLACK.getRGB();
        int offColor = Color.WHITE.getRGB();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
            }
        }
        return image;
    }
}