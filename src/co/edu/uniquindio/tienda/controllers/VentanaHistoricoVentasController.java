package co.edu.uniquindio.tienda.controllers;

import java.util.LinkedList;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.model.Cliente;
import co.edu.uniquindio.tienda.model.Producto;
import co.edu.uniquindio.tienda.model.Venta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VentanaHistoricoVentasController {
	
	private String documentoCliente;
	private Cliente cliente;
	private Boolean esCliente=true;
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	@FXML
    private TableColumn<Venta, String> columnaCliente;

    @FXML
    private TableView<Venta> tablaHistorico;

    @FXML
    private TableColumn<Venta, String> columnaCodigo;

    @FXML
    private TableColumn<Venta, String> columnaFecha;

    @FXML
    private TableColumn<Venta, String> columnaTotal;

    @FXML
    void salir() {
    	ventana.close();
    }
    
    public VentanaHistoricoVentasController() {
    	
    }
    
    public void actualizarTabla() {
    	
		tablaHistorico.getItems().clear();
		    	
		LinkedList<Venta> ventas = mfm.getTienda().getHistoricoVentas();
		
		// Convertir la lista de ventas en una ObservableList
		ObservableList<Venta> ventasObservable = FXCollections.observableArrayList(ventas);
		
		// Configurar las columnas de la tabla
		columnaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
		columnaCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
		columnaFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaCadena()));
		columnaTotal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalCadena()));
		
		// Asignar los datos a la tabla
		tablaHistorico.setItems(ventasObservable);
    	
    	/**
    	 * //mfm.tienda.imprimirCodigosVentas();
        // Limpiar la tabla
    	tablaHistorico.getItems().clear();
        
        // Obtener la lista de ventas del historial
        LinkedList<Venta> ventas = mfm.getTienda().getHistoricoVentas();
        
        // Convertir la lista de ventas en una ObservableList
        ObservableList<Venta> ventasObservable = FXCollections.observableArrayList(ventas);
        tablaHistorico.setItems(ventasObservable);
        
        // Asignar los valores de las propiedades a las columnas
        columnaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columnaCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNombre()))));
        columnaFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaCadena()));
        columnaTotal.setCellValueFactory(cellData -> new SimpleStringProperty(contadorProductos.get(cellData.getTotalCadena())));
        

        // Agregar las listas de ventas a la tabla
        //tablaHistorico.getItems().addAll(ventasObservable);
    	 */
    }

    
    public void setAplicacionPrincipal(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
	}

	public void setVentana(Stage primaryStage) {
		this.ventana=primaryStage;
	}
	
	public void setDocumentoCliente(String documentoCliente) {
		this.documentoCliente=documentoCliente;
		this.cliente=mfm.buscarCliente(documentoCliente);
	}

}
