package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class OrderManagementFormController {
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtPrice;
    public TableView tblOrders;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colTotal;
    public JFXComboBox cmbItemCode;
    public Label lblAdminID;
    public Label lblAdminName;
    public Label lblDate;
    public JFXComboBox cmbTransactionType;
    public Label lblNetTotal;
    public Label lblTime;
    public JFXTextField txtItemDescription;
    public JFXTextField txtIQuantity;
    public Label lblOrderID;

    public void btnLoadHomePageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadEmployeeManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadInventoryManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadSupplierManagementPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnLoadOrderDetailPageOnAction(MouseEvent mouseEvent) {
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) {
    }

    public void btnProceedToNetTotalOnAction(ActionEvent actionEvent) {
    }

    public void btnPayementCompleteOnAction(ActionEvent actionEvent) {
    }
}
