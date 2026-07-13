module fis.dsw.sgc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;

    requires java.sql;
    requires java.desktop;
    requires javafx.base;

    // Paquetes abiertos para que FXML pueda usar los controladores
    opens fis.dsw.sgc.administracion.controller to javafx.fxml;
    opens fis.dsw.sgc.administracion.dashboard to javafx.fxml;
    opens fis.dsw.sgc.inmuebles.controller to javafx.fxml;
    opens fis.dsw.sgc.finanzas.controller to javafx.fxml;
    opens fis.dsw.sgc.app to javafx.fxml;
    exports fis.dsw.sgc.app;
}
