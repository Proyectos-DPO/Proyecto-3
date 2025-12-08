package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Autenticador;
import interfaz.adminStuff.AdminHomePanel;
import sesion.SesionAdmin;

public class LoginView extends JPanel implements ActionListener{
	
	private static final String LOGIN = "login";
	private static final String REGISTER = "register";
	private static final String FONT = "Trebuchet MS";
	
	private JButton registerBtn;
	private JButton loginBtn;
	private JTextField txtUsuario;
	private JTextField txtContra;
	
	private VentanaInicio papa;
	
	public LoginView(VentanaInicio papa) {
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300,300));
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));  // Este es un Layout que no vimos en clase, sin embargo existe y averiguamos como usarlo
		
		JLabel userLabel = new JLabel("Usuario");
		userLabel.setFont(new Font(FONT, Font.PLAIN, 12));    //Aesthetic 
		
		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setFont(new Font(FONT, Font.PLAIN, 12)); 
		
		txtUsuario = new JTextField();
		txtContra = new JTextField();
		
		registerBtn = new JButton("Ingresar");
		registerBtn.setFont(new Font(FONT, Font.BOLD, 16));
		registerBtn.addActionListener(this);
        registerBtn.setActionCommand(LOGIN);
		
		loginBtn = new JButton("Registrarse");
		loginBtn.setFont(new Font(FONT, Font.BOLD, 16));
		
		
		formPanel.add(userLabel);
        formPanel.add(txtUsuario);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(passwordLabel);
        formPanel.add(txtContra);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginBtn);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(registerBtn);
        
        add(formPanel, BorderLayout.CENTER);
		this.papa = papa;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
        if( comando.equals( LOGIN ) )
        {
        	try {
				papa.setSesion(Autenticador.iniciarSesion(papa.getDatos(), txtUsuario.getText(), txtContra.getText()));
				if (papa.getSesion() instanceof SesionAdmin) {
				papa.getCardPanel().add(new AdminHomePanel(papa), "adminHome");
				papa.showPanel("adminHome");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
	}
	
}
