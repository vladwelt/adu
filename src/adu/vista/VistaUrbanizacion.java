package adu.vista;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

class VistaUrbanizacion extends JPanel {
    
    //private Urbanizacion urbanizacion;
    private JTextField label_find;
    private JButton button_find;
    private JTable tabla;
    private JTable tabla_cliente;

    public VistaUrbanizacion() {
        //this.urbanizacion = _urbanizacion;
        label_find = new JTextField();
        button_find = new JButton("Buscar..");
        tabla = new JTable(new MyTableModel());
        tabla_cliente = new JTable(new MyTableModel2());
        
        tabla_cliente.setPreferredScrollableViewportSize(new Dimension(200,20));
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
        panel.add(label_find,BorderLayout.CENTER);
        panel.add(button_find,BorderLayout.EAST);
        add(panel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(scrollPane1,BorderLayout.SOUTH);
    }

    public VistaUrbanizacion(DefaultTableModel model) {
        this();
        tabla.setModel(model);
        button_find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    void setModel(DefaultTableModel model) {
        tabla = new JTable(model);
        setVisible(true);
//        tabla.setModel(model);
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
	    {new Integer(7989077),"Kathy", "Smith","Rocha",
	     "button", new Integer(7888),new Integer(89080)},
	    {new Integer(8989022),"John", "Doe","Perez",
	     "button", new Integer(12313), new Integer(78798)},
	    {new Integer(6768980),"Sue", "Black","Rojo",
	     "button", new Integer(25657), new Integer(5675)},
	    {new Integer(1234567),"Jane", "White","Rex",
	     "button", new Integer(34555), new Integer(678768)},
	    {new Integer(4564577),"Joe", "Brown","Ter",
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
	    {new Integer(7989077),"Kathy", "Smith","Rocha",
	     "button", new Integer(7888),new Integer(89080),
             "a","s","s","s","s"}
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

