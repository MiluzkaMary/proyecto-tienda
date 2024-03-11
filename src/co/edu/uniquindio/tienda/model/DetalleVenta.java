package co.edu.uniquindio.tienda.model;

import java.io.Serializable;

public class DetalleVenta implements Serializable{
	
	private Integer cantidad;
	private Double subTotal;
	private Producto productoElegido;
	
	public DetalleVenta(Integer cantidad, Double subTotal, Producto productoElegido) {
		super();
		this.cantidad = cantidad;
		this.subTotal = subTotal;
		this.productoElegido = productoElegido;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the subTotal
	 */
	public Double getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the productoElegido
	 */
	public Producto getProductoElegido() {
		return productoElegido;
	}

	/**
	 * @param productoElegido the productoElegido to set
	 */
	public void setProductoElegido(Producto productoElegido) {
		this.productoElegido = productoElegido;
	}
	
	
	

}
