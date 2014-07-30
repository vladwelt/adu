package adu.controlador;

import adu.vista.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controlador implements ActionListener {
    //private VistaUrbanizacion urbanizacion;
    private VistaAdu vista;

    public Controlador(VistaAdu _vista) {
        this.vista = _vista;
        

        vista.addComboBoxActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                int i = vista.getComboBoxSelectionIndex();
                System.out.println("numero : "+i);
            }
        });
    }
    
    public void actionPerformed(ActionEvent evt) {
        //  TODO 
    }
}
