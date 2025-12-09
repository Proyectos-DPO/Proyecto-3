package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaz.Crear.CrearContraoferta;
import interfaz.Crear.CrearOferta;
import interfaz.Crear.CrearPeticion;
import interfaz.Crear.TransferirPrompt;
import interfaz.adminStuff.AdminHomePanel;
import interfaz.utils.CrearVenues;

public class VisualizadorPanel extends JFrame {

    public VisualizadorPanel() {


        
        JPanel panel = new CrearDescuento();

        setTitle("Preview: " + panel.getClass().getSimpleName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new VisualizadorPanel();
    }
}