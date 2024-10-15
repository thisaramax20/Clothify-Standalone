package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementFormController {
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantity;
    public TableView tblInventory;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colCategory;
    public ImageView imageView;
    public JFXComboBox cmbSize;
    public JFXComboBox cmbCategory;
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
        return;
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

    public void btnSelectImageOnAction(ActionEvent actionEvent) {

    }
}
