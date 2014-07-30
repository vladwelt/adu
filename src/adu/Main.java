package adu;

import adu.controlador.*;
import adu.vista.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                UIManager.put("swing.boldMetal",Boolean.FALSE);
                VistaAdu adu = new VistaAdu("Administrador de Urbanizaciones");
                Controlador cont = new Controlador(adu);
                adu.runTest();
            }
        });
    }
}
