package interfaz.Crear;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CrearPeticion extends JPanel implements ActionListener {

	private static final String SEND = "Send";
    private static final String FONT = "Trebuchet MS";
	
    private JButton btnCrear;
    
	public CrearPeticion() {
		
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

  
        JLabel lblTitulo = new JLabel("Crear Peticion");
        lblTitulo.setFont(new Font(FONT, Font.BOLD, 18));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(48));
        
        btnCrear = new JButton("Enviar Peticion");
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
