package adu.controlador;

import adu.modelo.*;
import adu.vista.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;

public class Controlador {

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
                    "Lote",
                    "Manzano",
                    "C.I.",
                    "Nombre",
                    "Apellido Paterno",
                    "Apellido Materno",
                    "Total",
                    "Deuda",
                    "Detalles"};
                Object rowData[][] = new Object[lotes.size()][columnNames.length];
                for (int i = 0; i < rowData.length; i++) {
                    Lote lote = lotes.get(i);
                    rowData[i][0] = lote.getNumero_lote();
                    try {
                    rowData[i][1] = Manzano.getNumeroManzano(lote.getManzanoId());
                    } catch(SQLException e) {
                        System.out.println("NO EXISTE MANZANO");
                        rowData[i][1] = "S/N";
                    }
                    Cliente cliente = lote.getCliente();
                    if(cliente == null) {
                        rowData[i][2] = "S/N";
                        rowData[i][3] = "S/N";
                        rowData[i][4] = "S/N";
                        rowData[i][5] = "S/N";
                    } else {
                        rowData[i][2] = cliente.getCi();
                        rowData[i][3] = cliente.getNombre();
                        rowData[i][4] = cliente.getApellidoPaterno();
                        rowData[i][5] = cliente.getApellidoMaterno();
                    }
                    rowData[i][6] = lote.getPrecio();
                    rowData[i][7] = lote.getDeuda();
                    rowData[i][8] = "Cobrar";
                }
                DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
                vista.setTablaLotesMdel(model,urbanizacion);
            }
        });



        vista.addMenuActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == vista.getMenuAgregarUrbanizacion()) {
                    try {
                        vista.addUrbanizacion();
                    } catch (NumberFormatException e) {
                        vista.alertMessage("LOS DATOS INGRESADOS "
                                +"NO SON CORRECTOS : \n" +
                                e.getMessage());
                    }
                }
            }
        });
    }
}
