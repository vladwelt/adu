
package adu.modelo;

import java.sql.Date;

public class Pago {
    private int id;
    private int venta_id;
    private Date fechaPago;
    private double monto;

    public Pago(int id, int venta_id, Date fechaPago, double monto) {
        this.id = id;
        this.venta_id = venta_id;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(int venta_id) {
        this.venta_id = venta_id;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
