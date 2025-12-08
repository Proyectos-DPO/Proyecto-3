package interfaz.adminStuff;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import interfaz.VentanaInicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AdminHomePanel extends JPanel {
	
	private VentanaInicio papa;

    public AdminHomePanel(VentanaInicio papa) {
    	
    	this.papa = papa;
    	
        setLayout(new BorderLayout());
        setBackground(new Color(245,245,245));

        BarraAdmin topBar = new BarraAdmin(papa);

        add(topBar, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1,3,10,10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        centerPanel.setBackground(new Color(245,245,245));
        
        TreeMap<LocalDate, Double> porFecha = papa.getDatos().getGananciasBoleteria().getPorFecha();
        
        int counter = 0;
        Object[][] matricitaFecha = new Object[porFecha.size()][2];
        for(Entry<LocalDate, Double> lol :porFecha.entrySet()) {
        	matricitaFecha[counter][0]= lol.getKey().toString();
        	matricitaFecha[counter][1] = lol.getValue();
        	counter++;
        	
        }
        
        centerPanel.add(
            createTablePanel("Ganancias por Fecha",
                    new String[]{"Fecha","Monto"},
                    matricitaFecha)
        );
        
        TreeMap<String, Double> porEvento = papa.getDatos().getGananciasBoleteria().getPorEvento();
        
        counter = 0;
        Object[][] matricitaEvento = new Object[porEvento.size()][2];
        for(Entry<String, Double> lol :porEvento.entrySet()) {
        	matricitaEvento[counter][0]= lol.getKey();
        	matricitaEvento[counter][1] = lol.getValue();
        	counter++;
        	
        }
        
        centerPanel.add(
            createTablePanel("Ganancias por Evento",
                    new String[]{"Evento","Monto"},
                    matricitaEvento)
        );
        
        TreeMap<String, Double> porOrganizador = papa.getDatos().getGananciasBoleteria().getPorOrganizador();
        
        counter = 0;
        Object[][] matricitaOrganizador = new Object[porOrganizador.size()][2];
        for(Entry<String, Double> lol :porOrganizador.entrySet()) {
        	matricitaOrganizador[counter][0]= lol.getKey();
        	matricitaOrganizador[counter][1] = lol.getValue();
        	counter++;
        	
        }
        
        centerPanel.add(
            createTablePanel("Ganancias por Organizador",
                    new String[]{"Organizador","Monto"},
                    matricitaOrganizador)
        );

        add(centerPanel, BorderLayout.CENTER);
    }

    

    private JPanel createTablePanel(String title, String[] columns, Object[][] data){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setBackground(Color.WHITE);

        JTable table = new JTable(new DefaultTableModel(data, columns));

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
}

