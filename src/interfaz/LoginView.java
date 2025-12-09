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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Autenticador;
import interfaz.adminStuff.AdminHomePanel;
import interfaz.clientStuff.ClientHomePanel;
import interfaz.organizadorStuff.OrganizadorHomePanel;
import sesion.Sesion;
import sesion.SesionAdmin;
import sesion.SesionCliente;
import sesion.SesionOrganizador;

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
        if (comando.equals(LOGIN)) {
            try {
                Sesion sesion = Autenticador.iniciarSesion(
                        papa.getDatos(),
                        txtUsuario.getText(),
                        txtContra.getText()
                );
                papa.setSesion(sesion);

                if (sesion instanceof SesionAdmin) {
                    papa.getCardPanel().add(new AdminHomePanel(papa), "adminHome");
                    papa.showPanel("adminHome");
                }
                else if (sesion instanceof SesionOrganizador) {
                	
                    OrganizadorHomePanel orgPanel = new OrganizadorHomePanel(papa);
                    papa.getCardPanel().add(orgPanel, "organizadorHome");

                    
                    ClientHomePanel clientPanel = new ClientHomePanel(papa);
                    papa.getCardPanel().add(clientPanel, "clientHome");

                    
                    papa.showPanel("organizadorHome");
                }
                
                else if (sesion instanceof SesionCliente) {
                    ClientHomePanel clientPanel = new ClientHomePanel(papa);
                    papa.getCardPanel().add(clientPanel, "clientHome");
                    papa.showPanel("clientHome");
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error al ingresar: " + e1.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                e1.printStackTrace();
            }
        }
    }
}