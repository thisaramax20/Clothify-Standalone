package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierManagementFormController {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtCompany;
    public TableView tblSupplier;
    public TableColumn colID;
    public TableColumn colNIC;
    public TableColumn colName;
    public TableColumn colCompany;
    public JFXTextField txtItemName;
    public JFXComboBox cmbSupplierId;
    public TableView tblItemSupplier;
    public TableColumn colSupplierID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemCategory;
    public JFXComboBox cmbItemCategory;
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
        return;
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

    public void btnAddItemOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
    }
}
