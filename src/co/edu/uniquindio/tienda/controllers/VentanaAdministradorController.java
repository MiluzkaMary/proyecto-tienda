package co.edu.uniquindio.tienda.controllers;

import co.edu.uniquindio.tienda.application.Aplicacion;
import co.edu.uniquindio.tienda.model.Administrador;
import co.edu.uniquindio.tienda.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaAdministradorController {
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana=mfm.getVentana();
	private Aplicacion aplicacion=mfm.getAplicacion();
	private Administrador admin;
	private String documento;
	
	@FXML
    private Button btnCatalogo;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnHistoricoVentas;

    @FXML
    private Text txtBienvenida;
    
    public VentanaAdministradorController() {
    	
    }
    
    public void Initialize() {
    	
    }

    @FXML
    void verCatalogo() {
    	aplicacion.iniciarVentanaCatalogo(false, this.documento);
    }

    @FXML
    void verHistoricoVentas() {
    	aplicacion.iniciarVentanaHistoricoVentas(documento);

    }

    @FXML
    void verInventario() {

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

	
	public void setDocumento(String documento) {
		System.out.print("dentro de ventana admin "+documento);
		this.documento=documento;
		this.admin=mfm.buscarAdmin(documento);
		txtBienvenida.setText("¡Bienvenid@, "+ admin.getNombre() + "!" );
	}
	

}
