package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaz.Crear.CrearContraoferta;
import interfaz.Crear.CrearOferta;
import interfaz.Crear.CrearPeticion;
import interfaz.Crear.TransferirPrompt;
import interfaz.adminStuff.AdminHomePanel; // o el paquete donde esté tu panel
//import interfaz.adminStuff.FijarCuotasPrompt;
import interfaz.utils.CrearVenues;

/**
 * Visualizador genérico para cualquier JPanel.
 * Ejecuta este archivo directamente y modifica MANUALMENTE
 * el panel que deseas visualizar en el constructor.
 */
public class VisualizadorPanel extends JFrame {

    public VisualizadorPanel() {

        // 👇👇👇 CAMBIA SOLO ESTA LÍNEA SEGÚN EL PANEL QUE QUIERAS PROBAR 👇👇👇

        // Si tu panel NO necesita padre:
        // JPanel panel = new AdminHomePanel();

        // Si tu panel recibe un "padre" (recomendado):
        JPanel panel = new CrearDescuento();

        // 👆👆👆 CAMBIA AdminHomePanel por la clase que quieras probar 👆👆👆

        setTitle("Preview: " + panel.getClass().getSimpleName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);

        pack();                    // se ajusta al preferredSize del panel
        setLocationRelativeTo(null); // centra la ventana
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        // IMPORTANTE: siempre va con new
        new VisualizadorPanel();
    }
}