package adu.vista;

import javax.swing.*;
import java.awt.*;

class VistaAdu extends JFrame {
    
    //private VistaUrbanizacion[] panel_urbanizaciones;
    //private Urbanizacion[] urbanizaciones;
    private VistaUrbanizacion vista;
    private JButton button_add;
    private JTabbedPane panels;

    public VistaAdu(String nombre) {
        super(nombre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //urbanizaciones = new VistaUrbanizacion[];
        button_add = new JButton("Agregar");
//        addViewUrbanizaciones();
        panels = new JTabbedPane();
        vista = new VistaUrbanizacion();
        add(panels);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                UIManager.put("swing.boldMetal",Boolean.FALSE);
                new VistaAdu("Administrador de Urbanizaciones").runTest();
            }
        });
    }
}
