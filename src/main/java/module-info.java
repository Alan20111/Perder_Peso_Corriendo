module com.practica1.practica1top {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.practica1.practica1top to javafx.fxml;
    exports com.practica1.practica1top;
}