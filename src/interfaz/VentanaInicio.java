package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import data.Datos;
import eventos.Evento;
import interfaz.clientStuff.LocalidadesEventoPanel;
import persistencia.Persistencia;
import sesion.Sesion;
import interfaz.clientStuff.CarritoPanel;

public class VentanaInicio extends JFrame {

    private CardPanel cardPanel;
    private Datos datos;
    private Sesion sesion;

    public VentanaInicio() {

        try {
            datos = Persistencia.cargarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("BoletaMaster");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardPanel = new CardPanel();

        // Panel inicial: login
        cardPanel.add(new LoginView(this), "login");

        add(cardPanel);
        showPanel("login");
        pack();
        setVisible(true);
    }

    public void showPanel(String nombre) {
        cardPanel.showCard(nombre);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void mostrarCarrito() {
        CarritoPanel carritoPanel = new CarritoPanel(this);
        cardPanel.add(carritoPanel, "carrito");
        showPanel("carrito");
    }

    /**
     * Muestra el panel de localidades para el evento dado,
     * reutilizando el mismo CardPanel (igual que login / tienda).
     */
    public void mostrarLocalidadesEvento(Evento evento) {
        LocalidadesEventoPanel panelLocalidades = new LocalidadesEventoPanel(this, evento);
        cardPanel.add(panelLocalidades, "localidadesEvento");
        showPanel("localidadesEvento");
    }

    public CardPanel getCardPanel() {
        return cardPanel;
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("No se pudo establecer Nimbus Look and Feel: " + e.getMessage());
        }

        new VentanaInicio();
    }
}
