package edu.icet.ecom.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUsername;
    public Label lblPassword_OTP;
    public JFXTextField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        Stage stage = LandingPageController.stage;
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/superAdminDashBoard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnForgotPasswordOnAction(ActionEvent actionEvent) {
        lblPassword_OTP.setText("OTP");
    }
}
