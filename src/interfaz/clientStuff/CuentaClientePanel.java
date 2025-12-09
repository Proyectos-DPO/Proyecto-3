package interfaz.clientStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Datos;
import interfaz.VentanaInicio;
import sesion.Sesion;
import sesion.SesionCliente;
import tiquetes.Tiquete;
import tiquetes.TiqueteIndividual;
import usuarios.Cliente;
import usuarios.Usuario;

public class CuentaClientePanel extends JPanel {

    private final VentanaInicio ventanaInicio;
    private final Color CORPORATE_PURPLE = new Color(120, 81, 169);

    public CuentaClientePanel(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        header.setPreferredSize(new Dimension(100, 70));

        JLabel lblLogo = new JLabel("Ꝃ");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 24));
        lblLogo.setForeground(Color.BLACK);

        JLabel lblTitulo = new JLabel(" Mi Cuenta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.BLACK);

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.X_AXIS));
        left.add(lblLogo);
        left.add(lblTitulo);

        header.add(Box.createRigidArea(new Dimension(20, 0)));
        header.add(left);
        header.add(Box.createHorizontalGlue());

        header.add(crearChipButton("Mis ofertas"));
        header.add(Box.createRigidArea(new Dimension(12, 0)));

        header.add(crearChipButton("Administrar Peticiones"));
        header.add(Box.createRigidArea(new Dimension(12, 0)));

        header.add(crearChipButton("Fijar Cuotas"));
        header.add(Box.createRigidArea(new Dimension(12, 0)));

        JButton btnLogout = crearChipButton("Log Out");
        btnLogout.addActionListener(e -> {
            ventanaInicio.setSesion(null);
            ventanaInicio.showPanel("login");
        });
        header.add(btnLogout);
        header.add(Box.createRigidArea(new Dimension(20, 0)));

        add(header, BorderLayout.NORTH);

        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(Color.WHITE);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 224, 224), 1, true),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel lblTiquetes = new JLabel("Tiquetes");
        lblTiquetes.setFont(new Font("Arial", Font.BOLD, 16));
        lblTiquetes.setForeground(new Color(80, 80, 80));
        card.add(lblTiquetes, BorderLayout.NORTH);

        JPanel listaTickets = new JPanel();
        listaTickets.setLayout(new BoxLayout(listaTickets, BoxLayout.Y_AXIS));
        listaTickets.setBackground(Color.WHITE);
        listaTickets.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));

        cargarTiquetesCliente(listaTickets);

        JScrollPane scroll = new JScrollPane(listaTickets);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        card.add(scroll, BorderLayout.CENTER);
        contentWrapper.add(card, BorderLayout.CENTER);
        add(contentWrapper, BorderLayout.CENTER);
    }

    private void cargarTiquetesCliente(JPanel listaTickets) {
        Sesion sesion = ventanaInicio.getSesion();
        if (!(sesion instanceof SesionCliente)) {
            JLabel lbl = new JLabel("No hay un cliente autenticado.");
            lbl.setFont(new Font("Arial", Font.ITALIC, 13));
            listaTickets.add(lbl);
            return;
        }

        String login = sesion.getLogin();
        Datos datos = ventanaInicio.getDatos();
        Usuario usuario = datos.getUsuario(login);

        if (!(usuario instanceof Cliente)) {
            JLabel lbl = new JLabel("El usuario actual no es un cliente.");
            lbl.setFont(new Font("Arial", Font.ITALIC, 13));
            listaTickets.add(lbl);
            return;
        }

        Cliente cliente = (Cliente) usuario;
        if (cliente.getTiquetes() == null || cliente.getTiquetes().isEmpty()) {
            JLabel lbl = new JLabel("Aún no tienes tiquetes.");
            lbl.setFont(new Font("Arial", Font.ITALIC, 13));
            listaTickets.add(lbl);
            return;
        }

        for (Map.Entry<String, Tiquete> entry : cliente.getTiquetes().entrySet()) {
            Tiquete t = entry.getValue();

            if (t instanceof TiqueteIndividual) {
                TiqueteIndividual ti = (TiqueteIndividual) t;

                TicketCuentaPanel ticketPanel =
                        new TicketCuentaPanel(
                                ti.getId(),                             // id del tiquete
                                "-Asiento: " + ti.getIdAsiento(),       // texto de asiento
                                ti,
                                ventanaInicio                           // para abrir el QR
                        );
                ticketPanel.setAlignmentX(LEFT_ALIGNMENT);
                listaTickets.add(ticketPanel);
                listaTickets.add(Box.createRigidArea(new Dimension(0, 12)));
            }
        }
    }

    private JButton crearChipButton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(CORPORATE_PURPLE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return btn;
    }
}