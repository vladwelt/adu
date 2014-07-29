package adu.modelo;

class Cliente {

    private int ci;//Carnet de identidad
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String direccion;
    private int telefono_fijo;
    private int telefono_celular;

    public Cliente(int _ci, String _nombre, String _ap, String _am,
            String _direccion, int _tf, int _tc) {
        this.ci = _ci;
        this.nombre = _nombre;
        this.apellido_paterno = _ap;
        this.apellido_materno = _am;
        this.direccion = _direccion;
        this.telefono_fijo = _tf;
        this.telefono_celular = _tc;
    }

    public Cliente(int _ci, String _nombre, String _ap, String _am,
            String _direccion, int _tc) {
        this(_ci, _nombre, _ap, _am, _direccion,0, _tc);
    }

    public Cliente(int _ci, String _nombre, String _ap,
            String _direccion, int _tc) {
        this(_ci, _nombre, _ap, "", _direccion,0, _tc);
    }
    
    public int getCi() {
        
        return this.ci;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidoPaterno() {
        return this.apellido_paterno;
    }

    public String getApellidoMaterno() {
        return this.apellido_materno;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public int getTelefonoFijo() {
        return this.telefono_fijo;
    }

    public int getTelefonoCelular() {
        return this.telefono_celular;
    }

    public void setCi(int _ci) {
        this.ci = _ci;
    }

    public void setNombre(String _nombre) {
        this.nombre = _nombre;
    }

    public void setApellidoPaterno(String _ap) {
        this.apellido_paterno = _ap;
    }

    public void setApellidoMaterno(String _am) {
        this.apellido_materno = _am;
    }

    public void setDireccion(String _direccion) {
        this.direccion = _direccion;
    }

    public void setTelefonoFijo(int _tf) {
        this.telefono_fijo = _tf;
    }

    public void setTelefonoCelular(int _tc) {
        this.telefono_celular = _tc;
    }

}
