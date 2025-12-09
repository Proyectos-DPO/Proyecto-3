package interfaz.Crear;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class CrearDescuento extends JPanel implements ActionListener {

    private static final String SEND = "Send";
    private static final String FONT = "Trebuchet MS";

    private JTextField txtPorcentaje;
    private JSpinner spFechaInicio;
    private JSpinner spHoraInicio;
    private JSpinner spFechaFin;
    private JSpinner spHoraFin;
    private JButton btnCrear;
    private JPanel card;

    public CrearDescuento() {

        setLayout(new BorderLayout());
        setBackground(new Color(243, 243, 243));

        card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(224, 224, 224)),
                        BorderFactory.createEmptyBorder(20, 24, 24, 24)
                )
        );

        
        JLabel lblTitulo = new JLabel("Crear Descuento");
        lblTitulo.setFont(new Font(FONT, Font.BOLD, 22));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(32));

        
        JLabel lblPorcentaje = new JLabel("Porcentaje de descuento:");
        lblPorcentaje.setFont(new Font(FONT, Font.PLAIN, 12));
        lblPorcentaje.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblPorcentaje);
        card.add(Box.createVerticalStrut(4));

        
        txtPorcentaje = new JTextField();
        txtPorcentaje.setFont(new Font(FONT, Font.PLAIN, 12));
        txtPorcentaje.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtPorcentaje.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        txtPorcentaje.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtPorcentaje);
        card.add(Box.createVerticalStrut(24));

        
        JLabel lblInicio = new JLabel("Inicio");
        lblInicio.setFont(new Font(FONT, Font.PLAIN, 12));
        lblInicio.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblInicio);
        card.add(Box.createVerticalStrut(4));

        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new BoxLayout(panelInicio, BoxLayout.X_AXIS));
        panelInicio.setBackground(Color.WHITE);
        panelInicio.setAlignmentX(LEFT_ALIGNMENT);

        // Spinner FECHA INICIO
        spFechaInicio = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        spFechaInicio.setFont(new Font(FONT, Font.PLAIN, 12));
        spFechaInicio.setEditor(new JSpinner.DateEditor(spFechaInicio, "dd/MM/yyyy"));
        spFechaInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        spFechaInicio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));

        // Spinner HORA INICIO
        spHoraInicio = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE));
        spHoraInicio.setFont(new Font(FONT, Font.PLAIN, 12));
        spHoraInicio.setEditor(new JSpinner.DateEditor(spHoraInicio, "HH:mm"));
        spHoraInicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        spHoraInicio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));

        panelInicio.add(spFechaInicio);
        panelInicio.add(Box.createHorizontalStrut(8));
        panelInicio.add(spHoraInicio);

        card.setVisible(true);
        card.add(panelInicio);
        card.add(Box.createVerticalStrut(16));

        // -------- FIN --------
        JLabel lblFin = new JLabel("Fin");
        lblFin.setFont(new Font(FONT, Font.PLAIN, 12));
        lblFin.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblFin);
        card.add(Box.createVerticalStrut(4));

        JPanel panelFin = new JPanel();
        panelFin.setLayout(new BoxLayout(panelFin, BoxLayout.X_AXIS));
        panelFin.setBackground(Color.WHITE);
        panelFin.setAlignmentX(LEFT_ALIGNMENT);

        // Spinner FECHA FIN
        spFechaFin = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        spFechaFin.setFont(new Font(FONT, Font.PLAIN, 12));
        spFechaFin.setEditor(new JSpinner.DateEditor(spFechaFin, "dd/MM/yyyy"));
        spFechaFin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        spFechaFin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));

        // Spinner HORA FIN
        spHoraFin = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE));
        spHoraFin.setFont(new Font(FONT, Font.PLAIN, 12));
        spHoraFin.setEditor(new JSpinner.DateEditor(spHoraFin, "HH:mm"));
        spHoraFin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        spHoraFin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));

        panelFin.add(spFechaFin);
        panelFin.add(Box.createHorizontalStrut(8));
        panelFin.add(spHoraFin);

        card.add(panelFin);
        card.add(Box.createVerticalStrut(24));

        btnCrear = new JButton("L");
        btnCrear.setFont(new Font(FONT, Font.BOLD, 14));
        btnCrear.setFocusPainted(false);
        btnCrear.setBackground(new Color(51,51,51));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnCrear.addActionListener(this);
        btnCrear.setActionCommand(SEND);
        card.add(btnCrear);

        card.setVisible(true);
        add(card, BorderLayout.NORTH);
        
        
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(1020,360);
        d.width = 360; // ancho fijo estilo card
        return d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SEND.equals(e.getActionCommand())) {
          
        }
    }

    public String getPorcentaje() {
        return txtPorcentaje.getText();
    }

    public Date getFechaInicio() {
        return (Date) spFechaInicio.getValue();
    }

    public Date getHoraInicio() {
        return (Date) spHoraInicio.getValue();
    }

    public Date getFechaFin() {
        return (Date) spFechaFin.getValue();
    }

    public Date getHoraFin() {
        return (Date) spHoraFin.getValue();
    }
}
