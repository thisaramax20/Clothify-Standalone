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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountSettingsFormController {
    public JFXTextField txtCurrentPassword;
    public JFXTextField txtTempNewPassword;
    public JFXTextField txtNewPassword;
    public static Stage stage = LandingPageController.getStage();

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
        if(loginService.validateUser(username,txtCurrentPassword.getText())){
            String newPassword = txtNewPassword.getText();
            if (txtTempNewPassword.getText().equals(newPassword)){
                AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
                executed = adminService.changePassword(username, newPassword);
            }else{
                new Alert(Alert.AlertType.ERROR,"Your new password combination does not match.").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Your old password is incorrect.").show();
        }
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }
}
