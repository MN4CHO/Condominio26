package fis.dsw.sgc.administracion.controller;

import fis.dsw.sgc.core.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class loginController {

    @FXML
    private Button loginButton;

    @FXML
    void login(ActionEvent event) throws IOException {

        String vista = "/administracion/fxml/dashboard.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(vista));
        NavigationUtil.changeScene(event, root);

    }

}
