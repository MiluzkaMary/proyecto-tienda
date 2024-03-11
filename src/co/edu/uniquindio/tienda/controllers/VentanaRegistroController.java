package co.edu.uniquindio.tienda.controllers;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.util.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaRegistroController {
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	@FXML
    private Text titleNombre;

    @FXML
    private TextField txtNombre;

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
    private Button btnRegistrar;
    
    public VentanaRegistroController() {
    	
    }
    
    public void initialize() {
        
    }

    @FXML
    public void registrar() {
    	mfm.tienda.registrarCliente(txtIDNuevo.getText(), txtNombre.getText(), txtDireccion.getText());
    	ventana.close();
    	
    	Persistencia.serialize("src/archivo.dat", mfm.getTienda());
    }
    
    public void setAplicacionPrincipal(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
	}

	public void setVentana(Stage primaryStage) {
		this.ventana=primaryStage;
	}

}
