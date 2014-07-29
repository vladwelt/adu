package vista;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;

class VistaUrbanizacion extends JPanel {
    
    //private Urbanizacion urbanizacion;
    private JTextField label_find;
    private JButton button_find;
    private JTable tabla;


    public VistaUrbanizacion() {
        //this.urbanizacion = _urbanizacion;
        label_find = new JTextField();
        button_find = new JButton("Buscar..");
        tabla = new JTable(new MyTableModel());
        tabla.setPreferredScrollableViewportSize(new Dimension(200,500));
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(label_find);
        add(button_find);
        add(scrollPane);
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

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {

            data[row][col] = value;
            // Normally, one should call fireTableCellUpdated() when 
            // a value is changed.  However, doing so in this demo
            // causes a problem with TableSorter.  The tableChanged()
            // call on TableSorter that results from calling
            // fireTableCellUpdated() causes the indices to be regenerated
            // when they shouldn't be.  Ideally, TableSorter should be
            // given a more intelligent tableChanged() implementation,
            // and then the following line can be uncommented.
            // fireTableCellUpdated(row, col);
        }
    }

}

