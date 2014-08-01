package adu.vista;

import adu.modelo.Cliente;
import adu.modelo.Lote;
import adu.modelo.Pago;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
import java.lang.Integer;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.*;

class VistaUrbanizacion extends JPanel {

    //private Urbanizacion urbanizacion;
    private JTextField label_find;
    private JComboBox<String> cb_tipo_busqueda;
    private JTable tabla;
    private JTable tabla_cliente;
    private PnPagos pnPagos;
    private TableRowSorter<DefaultTableModel> sorter;
    
    public VistaUrbanizacion() {
        //this.urbanizacion = _urbanizacion;
        label_find = new JTextField();
        cb_tipo_busqueda = new JComboBox<>(new String[]{"Nombre", "Apellido Paterno", "Apellido Materno"});
        cb_tipo_busqueda.setSelectedIndex(0);
        pnPagos = new PnPagos();
        tabla = new JTable();
        tabla_cliente = new JTable(new MyTableModel2());

        tabla_cliente.setPreferredScrollableViewportSize(new Dimension(200, 20));
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);

        tabla_cliente.setFillsViewportHeight(true);
        tabla_cliente.setAutoCreateRowSorter(true);
        tabla_cliente.setRowHeight(20);
        tabla_cliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(tabla);
        JScrollPane scrollPane1 = new JScrollPane(tabla_cliente,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        label_find.setColumns(50);

        //Filtrado de datos
        label_find.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        filterText();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        filterText();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        filterText();
                    }
                });
        
        panel.add(label_find, BorderLayout.CENTER);
        panel.add(cb_tipo_busqueda, BorderLayout.WEST);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane1, BorderLayout.SOUTH);
        add(pnPagos, BorderLayout.EAST);
    }
    private DefaultTableModel lotesModel;

    public VistaUrbanizacion(DefaultTableModel model) {
        this();
        tabla.setModel(model);
        this.lotesModel = model;

        sorter = new TableRowSorter<DefaultTableModel>(model);
        tabla.setRowSorter(sorter);
        tabla.setFillsViewportHeight(true);

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int indice = tabla.getSelectedRow();
                if (indice < 0) {
                    System.out.println("indice negativo seleccionado de la tabla de clientes");
                    return;
                }
                Object oLote = tabla.getValueAt(indice, 0);
                Object oCi = tabla.getValueAt(indice, 1);
                if (oLote == null || oCi == null) {
                    System.out.println("lote o ci == null");
                    return;
                }
                int lote = Integer.parseInt(oLote.toString());
                int ci = Integer.parseInt(oCi.toString());
                ArrayList<Pago> pagos = Lote.getPagos(ci, lote);
                String[] columnNames = {
                    "fecha pago",
                    "monto"
                };
                Object rowData[][] = new Object[pagos.size()][columnNames.length];
                for (int i = 0; i < rowData.length; i++) {
                    Pago pago = pagos.get(i);
                    rowData[i][0] = pago.getFechaPago();
                    rowData[i][1] = pago.getMonto();
                }
                
                DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
                pnPagos.setCambiarTablaPagos(model);
            }
        });
    }

    void setModel(DefaultTableModel model) {
        tabla = new JTable(model);

        setVisible(true);
//        tabla.setModel(model);
    }

    public void filterText() {
        RowFilter<DefaultTableModel, Object> filter = null;
        try {
            int indice = cb_tipo_busqueda.getSelectedIndex();
            
            filter = RowFilter.regexFilter(label_find.getText(), 2 + indice);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(filter);
    }

    public void addCliente(JFrame frame) {
        JPanel form = new JPanel(new GridLayout(0, 1));

        form.add(new JLabel("C.I."));
        JTextField ci = new JTextField();
        form.add(ci);

        form.add(new JLabel("Nombre"));
        JTextField nombre = new JTextField();
        form.add(nombre);

        form.add(new JLabel("Apellido Paterno"));
        JTextField ap = new JTextField();
        form.add(ap);

        form.add(new JLabel("Apellido Materno"));
        JTextField am = new JTextField();
        form.add(am);

        form.add(new JLabel("Direccion"));
        JTextField dir = new JTextField();
        form.add(dir);

        int result = JOptionPane.showConfirmDialog(frame, form,
                "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //TODO evaluacion de la respuesta 
            //y captura de datos llenados
        }
        form.add(new JLabel("Telefono Fijo"));
        JTextField tf = new JTextField();
        form.add(tf);

        form.add(new JLabel("Celular"));
        JTextField cel = new JTextField();
        form.add(cel);

        result = JOptionPane.showConfirmDialog(frame, form,
                "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //try {
            //TODO Verificacion de datos correctos
            int numci = Integer.parseInt(ci.getText());
            int numtf = Integer.parseInt(tf.getText());
            int numcel = Integer.parseInt(cel.getText());
            //} catch(Exception e) {
            //  System.out.println("ERROR IN INPUT DATA");
            //}
            Cliente nuevo = new Cliente(numci, nombre.getText(),
                    ap.getText(), am.getText(), dir.getText(),
                    numtf, numcel);
            try {
                nuevo.save();
            } catch (SQLException ex) {
                System.out.println("ERROR SAVE: Cliente");
            }

        }
    }


    class MyTableModel2 extends AbstractTableModel {

        private String[] columnNames = {"C.I.",
            "Nombre",
            "Apellido Paterno",
            "Apellido Materno",
            "Detalles",
            "Total",
            "Deuda",
            "uno",
            "dos",
            "tres",
            "cuatro",
            "cinco"};
        private Object[][] data = {
            {new Integer(7989077), "Kathy", "Smith", "Rocha",
                "button", new Integer(7888), new Integer(89080),
                "a", "s", "s", "s", "s"}
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {

            data[row][col] = value;
        }
    }

}
