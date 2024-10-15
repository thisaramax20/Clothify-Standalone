package edu.icet.ecom.controller.superAdmin;

import edu.icet.ecom.controller.LandingPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportManagementSuperFormController {
    public static Stage stage = LandingPageController.getStage();

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
        return;
    }

    public void btnDailyReportOnAction(ActionEvent actionEvent) {
    }

    public void btnMonthlyReportOnAction(ActionEvent actionEvent) {
    }

    public void btnAnnualReportOnAction(ActionEvent actionEvent) {

    }
}
