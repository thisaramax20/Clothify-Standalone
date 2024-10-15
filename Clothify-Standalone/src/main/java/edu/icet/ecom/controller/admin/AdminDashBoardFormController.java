package edu.icet.ecom.controller.admin;

import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.controller.LoginFormController;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.AdminServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashBoardFormController implements Initializable {
    public static Stage stage = LandingPageController.getStage();

    @FXML
    private Label lblAdminName;

    @FXML
    void btnLoadAccountSettingsPageOnAction(MouseEvent event) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/accountSettings.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoadEmployeeManagementPageOnAction(MouseEvent event) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/employeeManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoadHomePageOnAction(MouseEvent event) {
        return;
    }

    @FXML
    void btnLoadInventoryManagementPageOnAction(MouseEvent event) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/inventoryManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoadOrderDetailPageOnAction(MouseEvent event) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/orderManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoadSupplierManagementPageOnAction(MouseEvent event) {
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/supplierManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(MouseEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
        lblAdminName.setText(adminService.getById(LoginFormController.username).getName());
    }
}
