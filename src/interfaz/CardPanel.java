package interfaz;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class CardPanel extends JPanel {

    private CardLayout layout;
    private Map<String, JPanel> mapitaUwU;
    

    public CardPanel() {
        layout = new CardLayout();
        mapitaUwU = new HashMap<String, JPanel>();
        setLayout(layout);
    }

    @Override
	public void add(Component comp, Object constraints) {
		super.add(comp, constraints);
		mapitaUwU.put(constraints.toString(),(JPanel) comp);
	}
    
    public boolean isCardPresent(String name) {
    	return mapitaUwU.containsKey(name);
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
