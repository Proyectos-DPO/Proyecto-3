package interfaz.Crear;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrearOferta extends JPanel implements ActionListener{

	private static final String SEND = "Send";
    private static final String FONT = "Trebuchet MS";

    private JTextField valorOferta;
    private JButton btnCrear;

    public CrearOferta() {

        setLayout(new BorderLayout());
        setBackground(new Color(243,243,243));

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(224,224,224)),
                        BorderFactory.createEmptyBorder(20, 24, 24, 24)
                )
        );

  
        JLabel lblTitulo = new JLabel("Crear Oferta");
        lblTitulo.setFont(new Font(FONT, Font.BOLD, 18));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(48));
        
        JLabel lbl = new JLabel("Valor de la Oferta");
        lbl.setFont(new Font(FONT, Font.PLAIN, 12));
        valorOferta = new JTextField();
        valorOferta.setFont(new Font(FONT, Font.PLAIN, 12));
        valorOferta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        valorOferta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221,221,221)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        
        card.add(lbl);
        card.add(Box.createVerticalStrut(4));
        valorOferta.setAlignmentX(LEFT_ALIGNMENT);
        card.add(valorOferta);
        card.add(Box.createVerticalStrut(16));


        btnCrear = new JButton("Crear Oferta");
        btnCrear.setFont(new Font(FONT, Font.BOLD, 14));
        btnCrear.setFocusPainted(false);
        btnCrear.setBackground(new Color(51,51,51));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnCrear.addActionListener(this);
        btnCrear.setActionCommand(SEND);
        card.add(btnCrear);

        btnCrear.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCrear.getPreferredSize().height));

        add(card, BorderLayout.NORTH);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width = 360;
        return d;
    }
    
    
    
	public JTextField getValorOferta() {
		return valorOferta;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if (comando.equals(SEND)) {
			
		}
	}


}
