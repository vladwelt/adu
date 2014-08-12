package adu.vista;

import adu.modelo.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

class VistaUrbanizacion extends JPanel {

    private Urbanizacion urbanizacion;
    private JTextField label_find;
    private JComboBox<String> cb_tipo_busqueda;
    private JTable tabla;
    private PnPagos pnPagos;
    private TableRowSorter<DefaultTableModel> sorter;
    private int id_urbanizacion;
    private JButton button_add;

    public VistaUrbanizacion() {
        //this.urbanizacion = _urbanizacion;
        button_add = new JButton("Vender");
        label_find = new JTextField();
        cb_tipo_busqueda = new JComboBox<>(new String[]{"Nombre", "Apellido Paterno", "Apellido Materno"});
        cb_tipo_busqueda.setSelectedIndex(0);
        pnPagos = new PnPagos();
        tabla = new JTable();
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);

        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(tabla);
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

        button_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    addVenta(null);
                } catch (NumberFormatException e) {
                    alertMessage("LOS DATOS INGRESADOS NO SON CORRECTOS"
                            +"\n"+e.getMessage());
                    System.out.println("ERROR AL INGRESAR LOS DATOS " +     e.getMessage());
                }
            }
        });

        panel.add(label_find, BorderLayout.CENTER);
        panel.add(cb_tipo_busqueda, BorderLayout.WEST);
        panel.add(button_add, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    private DefaultTableModel lotesModel;

    public VistaUrbanizacion(DefaultTableModel model,
            Urbanizacion _urbanizacion) {
        this();
        this.urbanizacion = _urbanizacion;
        tabla.setModel(model);
        this.lotesModel = model;

        sorter = new TableRowSorter<DefaultTableModel>(model);
        tabla.setRowSorter(sorter);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int indice = tabla.getSelectedRow();
                int index = tabla.getSelectedColumn();
                if (indice < 0 || index > 7) {
                    System.out.println("indice negativo seleccionado de la tabla de clientes");
                    return;
                }
                Object oLote = tabla.getValueAt(indice, 0);
                Object oManzano = tabla.getValueAt(indice, 1);
                Object oCi = tabla.getValueAt(indice, 2);
                if (oLote == null || oCi == null) {
                    System.out.println("lote o ci == null");
                    return;
                }
                int lote = Integer.parseInt(oLote.toString());
                int ci = Integer.parseInt(oCi.toString());
                int manzano = Integer.parseInt(oManzano.toString());
                ArrayList<Pago> pagos = Lote.getPagos(urbanizacion, ci, lote, manzano);
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
                JOptionPane.showConfirmDialog(null, pnPagos,
                        "Pagos", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                tabla.clearSelection();

            }
        });

        Action cobrar;
        cobrar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int index = Integer.valueOf(e.getActionCommand());
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                try {
                    addCobro(model, index);
                } catch (NumberFormatException ex) {
                    alertMessage("LOS DATOS INGRESADOS NO SON CORRECTOS"
                            + "\n" + ex.getMessage());
                }
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(tabla, cobrar, 8);
    }

    public void setModel(DefaultTableModel model) {
        tabla = new JTable(model);
        setVisible(true);
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

    public void addVenta(JFrame frame) throws NumberFormatException{

        JPanel contenedor = new JPanel();

        contenedor.setLayout(new FlowLayout(3, 50, 25));
        JPanel form = new JPanel(new GridLayout(17, 1));
        form.add(new JLabel("CLIENTE :"));
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

        form.add(new JLabel("Celular"));
        JTextField cel = new JTextField();
        form.add(cel);

        contenedor.add(form);
        contenedor.add(new JSeparator(SwingConstants.VERTICAL));

        JPanel form1 = new JPanel(new GridLayout(17, 1));
        form1.add(new JLabel("LOTE :"));
        
        form1.add(new JLabel("Numero Manzano"));
        String[] manzanoss = new String[urbanizacion.getCantidadManzanos()];
        for (int i = 0; i < urbanizacion.getCantidadManzanos(); i++) {
            manzanoss[i] = String.valueOf(i+1);
        }
        JComboBox manzanos = new JComboBox(manzanoss);
        if(manzanos.getItemCount() > 0) {
            manzanos.setSelectedIndex(0);
        }
        form1.add(manzanos);
        
        form1.add(new JLabel("Numero de lote"));
        JTextField numlote = new JTextField();
        form1.add(numlote);

        form1.add(new JLabel("Descripcion"));
        JTextField descr = new JTextField();
        form1.add(descr);

        form1.add(new JLabel("Ancho"));
        JTextField ancho = new JTextField();
        form1.add(ancho);

        form1.add(new JLabel("Largo"));
        JTextField largo = new JTextField();
        form1.add(largo);
        
        form1.add(new JLabel("Precio(Bs)"));
        JTextField precio = new JTextField();
        form1.add(precio);

        contenedor.add(form1);

        form1.add(new JLabel("Cantidad de cuotas"));
        JTextField cantcuotas = new JTextField();
        form1.add(cantcuotas);

        form1.add(new JLabel("Fecha de Venta"));

        JDateChooser fecha = new JDateChooser(new java.util.Date());
        fecha.setLocale(new Locale("ES"));
        form1.add(fecha);

        int result = JOptionPane.showConfirmDialog(frame, contenedor,
                "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            int numci = Integer.parseInt(ci.getText());
            int numcel = Integer.parseInt(cel.getText());
            double numlargo = Double.parseDouble(largo.getText());
            double numancho = Double.parseDouble(ancho.getText());
            double numprecio = Double.parseDouble(precio.getText());
            int numloteint = Integer.parseInt(numlote.getText());
            int numcantlotes = Integer.parseInt(cantcuotas.getText());
            Date fecha_venta = new Date(fecha.getDate().getTime());

            Cliente cliente_ = new Cliente(numci, nombre.getText(),
                    ap.getText(), am.getText(), dir.getText(),
                    00000, numcel);

            Lote lote = new Lote(manzanos.getSelectedIndex() + 1,
                    numlargo, numancho, numprecio, numloteint,
                    descr.getText());

            DefaultTableModel model = (DefaultTableModel) tabla.getModel();

            urbanizacion.getLotes().add(lote);
            try {
                cliente_.save();
                lote.save(urbanizacion);
                lote.vender(cliente_.getCi(), fecha_venta, numcantlotes);
                model.addRow(new Object[]{lote.getNumero_lote(),
                    lote.getNumeroManzano(),
                    cliente_.getCi(), cliente_.getNombre(),
                    cliente_.getApellidoPaterno(), 
                    cliente_.getApellidoMaterno(),lote.getPrecio(),
                    lote.getPrecio(), "Cobrar"});
            } catch (SQLException ex) {
                alertMessage("BASE DE DATOS[Urbanizacion] : \n" +
                        ex.getMessage());
            }
        }
    }
    
    public void alertMessage(String message) {
            JOptionPane.showMessageDialog(null, message,
                "ERROR!!!", JOptionPane.ERROR_MESSAGE);
    }

    public void addCobro(DefaultTableModel model,
            int index)throws NumberFormatException {
        JPanel form = new JPanel(new GridLayout(0, 1));

        form.add(new JLabel("COBRAR :"));
        form.add(new JLabel("Nombre"));
        JTextField nomcompleto = new JTextField();
        nomcompleto.setText(model.getValueAt(index, 3) + " "
              + model.getValueAt(index, 4) + " "
                + model.getValueAt(index, 5));
        nomcompleto.setEditable(false);
        form.add(nomcompleto);

        form.add(new JLabel("Lote"));
        JTextField num_lote = new JTextField();
        num_lote.setText(model.getValueAt(index, 0).toString());
        num_lote.setEditable(false);
        form.add(num_lote);

        form.add(new JLabel("Manzano"));
        JTextField num_manzano = new JTextField();
        num_manzano.setText(model.getValueAt(index, 1).toString());
        num_manzano.setEditable(false);
        form.add(num_manzano);

        form.add(new JLabel("Monto"));
        JTextField monto = new JTextField();
        form.add(monto);

        form.add(new JLabel("fecha"));

        JDateChooser fecha = new JDateChooser(new java.util.Date());
        fecha.setLocale(new Locale("ES"));
        form.add(fecha);

        int result = JOptionPane.showConfirmDialog(null, form,
                "Cobrar", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        int numero_lote = Integer.parseInt(num_lote.getText());
        int numero_manzano = Integer.parseInt(num_manzano.getText());
        int cuota = Integer.parseInt(monto.getText());

        if (result == JOptionPane.OK_OPTION) {
            try {
                Lote lote = urbanizacion.getLote(numero_lote, numero_manzano);
                if (lote == null) {
                    alertMessage("LOTE NO ENCONTRADO!!!");
                    return;
                }
                Date fecha_pago = new Date(fecha.getDate().getTime());
                lote.pagarCuota(cuota, fecha_pago);
                tabla.getModel().setValueAt(lote.getDeuda(), index, 7);
            } catch (SQLException ex) {
                alertMessage("BASE DE DATOS[cobros] : \n" + 
                    ex.getMessage());
            }
        }
    }
}
