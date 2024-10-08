package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class InventoryManagementSuperFormController {
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

    public void btnLoadHomePageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadAdminManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadEmployeeManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadInventoryManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadSupplierManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadOrderDetailPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadReportManagementPageOnAction(MouseEvent mouseEvent) {
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
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            System.out.println(file.getAbsolutePath());
        }
    }
}
