package adu.vista;

import adu.modelo.Cliente;
import adu.modelo.Lote;
import adu.modelo.Pago;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.lang.Integer;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

class VistaUrbanizacion extends JPanel {

    //private Urbanizacion urbanizacion;
    private JTextField label_find;
    private JButton button_find;
    private JButton btn_todos;
    private JTable tabla;
    private JTable tabla_cliente;
    private PnPagos pnPagos;
    
    public VistaUrbanizacion() {
        //this.urbanizacion = _urbanizacion;
        label_find = new JTextField();
        button_find = new JButton("Buscar..");
        btn_todos = new JButton("Todos");
        pnPagos = new PnPagos();
        tabla = new JTable(new MyTableModel());
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
        
        panel.add(btn_todos, BorderLayout.WEST);
        panel.add(label_find, BorderLayout.CENTER);
        panel.add(button_find, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane1, BorderLayout.SOUTH);
        add(pnPagos, BorderLayout.WEST);
    }
    private DefaultTableModel lotesModel;

    public VistaUrbanizacion(DefaultTableModel model) {
        this();
        tabla.setModel(model);
        this.lotesModel = model;
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int indice = tabla.getSelectedRow();
                if(indice < 0){
                    System.out.println("indice negativo seleccionado de la tabla de clientes");
                    return;
                }
                Object oLote = tabla.getValueAt(indice, 0);
                Object oCi = tabla.getValueAt(indice, 1);
                if(oLote == null || oCi == null) {
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
        btn_todos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabla.setModel(lotesModel);
            }
        });
        button_find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean bb = false;
                String buscar = label_find.getText();
                Object[] row;
                String[] columnNames = {
                    "Num Lote",
                    "C.I.",
                    "Nombre",
                    "Apellido Paterno",
                    "Apellido Materno",
                    "Detalles",
                    "Total",
                    "Deuda"};
                Object rowData[][] = new Object[lotesModel.getRowCount()][columnNames.length];
                for (int i = 0; i < rowData.length; i++) {
                    bb = false;
                    bb = bb || contiene(buscar , i,2); 
                    bb = bb || contiene(buscar , i,3); 
                    bb = bb || contiene(buscar , i,4); 
                    if (bb) {
                        System.out.println(buscar + " = " + lotesModel.getValueAt(i, 2).toString() + "=>" + lotesModel.getValueAt(i, 2).toString().contains(buscar));
                        for (int j = 0; j < lotesModel.getColumnCount(); j++) {
                            rowData[i][j] = lotesModel.getValueAt(i, j);                        
                        }
                    }
                }
                DefaultTableModel filtrado = new DefaultTableModel(rowData, columnNames);
                tabla.setModel(filtrado);
            }

            private boolean contiene(String buscar, int i, int j) {
                Object value = lotesModel.getValueAt(i, j);
                if(value != null)
                    return buscar.contains(value.toString());
                return false;
            }

        });
    }

    void setModel(DefaultTableModel model) {
        tabla = new JTable(model);
        setVisible(true);
//        tabla.setModel(model);
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
                Cliente nuevo = new Cliente(numci,nombre.getText(),
                        ap.getText(),am.getText(),dir.getText(),
                        numtf,numcel);
                try {
                    nuevo.save();
                } catch(SQLException ex) {
                    System.out.println("ERROR SAVE: Cliente");
                }

            }
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {"C.I.",
            "Nombre",
            "Apellido Paterno",
            "Apellido Materno",
            "Detalles",
            "Total",
            "Deuda"};
        private Object[][] data = {
            {new Integer(7989077), "Kathy", "Smith", "Rocha",
                "button", new Integer(7888), new Integer(89080)},
            {new Integer(8989022), "John", "Doe", "Perez",
                "button", new Integer(12313), new Integer(78798)},
            {new Integer(6768980), "Sue", "Black", "Rojo",
                "button", new Integer(25657), new Integer(5675)},
            {new Integer(1234567), "Jane", "White", "Rex",
                "button", new Integer(34555), new Integer(678768)},
            {new Integer(4564577), "Joe", "Brown", "Ter",
                "button", new Integer(10345), new Integer(67867)}
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
