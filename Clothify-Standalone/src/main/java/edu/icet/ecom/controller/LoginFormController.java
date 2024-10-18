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

import java.io.IOException;

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
        if (isOtp){
            if (txtPassword.getText().equals(otp)){
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
        lblPassword_OTP.setText("OTP");
        otp = loginService.sendEmail(txtUsername.getText());
        isOtp = true;
    }

    public static boolean getIsOtp(){
        return isOtp;
    }
}
