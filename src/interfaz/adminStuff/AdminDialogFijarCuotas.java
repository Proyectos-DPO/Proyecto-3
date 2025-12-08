package interfaz.adminStuff;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import interfaz.VentanaInicio;
import sesion.SesionAdmin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDialogFijarCuotas extends JDialog implements ActionListener {

    private JTextField txtNuevoValor;
    private JComboBox<String> comboCuotas;
    private static final String FIJAR = "El fijador de cabello es malo a largo plazo? Siempre tuve la duda.";
    private VentanaInicio papa;
    
    public AdminDialogFijarCuotas(VentanaInicio papa) {
    	this.papa = papa;
        setTitle("Fijar Cuotas");  // modal

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(20, 30, 20, 30));
        container.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Fijar Cuotas");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(titulo);

        container.add(Box.createVerticalStrut(20));

        JLabel lblNuevoValor = new JLabel("Nuevo Valor");
        lblNuevoValor.setFont(lblNuevoValor.getFont().deriveFont(14f));
        lblNuevoValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(lblNuevoValor);

        txtNuevoValor = new JTextField();
        txtNuevoValor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtNuevoValor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        container.add(txtNuevoValor);

        container.add(Box.createVerticalStrut(20));

        JLabel lblCuotaCambiar = new JLabel("Cuota a Cambiar");
        lblCuotaCambiar.setFont(lblCuotaCambiar.getFont().deriveFont(14f));
        lblCuotaCambiar.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(lblCuotaCambiar);

        comboCuotas = new JComboBox<>(new String[]{
                "Evento Cultural",
                "Evento Deportivo",
                "Evento Religioso",
                "Evento Musical",
                "Cuota Emision"
        });

        comboCuotas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        comboCuotas.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        container.add(comboCuotas);

        container.add(Box.createVerticalStrut(30));

        JButton btnFijar = new JButton("Fijar Cuota");
        btnFijar.setPreferredSize(new Dimension(200, 45));
        btnFijar.setBackground(new Color(45, 45, 45));
        btnFijar.setForeground(Color.WHITE);
        btnFijar.setFocusPainted(false);
        btnFijar.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnFijar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnFijar.setActionCommand(FIJAR);
        btnFijar.addActionListener(this);
        
        container.add(btnFijar);

        add(container, BorderLayout.CENTER);

        pack();
        setSize(380, 380);
        setLocationRelativeTo(papa);
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		SesionAdmin sesion = (SesionAdmin) papa.getSesion();
		switch(e.getActionCommand()) {
		case FIJAR:
			String[] lol = comboCuotas.getSelectedItem().toString().split(" ");
			if (lol[0].equals("Evento")) {
				
				sesion.fijarPorcentajeServicio(Double.valueOf(txtNuevoValor.getText()), lol[1]);
			} else {
				sesion.fijarCuotaFijaEmision(Double.valueOf(txtNuevoValor.getText()));
			}
			this.dispose();
			break;
		}
		
	}
}

