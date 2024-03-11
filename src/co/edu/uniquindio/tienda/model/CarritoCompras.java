package co.edu.uniquindio.tienda.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CarritoCompras implements Serializable {
	
	private String codigo;
	private Cliente cliente;
	private ArrayList<Producto> listaProductos;
	
	public CarritoCompras(String codigo, Cliente cliente, ArrayList<Producto> listaProductos) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.listaProductos = listaProductos;
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
	 * @return the listaProductos
	 */
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	/**
	 * @param listaProductos the listaProductos to set
	 */
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	

}
