module Tienda {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens co.edu.uniquindio.tienda.application to javafx.graphics;
    opens co.edu.uniquindio.tienda.controllers to javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	
}
