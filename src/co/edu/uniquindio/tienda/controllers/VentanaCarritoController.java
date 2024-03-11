package co.edu.uniquindio.tienda.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.model.CarritoCompras;
import co.edu.uniquindio.tienda.model.Cliente;
import co.edu.uniquindio.tienda.model.DetalleVenta;
import co.edu.uniquindio.tienda.model.Producto;
import co.edu.uniquindio.tienda.util.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VentanaCarritoController {
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	private Cliente cliente;
	private String documento;
	
	@FXML
    private TableColumn<Producto, String> columnaCantidad;

    @FXML
    private TableColumn<Producto, String> columnaPrecio;

    @FXML
    private TableView<Producto> tablaProductosCarrito;

    @FXML
    private TableColumn<Producto, String> columnaCodigo;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    
    
    public VentanaCarritoController() {
    	
    }
    
    public void Initialize() {
    	
    }
    
    public void actualizarTabla() {
        // Buscar el carrito de compras del cliente
        CarritoCompras carritoCliente = null;
        for (CarritoCompras carrito : mfm.tienda.getCarritoCompras()) {
            if (carrito.getCliente().getIdentificacion().equals(documento)) {
                carritoCliente = carrito;
                break;
            }
        }
        
        if (carritoCliente != null) {
            // Obtener la lista de productos del carrito del cliente
            ArrayList<Producto> productosCarrito = carritoCliente.getListaProductos();
            
            // Contador para cada producto en el carrito
            Map<Producto, Integer> contadorProductos = new HashMap<>();
            for (Producto producto : productosCarrito) {
                contadorProductos.put(producto, contadorProductos.getOrDefault(producto, 0) + 1);
            }
            
            // Limpiar la tabla
            tablaProductosCarrito.getItems().clear();
            
            // Agregar los productos del carrito a la tabla
            ObservableList<Producto> listaProductos = FXCollections.observableArrayList(contadorProductos.keySet());
            tablaProductosCarrito.setItems(listaProductos);
            
            // Asignar los valores de las propiedades a las columnas
            columnaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
            columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            columnaPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrecioCadena()));
            columnaCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(contadorProductos.get(cellData.getValue()).toString()));
        } else {
            // No se encontró un carrito para el cliente, mostrar un mensaje o tomar alguna otra acción apropiada
        }
    }


    @FXML
    public void irAPagar() {
    	
    	CarritoCompras carritoCliente = null;
    	for (CarritoCompras carrito : mfm.tienda.getCarritoCompras()) {
    	    if (carrito.getCliente().getIdentificacion().equals(documento)) {
    	        carritoCliente = carrito;
    	        break;
    	    }
    	}

    	if (carritoCliente != null) {
    	    // Obtener la lista de productos del carrito del cliente
    	    ArrayList<Producto> productosCarrito = carritoCliente.getListaProductos();
    	    
    	    // Crear una lista para almacenar los detalles de venta
    	    ArrayList<DetalleVenta> detallesVenta = new ArrayList<>();
    	    
    	    // Variable para almacenar el total de subtotales
    	    Double totalVentas = 0.0;
    	    
    	    // Contador para cada producto en el carrito
    	    Map<Producto, Integer> contadorProductos = new HashMap<>();
    	    for (Producto producto : productosCarrito) {
    	        contadorProductos.put(producto, contadorProductos.getOrDefault(producto, 0) + 1);
    	    }
    	    
    	    // Crear un detalle de venta para cada producto en el carrito
    	    for (Map.Entry<Producto, Integer> entry : contadorProductos.entrySet()) {
    	        Producto producto = entry.getKey();
    	        Integer cantidad = entry.getValue();
    	        Double subTotal = producto.getPrecio() * cantidad; // Subtotal = Precio * Cantidad
    	        totalVentas += subTotal; // Sumar al total de ventas
    	        
    	        DetalleVenta detalleVenta = new DetalleVenta(cantidad, subTotal, producto);
    	        detallesVenta.add(detalleVenta);
    	        
    	        
    	    }
    	    mfm.tienda.agregarVentaNueva(documento, totalVentas, detallesVenta);
    	    
    	    
    	}
    	
    	ventana.close();
    	
    			
    	
    	//recuerde que despues de esta accion se devuelve al cliente a la ventana principal 
    	Persistencia.serialize("src/archivo.dat", mfm.getTienda());

    }
    
    public void salir() {
    	ventana.close();
    }
    
    @FXML
    public void eliminarProducto() {
    	Producto productoSeleccionado = tablaProductosCarrito.getSelectionModel().getSelectedItem();
    	mfm.tienda.agregarCantidadProducto(productoSeleccionado);
    	
    	mfm.tienda.eliminarProductoDeCarrito(productoSeleccionado, documento);
    	actualizarTabla();
    	
    	//Persistencia.serialize("src/archivo.dat", mfm.getTienda());
    }
    
    public void setAplicacionPrincipal(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
	}

	public void setVentana(Stage primaryStage) {
		this.ventana=primaryStage;
	}
	
	

	
	public void setDocumentoCliente(String documentoCliente) {
		this.documento=documentoCliente;
		this.cliente=mfm.buscarCliente(documentoCliente);
	}

}
