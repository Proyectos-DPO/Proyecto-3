package interfaz;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class CardPanel extends JPanel {

    private CardLayout layout;

    public CardPanel() {
        layout = new CardLayout();
        setLayout(layout);
    }



    public void showCard(String name) {
        layout.show(this, name);
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        // Devolver el tamaño preferido de la carta visible
        for (Component c : getComponents()) {
            if (c.isVisible()) {
                return c.getPreferredSize();
            }
        }
        return super.getPreferredSize();
    }
}
