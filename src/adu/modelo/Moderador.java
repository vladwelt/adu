package adu.modelo;

import java.lang.Runtime;

public class Moderador {

    private String userName;
    private String password;
    private String bdName;

    public Moderador(String _userName, String _password) {
        this.userName = _userName;
        this.password = _password;
        this.bdName = "adu";
    }

    public boolean backupDB(String path) {
         
        String executeCmd = "mysqldump -u " + userName + " -p" + password + " --add-drop-database -B " + bdName + " -r " + path + ".sql";
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean restoreDB(String source) {

        String[] executeCmd = new String[]{"mysql", "--user=" + userName, "--password=" + password, "-e", "source "+source};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }                         
        return false;
    }
}
