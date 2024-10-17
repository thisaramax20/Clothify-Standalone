package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.controller.LoginFormController;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
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

public class AccountSettingsSuperFormController {
    public JFXTextField txtCurrentPassword;
    public JFXTextField txtTempNewPassword;
    public JFXTextField txtNewPassword;
    private final Stage stage = LandingPageController.getStage();

    public void btnLoadHomePageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/superAdminDashBoard.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadAdminManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/adminManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadEmployeeManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/employeeManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadInventoryManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/inventoryManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadSupplierManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/supplierManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadOrderDetailPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/OrderManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadReportManagementPageOnAction(MouseEvent mouseEvent) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/reportManagementSuper.fxml"))));
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
            if (txtTempNewPassword.getText().equals(newPassword) && newPassword.length()>=8){
                AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
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
