package co.edu.uniquindio.tienda.controllers;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.model.Administrador;
import co.edu.uniquindio.tienda.model.Cliente;
import co.edu.uniquindio.tienda.model.Producto;
import co.edu.uniquindio.tienda.util.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaCatalogoController {
	
	private String documentoCliente;
	private String documentoAdmin;
	private Cliente cliente;
	private Administrador admin;
	
	private Boolean esCliente;
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	@FXML
    private TableColumn<Producto, String> columnaCantidad;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TableColumn<Producto, String> columnaPrecio;

    @FXML
    private Button btnAgregarCarrito;

    @FXML
    private TableColumn<Producto, String> columnaCodigo;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TextField txtPrecio;
    
    @FXML
    private Text titleNombre;
    
    @FXML
    private Text titleNuevoProducto;
    
    @FXML
    private Text titlePrecio;
    
    @FXML
    private Text titleCantidad;

    @FXML
    private ScrollPane scrollTable;

    
	
	public VentanaCatalogoController(){
    	
    }
    
    @FXML
    public void initialize() {
        actualizarTabla();
    }

    
    /**
	 * Metodo que actualiza la tabla de citas con la lista de citas actualizada.
	 */
	public void actualizarTabla() {
		tablaProductos.getItems().clear();
		ObservableList<Producto> listaProductos = FXCollections.observableArrayList(mfm.getProductosHash().values());
		tablaProductos.setItems(listaProductos);
		columnaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
		columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		columnaPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrecioCadena()));
		columnaCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCantidad().toString()));
	}
	
	@FXML
    public void salir() {
    	ventana.close();
    }
	
	@FXML
    public void agregarAlCarrito() {
		
		Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
		mfm.tienda.restarCantidadProducto(productoSeleccionado);
		actualizarTabla();
		
		mfm.tienda.agregarProductoACarrito(productoSeleccionado, documentoCliente);
		
		//Persistencia.serialize("src/archivo.dat", mfm.getTienda());

    }

	public void decidirPanelClienteAdmin() {
		if (esCliente) {
			txtNombreProducto.setVisible(false);
			txtCantidad.setVisible(false);
			txtPrecio.setVisible(false);
			titleNombre.setVisible(false);
			titleNuevoProducto.setVisible(false);
			titlePrecio.setVisible(false);
			titleCantidad.setVisible(false);
			btnAgregarProducto.setVisible(false);
			
			
			
			scrollTable.setPrefHeight(439);
			tablaProductos.setPrefHeight(450);
			
			btnAgregarCarrito.setVisible(true);
			btnVolver.setLayoutX(433);
			btnVolver.setLayoutY(530);
			btnAgregarCarrito.setLayoutX(200);
			btnAgregarCarrito.setLayoutY(530);
		}else {
			
			
			txtNombreProducto.setVisible(true);
			txtCantidad.setVisible(true);
			txtPrecio.setVisible(true);
			titleNombre.setVisible(true);
			titleNuevoProducto.setVisible(true);
			titlePrecio.setVisible(true);
			titleCantidad.setVisible(true);
			btnAgregarProducto.setVisible(true);
			
			btnAgregarCarrito.setVisible(false);
			btnVolver.setLayoutY(351);
			btnVolver.setLayoutX(300);
			
			scrollTable.setPrefHeight(239);
			tablaProductos.setPrefHeight(250);
		}
	}
	
    @FXML
    public void agregarNuevoProducto() {
    	mfm.tienda.agregarProductoNuevo(txtNombreProducto.getText(), txtCantidad.getText(), txtPrecio.getText());
    	actualizarTabla();
    	
    	//Persistencia.serialize("src/archivo.dat", mfm.getTienda());

    }
	
	public void setAplicacionPrincipal(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
	}

	public void setVentana(Stage primaryStage) {
		this.ventana=primaryStage;
	}
	
	public void setEsCliente(Boolean esCliente) {
		this.esCliente=esCliente;
	}
	
	public void setDocumentoCliente(String documentoCliente) {
		this.documentoCliente=documentoCliente;
		this.cliente=mfm.buscarCliente(documentoCliente);
	}
	
	public void setDocumentoAdmin(String documentoAdmin) {
		this.documentoAdmin=documentoAdmin;
		this.admin=mfm.buscarAdmin(documentoAdmin);
	}

}
