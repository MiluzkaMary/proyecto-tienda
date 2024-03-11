package co.edu.uniquindio.tienda.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Producto implements Serializable{
	
	private static final long serialVersionUID =1L;
	
	private Integer cantidad;
	private String codigo;
	private String nombre;
	private Double precio;
	
	public Producto(Integer cantidad, String codigo, String nombre, Double precio) {
		super();
		this.cantidad = cantidad;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public String getPrecioCadena() {
		DecimalFormat df = new DecimalFormat("#.00"); // Formato para dos decimales
		return df.format(precio); // Formatea el precio y lo devuelve como String
	}
	
	
	

}
