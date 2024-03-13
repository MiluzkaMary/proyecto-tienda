package co.edu.uniquindio.tienda.application;

import java.io.IOException;

import co.edu.uniquindio.tienda.controllers.ModelFactoryController;
import co.edu.uniquindio.tienda.controllers.VentanaAdministradorController;
import co.edu.uniquindio.tienda.controllers.VentanaCarritoController;
import co.edu.uniquindio.tienda.controllers.VentanaCatalogoController;
import co.edu.uniquindio.tienda.controllers.VentanaClienteController;
import co.edu.uniquindio.tienda.controllers.VentanaHistoricoVentasController;
import co.edu.uniquindio.tienda.controllers.VentanaInicioController;
import co.edu.uniquindio.tienda.controllers.VentanaRegistroController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Aplicacion extends Application {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		ModelFactoryController mfm = ModelFactoryController.getInstance();
		mfm.setAplicacion(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tienda Akira");
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/logoTienda.png"))); //logo en la ventana
		this.primaryStage.setResizable(false); //para prohibir al usuario maximizar la ventana
		mostrarLogin();
	}
	
	/**
	 * Inicia la ventana principal
	 */
	public void mostrarLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaInicio.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			VentanaInicioController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarVentanaAdministrador(String documento) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaAdmin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Administrador Inicio");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(primaryStage);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			VentanaAdministradorController controller = loader.getController();
			controller.setDocumento(documento);
			controller.setAplicacionPrincipal(this);
			controller.setVentana(ventana);
			
			ventana.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarVentanaCliente(String documento) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaCliente.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Cliente Inicio");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(primaryStage);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			VentanaClienteController controller = loader.getController();
			controller.setAplicacionPrincipal(this);
			controller.setDocumentoCliente(documento);
			controller.setVentana(ventana);
			controller.iniciarDatos();
			ventana.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarVentanaCatalogo(Boolean esCliente, String documento) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaCatalogo.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Catálogo");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(primaryStage);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			VentanaCatalogoController controller = loader.getController();
			controller.setAplicacionPrincipal(this);
			controller.setVentana(ventana);
			controller.setEsCliente(esCliente);
			if (esCliente) {
				controller.setDocumentoCliente(documento);
			}else {
				controller.setDocumentoAdmin(documento);
			}
			controller.decidirPanelClienteAdmin();
			ventana.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarVentanaRegistro() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaRegistro.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Registro de Clientes");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(primaryStage);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			VentanaRegistroController controller = loader.getController();
			controller.setAplicacionPrincipal(this);
			controller.setVentana(ventana);
			ventana.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarVentanaCarritoCliente (String documento) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaMiCarrito.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Carrito de Compras");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(primaryStage);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			VentanaCarritoController controller = loader.getController();
			controller.setAplicacionPrincipal(this);
			controller.setVentana(ventana);
			controller.setDocumentoCliente(documento);
			controller.actualizarTabla();
			ventana.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarVentanaHistoricoVentas (String documento) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/tienda/view/VentanaHistoricoVentas.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ventana = new Stage();
			ventana.setTitle("Historico de Ventas");
			ventana.initModality(Modality.WINDOW_MODAL);
			ventana.initOwner(primaryStage);
			Scene scene = new Scene(page);
			ventana.setScene(scene);
			VentanaHistoricoVentasController controller = loader.getController();
			controller.setAplicacionPrincipal(this);
			controller.setVentana(ventana);
			controller.setDocumentoCliente(documento);
			controller.actualizarTabla();
			ventana.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void iniciarVentanaCompras (String documento) {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
