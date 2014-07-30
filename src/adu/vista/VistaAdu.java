package adu.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaAdu extends JFrame {
    
    //private VistaUrbanizacion[] panel_urbanizaciones;
    //private Urbanizacion[] urbanizaciones;
    private VistaUrbanizacion vista;
    private JButton button_add;
    private JTabbedPane panels;
    private JComboBox lista_urbanizaciones;

    public VistaAdu(String nombre) {
        super(nombre);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //urbanizaciones = new VistaUrbanizacion[];
        
        button_add = new JButton("Agregar");
        
        lista_urbanizaciones = new JComboBox();
        for (int i = 0; i<5; i++) {
            lista_urbanizaciones.addItem("Urbanizacion" + i);
        }
        
        lista_urbanizaciones.setSelectedIndex(0);
        setLayout(new BorderLayout());
//        addViewUrbanizaciones();
        panels = new JTabbedPane();
        vista = new VistaUrbanizacion();
        add(lista_urbanizaciones,BorderLayout.NORTH);
        add(panels,BorderLayout.CENTER);
    }
    
    public void runTest() {
        panels.removeAll();
        for (int i=0; i < 5; i++) {
            String nombre = "Tab" + i;
            panels.add(nombre, new VistaUrbanizacion());
            iniciarTabComponent(i);
        }

        setSize(new Dimension(400,200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addComboBoxActionListener(ActionListener listener) {
        lista_urbanizaciones.addActionListener(listener);
    
    }

    public int getComboBoxSelectionIndex() {
        return lista_urbanizaciones.getSelectedIndex();
    }

    private void iniciarTabComponent(int i) {
        panels.setTabComponentAt(i,new ButtonTabComponent(panels));
    }

    private void fill_urbanizaciones() {
        //TODO 
    }

    private void addViewUrbanizaciones() {
        //TODO
        //for(int i=0; i<urbanizaciones.lenght;i++) {
        //    panel_urbanizaciones[i] = new VistaUrbanizacion
        //        (urbanizaciones[i]);
        //    panels.add(urbanizaciones[i].getName(),
        //            panel_urbanizaciones[i]);
        //}
    }
}
