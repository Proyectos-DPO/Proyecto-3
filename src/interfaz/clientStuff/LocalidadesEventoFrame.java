package interfaz.clientStuff;

import javax.swing.JFrame;

import eventos.Evento;

public class LocalidadesEventoFrame extends JFrame {

    public LocalidadesEventoFrame(Evento evento) {
        setTitle("Localidades - " + evento.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        setContentPane(new LocalidadesEventoPanel(evento));
    }
}
