package edu.icet.ecom.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.impl.LoginServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginFormController {
    public JFXTextField txtUsername;
    public Label lblPassword_OTP;
    public JFXTextField txtPassword;
    public static Stage stage = LandingPageController.getStage();
    LoginServiceImpl loginService = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
    private String otp;
    private static boolean isOtp = false;
    public static String username;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"You must fill out all fields").show();
            return;
        }
        if (isOtp){
            if (txtPassword.getText().equals(otp)){
                username = txtUsername.getText();
                stage.close();
                if (txtUsername.getText().startsWith("AD")){
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/admin/adminDashBoard.fxml"))));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/superAdmin/superAdminDashBoard.fxml"))));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Password and username combinations don't match.").show();
            }

        }else {
            boolean executed = loginService.validateUser(txtUsername.getText(), txtPassword.getText());
            if (executed){
                username = txtUsername.getText();
                stage.close();
                if (txtUsername.getText().startsWith("AD")){
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/admin/adminDashBoard.fxml"))));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/superAdmin/superAdminDashBoard.fxml"))));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Password and username combinations don't match.").show();
            }
        }

    }

    public void btnForgotPasswordOnAction(ActionEvent actionEvent) {
        otp = loginService.sendEmail(txtUsername.getText());
        if (otp==null) {
            new Alert(Alert.AlertType.ERROR,"Please enter a valid username").show();
            return;
        }
        isOtp = true;
        new Alert(Alert.AlertType.INFORMATION,"Otp has been sent to your email.").show();
        lblPassword_OTP.setText("OTP");
    }

    public static boolean getIsOtp(){
        return isOtp;
    }
}
