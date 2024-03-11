package co.edu.uniquindio.tienda.controllers;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaClienteController {
	
	private String documentoCliente;
	private Cliente cliente;
	private Boolean esCliente=true;
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	@FXML
    private Button btnCatalogo;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnCompras;

    @FXML
    private Text txtBienvenida;

    @FXML
    private Button btnCarrito;
    
    public VentanaClienteController(){
    	
    }
    
    @FXML
    public void initialize() {
    	
    }
    
    public void iniciarDatos() {
    	txtBienvenida.setText("¡Bienvenid@, "+ cliente.getNombre() + "!" );
    }
    
    

    @FXML
    void verCatalogo() {
    	aplicacion.iniciarVentanaCatalogo(esCliente, cliente.getIdentificacion());
    }

    @FXML
    void verCarrito() {
    	aplicacion.iniciarVentanaCarritoCliente(cliente.getIdentificacion());
    }

    @FXML
    void verCompras() {
    	aplicacion.iniciarVentanaCompras(cliente.getIdentificacion());
    }

    @FXML
    void salir() {
    	ventana.close();
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
