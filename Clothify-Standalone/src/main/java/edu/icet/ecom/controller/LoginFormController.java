package edu.icet.ecom.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static edu.icet.ecom.controller.LandingPageController.stage;

public class LoginFormController {
    public JFXTextField txtUsername;
    public Label lblPassword_OTP;
    public JFXTextField txtPassword;
    public static Stage stage = LandingPageController.getStage();

    public void btnLoginOnAction(ActionEvent actionEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/superAdmin/superAdminDashBoard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnForgotPasswordOnAction(ActionEvent actionEvent) {
        lblPassword_OTP.setText("OTP");
    }
}
