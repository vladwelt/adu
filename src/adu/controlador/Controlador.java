package adu.controlador;

import adu.modelo.Cliente;
import adu.modelo.Lote;
import adu.modelo.Urbanizacion;
import adu.vista.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    //private VistaUrbanizacion urbanizacion;
    private VistaAdu vista;

    public Controlador(VistaAdu _vista) {
        this.vista = _vista;

        vista.addComboBoxActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int j = vista.getComboBoxSelectionIndex();
                Urbanizacion urbanizacion = vista.getSelectedUrbanizacion();
                if (urbanizacion == null) {
                    JOptionPane.showMessageDialog(vista, "seleccione una urbanizacion por favor");
                    return;
                }
                ArrayList<Lote> lotes = urbanizacion.getLotes();
                String[] columnNames = {
                    "Num Lote",
                    "C.I.",
                    "Nombre",
                    "Apellido Paterno",
                    "Apellido Materno",
                    "Total",
                    "Deuda"};
                Object rowData[][] = new Object[lotes.size()][columnNames.length];
                for (int i = 0; i < rowData.length; i++) {
                    Lote lote = lotes.get(i);
                    rowData[i][0] = lote.getNumero_lote();
                    Cliente cliente = lote.getCliente();
                    if(cliente == null) {
                        rowData[i][1] = "S/N";
                        rowData[i][2] = "S/N";
                        rowData[i][3] = "S/N";
                        rowData[i][4] = "S/N";
                    } else {
                        rowData[i][1] = cliente.getCi();
                        rowData[i][2] = cliente.getNombre();
                        rowData[i][3] = cliente.getApellidoPaterno();
                        rowData[i][4] = cliente.getApellidoMaterno();
                    }
                    rowData[i][5] = lote.getPrecio();
                    rowData[i][6] = lote.getPrecio() - lote.getSumaPagos();
//                    rowData[i][6] = 
                }
                DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
                vista.setTablaLotesMdel(model);
            }
        });
    }

    public void actionPerformed(ActionEvent evt) {
        //  TODO 
    }
}
