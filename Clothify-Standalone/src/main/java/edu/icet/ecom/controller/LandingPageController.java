package edu.icet.ecom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class LandingPageController {
    @Getter
    public static Stage stage;

    public void btnLoadLoginFormOnAction(ActionEvent actionEvent) {
        try {
            stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/loginForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
