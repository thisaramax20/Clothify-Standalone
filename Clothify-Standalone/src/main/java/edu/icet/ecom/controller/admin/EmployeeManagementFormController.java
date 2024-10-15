package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeManagementFormController {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public DatePicker txtDOB;
    public TableView tblEmployeeDetals;
    public TableColumn colID;
    public TableColumn colNIC;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colEmail;
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
        return;
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

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {

    }
}
