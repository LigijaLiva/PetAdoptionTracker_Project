module nosaukums {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
