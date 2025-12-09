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

<<<<<<< HEAD
        JPanel panel = new CrearOferta();
=======
        // 👇👇👇 CAMBIA SOLO ESTA LÍNEA SEGÚN EL PANEL QUE QUIERAS PROBAR 👇👇👇

        // Si tu panel NO necesita padre:
        // JPanel panel = new AdminHomePanel();

        // Si tu panel recibe un "padre" (recomendado):
        JPanel panel = new CrearDescuento();
>>>>>>> branch 'master' of git@github.com:Proyectos-DPO/Proyecto-3.git

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