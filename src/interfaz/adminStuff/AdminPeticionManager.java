package interfaz.adminStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import interfaz.VentanaInicio;
import peticiones.Peticion;
import sesion.SesionAdmin;

public class AdminPeticionManager extends JPanel {

    private final VentanaInicio papa;

    public AdminPeticionManager(VentanaInicio papa) {
        this.papa = papa;

        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel topBar = new BarraAdmin(papa);
        add(topBar, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerWrapper.setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Peticiones");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        centerWrapper.add(titulo, BorderLayout.NORTH);

        JPanel listaPeticiones = new JPanel();
        listaPeticiones.setLayout(new BoxLayout(listaPeticiones, BoxLayout.Y_AXIS));
        listaPeticiones.setBackground(new Color(245, 245, 245));

        SesionAdmin sesion = (SesionAdmin) papa.getSesion();


        ArrayList<Peticion> peticionesUgU = sesion.administrarPeticiones(papa.getDatos());
        
        for (Peticion pet: peticionesUgU) {
        	
        	listaPeticiones.add(createPeticionCard(pet.getTipoPeticion()+": "+pet.getId()));
        	listaPeticiones.add(Box.createVerticalStrut(10));
        }
        
        
        JScrollPane scroll = new JScrollPane(listaPeticiones);
        scroll.setBorder(new LineBorder(new Color(230, 230, 230)));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        centerWrapper.add(scroll, BorderLayout.CENTER);

        add(centerWrapper, BorderLayout.CENTER);
    }

    private JPanel createPeticionCard(String textoPeticion) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220)),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblTexto = new JLabel(textoPeticion);
        lblTexto.setFont(lblTexto.getFont().deriveFont(14f));
        card.add(lblTexto, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botonesPanel.setOpaque(false);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        botonesPanel.add(btnAceptar);
        botonesPanel.add(btnCancelar);

        card.add(botonesPanel, BorderLayout.SOUTH);

        return card;
    }
}
