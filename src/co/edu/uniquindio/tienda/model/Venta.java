package co.edu.uniquindio.tienda.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venta implements Serializable {
	
	private String codigo;
	private LocalDate fecha;
	private Double total;
	private Cliente cliente;
	private ArrayList <DetalleVenta> detalleVenta;
	
	public Venta(String codigo, LocalDate fecha, Double total, Cliente cliente, ArrayList<DetalleVenta> detalleVenta) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.cliente = cliente;
		this.detalleVenta = detalleVenta;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the detalleVenta
	 */
	public ArrayList<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	/**
	 * @param detalleVenta the detalleVenta to set
	 */
	public void setDetalleVenta(ArrayList<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	
	/**
     * Calcula el total de la venta sumando los subtotales de cada detalle de venta.
     * @return Total de la venta.
     */
    private double calcularTotal() {
        double totalVenta = 0;
        for (DetalleVenta detalle : detalleVenta) {
            totalVenta += detalle.getSubTotal();
        }
        return totalVenta;
    }
    
    /**
	 * Devuelve el total de la venta en formato de cadena.
	 * @return Total de la venta en formato de cadena.
	 */
	public String getTotalCadena() {
		return String.format("%.2f", total);
	}

	/**
	 * Devuelve la fecha de la venta en formato de cadena.
	 * @return Fecha de la venta en formato de cadena.
	 */
	public String getFechaCadena() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fecha.format(formatter);
	}

	
	
	

}
