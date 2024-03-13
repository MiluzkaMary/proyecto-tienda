package co.edu.uniquindio.tienda.controllers;

import co.edu.uniquindio.tienda.application.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaInicioController {
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	@FXML
    private TextField txtID;

    @FXML
    private Text titleNombre;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> comboCargo;

    @FXML
    private Text titleDireccion;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Text titleRegistro;

    @FXML
    private Text titleid;

    @FXML
    private TextField txtIDNuevo;

    @FXML
    private Button btnIngresar;

    @FXML
    private ImageView imagenLogo;

    @FXML
    private Button btnRegistrar;
	
	public VentanaInicioController() {
		
	}
	
    public void initialize() {
        comboCargo.getItems().addAll("Administrador", "Cliente");
    }
    
    @FXML
    public void ingresar() {
    	String cargoSeleccionado = comboCargo.getValue();
    	String documento = txtID.getText();
        
        // Verificar si se ha seleccionado una opción
        if (cargoSeleccionado != null || documento!= null) {
        	
        	switch (cargoSeleccionado) {
            case "Administrador":
                System.out.print(documento);
            	aplicacion.iniciarVentanaAdministrador(documento);
            	
                break;
            case "Cliente":
            	aplicacion.iniciarVentanaCliente(documento);
            	
                break;
        	}
        	
        } else {
            // Si no se ha seleccionado ninguna opción
            System.out.println("Por favor seleccione un cargo y digite su identificación");
        }
    }
    
    public void ventanaRegistrarse() {
    	aplicacion.iniciarVentanaRegistro();
    }
    
    
    
    
    
    public void setAplicacionPrincipal(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
	}

	public void setVentana(Stage primaryStage) {
		this.ventana=primaryStage;
	}

}
