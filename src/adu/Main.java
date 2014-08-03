package adu;

import adu.controlador.*;
import adu.vista.*;
import javax.swing.*;
import org.jvnet.substance.SubstanceLookAndFeel;

public class Main {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(false);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin");
//                UIManager.put("swing.boldMetal",Boolean.FALSE);
                VistaAdu adu = new VistaAdu("Administrador de Urbanizaciones");
                Controlador cont = new Controlador(adu);
                adu.runTest();
            }
        });
    }
}
