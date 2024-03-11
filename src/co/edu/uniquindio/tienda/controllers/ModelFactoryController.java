package co.edu.uniquindio.tienda.controllers;

import java.util.HashMap;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.model.Administrador;
import co.edu.uniquindio.tienda.model.Cliente;
import co.edu.uniquindio.tienda.model.Producto;
import co.edu.uniquindio.tienda.model.Tienda;
import co.edu.uniquindio.tienda.util.Persistencia;
import javafx.stage.Stage;

public class ModelFactoryController {
	
	Tienda tienda = null;
	private Aplicacion aplicacion;
	private Stage ventana;
	
	private static class SingletonHolder { 
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	/**
	 * Metodo constructor
	 */
	public ModelFactoryController() {
		Tienda datosObtenidos;
		datosObtenidos=Persistencia.realizarCarga("src/archivo.dat") ;
		if(datosObtenidos!=null)
        {
			tienda=datosObtenidos;
        } else {
        	inicializarDatos();
        }
	}
	
	private void inicializarDatos() {
		
		this.tienda = new Tienda();
		tienda=new Tienda("Tienda Akira", "Av. 14 #35N-11", "19328273819-8");
		
		try {
			tienda.registrarCliente("12345", "Mary", "Armenia");
			tienda.registrarAdmin("Miluzka", "476299", "Armenia");
			datosQuemados();
			
		} catch(Exception e) {}
	}
	
	public void datosQuemados() {
		
		tienda.agregarProductoNuevo("Televisor LED 55 pulgadas", "20", "799.99");
		tienda.agregarProductoNuevo("Licuadora de alta velocidad", "15", "49.99");
        tienda.agregarProductoNuevo("Silla de oficina ergonómica", "30", "129.99");
        tienda.agregarProductoNuevo("Cafetera programable", "25", "39.99");
        tienda.agregarProductoNuevo("Juego de cuchillos de cocina", "10", "29.99");
        tienda.agregarProductoNuevo("Set de ollas de acero inoxidable", "18", "59.99");
        tienda.agregarProductoNuevo("Batidora de mano eléctrica", "20", "34.99");
        tienda.agregarProductoNuevo("Máquina de hacer pan automática", "8", "79.99");
        
	}
	
	/**
	 * Metodo que obtiene la tienda
	 * @return Biblioteca
	 */
	public Tienda getTienda() {
		return tienda;
	}
	
	public Cliente buscarCliente(String documento) {
		return tienda.buscarClientePorDocumento(documento);
	}
	
	public Administrador buscarAdmin(String documento) {
		return tienda.buscarAdminPorDocumento(documento);
	}
	
	public HashMap<String, Producto> getProductosHash() {
		return tienda.getProductos();
	}
	
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}
	
	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

}
