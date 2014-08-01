package adu.vista;

import adu.modelo.ListaUrbanizacion;
import adu.modelo.Urbanizacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class VistaAdu extends JFrame {

    //private VistaUrbanizacion[] panel_urbanizaciones;
    //private Urbanizacion[] urbanizaciones;
    private VistaUrbanizacion vista;
    private JButton button_add;
    private JTabbedPane panels;
    private JComboBox lista_urbanizaciones;
    private int pes;
    private String tabActual;

    //Init Menus
    private JMenuBar menu_bar;
    private JMenu menu_agregar;
    private JMenuItem menu_agregar_cliente;
    private JMenuItem menu_agregar_lote;
    private JMenuItem menu_agregar_urbanizacion;
    private JMenuItem menu_agregar_venta;
    private JMenuItem menu_agregar_pago;



    public VistaAdu(String nombre) {
        super(nombre);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button_add = new JButton("Agregar");

        lista_urbanizaciones = new JComboBox();
        try {
            lista_urbanizaciones.setModel(ListaUrbanizacion.getUrbanizaciones().getModel());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if(lista_urbanizaciones.getModel().getSize() > 0)
            lista_urbanizaciones.setSelectedIndex(0);
        setLayout(new BorderLayout());
        panels = new JTabbedPane();
        vista = new VistaUrbanizacion();
        
        panels.addChangeListener( new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                tabActual = panels.getTitleAt(panels.getSelectedIndex());
            }
        });


        pes = 0;
        initMenus();
        add(lista_urbanizaciones, BorderLayout.NORTH);
        add(panels, BorderLayout.CENTER);           
    }

    private void initMenus() {
        menu_bar = new JMenuBar();
        setJMenuBar(menu_bar);
        
        menu_agregar = new JMenu("Agregar");
        menu_bar.add(menu_agregar);

        menu_agregar_cliente = new JMenuItem("+ Cliente");
        menu_agregar_lote = new JMenuItem("+ Lote");
        menu_agregar_urbanizacion = new JMenuItem("+ Urbanizacion");
        menu_agregar_venta = new JMenuItem("+ Venta");
        menu_agregar_pago = new JMenuItem("+ Pago");

        menu_agregar.add(menu_agregar_urbanizacion);
        menu_agregar.add(menu_agregar_lote);
        menu_agregar.add(menu_agregar_cliente);
        menu_agregar.add(menu_agregar_venta);
        menu_agregar.add(menu_agregar_pago);
    }

    public void addMenuActionListener(ActionListener listener) {
        menu_agregar_urbanizacion.addActionListener(listener);
        menu_agregar_lote.addActionListener(listener);
        menu_agregar_cliente.addActionListener(listener);
        menu_agregar_venta.addActionListener(listener);
        menu_agregar_pago.addActionListener(listener);
    }

    public void runTest() {
        panels.removeAll();
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addComboBoxActionListener(ActionListener listener) {
        lista_urbanizaciones.addActionListener(listener);

    }

    public Urbanizacion getSelectedUrbanizacion() {
        Urbanizacion res = null;
        Object selectedItem = lista_urbanizaciones.getModel().getSelectedItem();
        if (selectedItem instanceof Urbanizacion) {
            res = (Urbanizacion) selectedItem;
        }
        return res;
    }

    public int getComboBoxSelectionIndex() {
        return lista_urbanizaciones.getSelectedIndex();
    }

    private void iniciarTabComponent(int i) {
        panels.setTabComponentAt(i, new ButtonTabComponent(panels));
    }

    public void setTablaLotesMdel(DefaultTableModel model) {
        String nombre = getSelectedUrbanizacion().toString();
        panels.add(nombre, new VistaUrbanizacion(model));
        iniciarTabComponent(pes);
        pes++;

        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //GET menus
    public JMenuItem getMenuAgregarCliente() {
        return this.menu_agregar_cliente;
    }
    public JMenuItem getMenuAgregarUrbanizacion() {
        return this.menu_agregar_urbanizacion;
    }
    public JMenuItem getMenuAgregarLote() {
        return this.menu_agregar_lote;
    }
    public JMenuItem getMenuAgregarVenta() {
        return this.menu_agregar_venta;
    } 
    public JMenuItem getMenuAgregarPago() {
        return this.menu_agregar_pago;
    }

    //VIEW AGREGAR
    public void addUrbanizacion() {
        System.out.println("urr");
    }

    public void addCliente() {
        
        System.out.println("Cliente"); 
    }

    public void addLote() {
    
        System.out.println("lote");
    }

    public void addVenta() {
    
        System.out.println("venta");
    }

    public void addPago() {
    
        System.out.println("Pago");
    }
}
