package adu.vista;

import adu.modelo.ListaUrbanizacion;
import adu.modelo.Urbanizacion;
import adu.modelo.Moderador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class VistaAdu extends JFrame {

    private VistaUrbanizacion vista;
    private JButton button_add;
    private JTabbedPane panels;
    private JComboBox lista_urbanizaciones;
    private int pes;
    private String tabActual;

    //Init Menus
    private JMenuBar menu_bar;
    private JMenu menu_agregar;
    private JMenuItem menu_agregar_urbanizacion;
    private JMenu menu_archivo;
    private JMenuItem menu_archivo_guardar;
    private JMenuItem menu_archivo_abrir;

    //backup
    private JFileChooser fileChooser;
    private Moderador backup;

    public VistaAdu(String nombre) {
        super(nombre);

        //fill data with you user and password
        backup = new Moderador("root","asdf");
        fileChooser = new JFileChooser();

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
        
        menu_archivo = new JMenu("Archivo");
        menu_bar.add(menu_archivo);

        menu_agregar = new JMenu("Agregar");
        menu_bar.add(menu_agregar);

        menu_agregar_urbanizacion = new JMenuItem("+ Urbanizacion");
        menu_agregar.add(menu_agregar_urbanizacion);

        menu_archivo_abrir = new JMenuItem("Abrir");
        menu_archivo_guardar = new JMenuItem("Guardar");

        menu_archivo.add(menu_archivo_abrir);
        menu_archivo_abrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(VistaAdu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                   File file = fileChooser.getSelectedFile();
                   backup.restoreDB(file.getAbsolutePath());
                }
            }
        });

        menu_archivo.add(menu_archivo_guardar);
        menu_archivo_guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showSaveDialog(VistaAdu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    //System.out.println(file.getAbsolutePath());
                    backup.backupDB(file.getAbsolutePath());
                }
            }
        });
    }

    public void addMenuActionListener(ActionListener listener) {
        menu_agregar_urbanizacion.addActionListener(listener);
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

    public void setTablaLotesMdel(DefaultTableModel model ,
            Urbanizacion _urbanizacion) {
        String nombre = getSelectedUrbanizacion().toString();
        panels.add(nombre, new VistaUrbanizacion(model,_urbanizacion));
        iniciarTabComponent(pes);
        pes++;

        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //GET menus
    public JMenuItem getMenuAgregarUrbanizacion() {
        return this.menu_agregar_urbanizacion;
    }

    //VIEW AGREGAR
    public void addUrbanizacion() {
        JPanel form = new JPanel(new GridLayout(0,1));
        form.add(new JLabel("Nombre"));
        JTextField nombre = new JTextField();
        form.add(nombre);

        form.add(new JLabel("Ancho"));
        JTextField ancho = new JTextField();
        form.add(ancho);

        form.add(new JLabel("Largo"));
        JTextField largo = new JTextField();
        form.add(largo);

        form.add(new JLabel("Numero de Lotes"));
        JTextField numLotes = new JTextField();
        form.add(numLotes);
        
        int result = JOptionPane.showConfirmDialog(null,
                form,"Agregar Urbanizacion",JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION) {

            double numancho = Double.parseDouble(ancho.getText());
            double numlargo = Double.parseDouble(largo.getText());
            int numlotes = Integer.parseInt(numLotes.getText());
            Urbanizacion nueva = new Urbanizacion(nombre.getText(),
                    numancho,numlargo,numlotes);
            try {
                nueva.save();
                lista_urbanizaciones.addItem(nueva);
            } catch (SQLException ex) {
                System.out.println("ERROR SAVE: Urbanizacion");
            }
        }
    }
}
