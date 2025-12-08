package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Importante para mensajes de error
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Autenticador;
import interfaz.adminStuff.AdminHomePanel;
import interfaz.clientStuff.ClientHomePanel; // NUEVO: Importar el panel del cliente
import sesion.SesionAdmin;
import sesion.SesionCliente; // NUEVO: Importar la sesión del cliente

public class LoginView extends JPanel implements ActionListener {
	
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
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		
		JLabel userLabel = new JLabel("Usuario");
		userLabel.setFont(new Font(FONT, Font.PLAIN, 12));
		
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
		String comando = e.getActionCommand();
        if( comando.equals( LOGIN ) )
        {
        	try {
        		// 1. Intentar iniciar sesión
				papa.setSesion(Autenticador.iniciarSesion(papa.getDatos(), txtUsuario.getText(), txtContra.getText()));
				
				// 2. Verificar si es ADMIN
				if (papa.getSesion() instanceof SesionAdmin) {
					papa.getCardPanel().add(new AdminHomePanel(papa), "adminHome");
					papa.showPanel("adminHome");
				}
				// 3. NUEVO: Verificar si es CLIENTE (o Organizador, ya que hereda de Cliente)
				else if (papa.getSesion() instanceof SesionCliente) {
					// Instanciamos el panel de la tienda
					ClientHomePanel clientPanel = new ClientHomePanel(papa);
					
					// Lo agregamos a las "cartas" del CardLayout
					papa.getCardPanel().add(clientPanel, "clientHome");
					
					// Mostramos el panel
					papa.showPanel("clientHome");
				}
				
			} catch (Exception e1) {
				// Es buena práctica mostrar el error en una ventanita
				JOptionPane.showMessageDialog(this, "Error al ingresar: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
        }
	}
	
}