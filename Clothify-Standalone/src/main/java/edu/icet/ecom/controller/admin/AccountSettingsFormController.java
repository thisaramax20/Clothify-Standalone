package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.controller.LoginFormController;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.impl.AdminServiceImpl;
import edu.icet.ecom.service.custom.impl.LoginServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountSettingsFormController implements Initializable {
    public JFXTextField txtCurrentPassword;
    public JFXTextField txtTempNewPassword;
    public JFXTextField txtNewPassword;
    private final Stage stage = LandingPageController.getStage();
    private boolean isOtp = LoginFormController.getIsOtp();

    public void btnLoadHomePageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/adminDashBoard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadEmployeeManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/employeeManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadInventoryManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/inventoryManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadSupplierManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/supplierManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadOrderDetailPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/orderManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnProceedOnAction(ActionEvent actionEvent) {
        LoginServiceImpl loginService = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        String username = LoginFormController.username;
        boolean executed = false;
        String newPassword = txtNewPassword.getText();
        AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
        if (isOtp){
            if (newPassword.length()>=8){
                executed = adminService.changePassword(username, newPassword);
                if (executed){
                    new Alert(Alert.AlertType.INFORMATION,"Success").show();
                }
            }
        }else{
            if(loginService.validateUser(username,txtCurrentPassword.getText())){
                if (txtTempNewPassword.getText().equals(newPassword) && newPassword.length()>=8){
                    executed = adminService.changePassword(username, newPassword);
                    if (executed){
                        new Alert(Alert.AlertType.INFORMATION,"Success").show();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Your new password combination does not match or password is too small.").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Your old password is incorrect.").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isOtp) txtCurrentPassword.setDisable(true);
    }
}
