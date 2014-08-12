package adu.modelo;

import java.lang.Runtime;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Moderador {

    private String userName;
    private String password;
    private String bdName;
    private JFileChooser fileChooser;

    public Moderador(String _userName, String _password) {
        fileChooser = new JFileChooser();
        this.userName = _userName;
        this.password = _password;
        this.bdName = "adu";
    }

    public boolean backupDB() {
        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(new JFrame());
        File file = new File("");
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        } else {
            return false;
        }
        String path = file.getAbsolutePath();
        String executeCmd = "mysqldump -u " + userName + " -p" + password + " --add-drop-database -B " + bdName + " -r " + path + ".sql";
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                showInformation("Base de Datos Recuperada\n"
                        + "DIR: "+path);
                return true;
            } else {
                showError("NO SE RECUPERO LA BASE DE DATOS!!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean restoreDB() {

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(new JFrame());
        File file = new File("");
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        else {
            System.exit(0);
        }
        
        String source = file.getAbsolutePath();

        String[] executeCmd = new String[]{"mysql", "--user=" + userName, "--password=" + password, "-e", "source "+source};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                showInformation("Base de Datos Cargada \n" 
                        + "DE: " +source);
                return true;
            } else {
                showError("No se pudo recuperar la base de datos \n" 
                        + "POSIBLE ERROR: " + source);;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"Debe Reiniciar la \n"
                +"aplicacion para ver los cambios", "REINICIE!!"
                ,JOptionPane.WARNING_MESSAGE);
        return false;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null,
                    message, "ERROR!!!!!", JOptionPane.ERROR_MESSAGE);

    }

    public void showInformation(String message) {
        JOptionPane.showMessageDialog( null,
                message,"EXITO",JOptionPane.INFORMATION_MESSAGE);
    }
}
