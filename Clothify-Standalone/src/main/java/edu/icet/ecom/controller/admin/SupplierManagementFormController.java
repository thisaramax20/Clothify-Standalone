package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.dto.Supplier;
import edu.icet.ecom.dto.SupplierItem;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.impl.SupplierItemServiceImpl;
import edu.icet.ecom.service.custom.impl.SupplierServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierManagementFormController implements Initializable {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtCompany;
    public TableView<Supplier> tblSupplier;
    public TableColumn colID;
    public TableColumn colNIC;
    public TableColumn colName;
    public TableColumn colCompany;
    public JFXTextField txtItemName;
    public JFXComboBox<Integer> cmbSupplierId;
    public TableView<SupplierItem> tblItemSupplier;
    public TableColumn colSupplierID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemCategory;
    public JFXComboBox<String> cmbItemCategory;
    public static Stage stage = LandingPageController.getStage();
    public JFXTextField txtId;
    public JFXTextField txtItemCode;
    SupplierServiceImpl supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    SupplierItemServiceImpl supplierItemService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIERITEM);

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
        boolean executed = supplierItemService.save(new SupplierItem(cmbSupplierId.getValue(), null,
                txtItemName.getText(),
                cmbItemCategory.getValue()
        ));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable2();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
        boolean executed = supplierItemService.delete(new CompositePK_SupplierItem(cmbSupplierId.getValue(),
                Integer.parseInt(txtItemCode.getText())));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable2();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
        boolean executed = supplierItemService.update(new SupplierItem(cmbSupplierId.getValue(),
                Integer.parseInt(txtItemCode.getText()
                ), txtItemName.getText(), cmbItemCategory.getValue()), new CompositePK_SupplierItem(cmbSupplierId.getValue(),
                Integer.parseInt(txtItemCode.getText())));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable2();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        supplierItemService.getById(new CompositePK_SupplierItem(cmbSupplierId.getValue(),
                Integer.parseInt(txtItemCode.getText())
        ));
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields(){
        txtItemCode.setText("");
        cmbItemCategory.setValue("");
        txtItemName.setText("");
        cmbSupplierId.setValue(0);
    }

    private void loadTable2(){
        List<SupplierItem> all = supplierItemService.getAll();
        ObservableList<SupplierItem> supplierItems = FXCollections.observableArrayList();
        supplierItems.addAll(all);
        tblItemSupplier.setItems(supplierItems);
    }

    private void setSelectedValues2(SupplierItem supplierItem){
        cmbSupplierId.setValue(supplierItem.getSupplierId());
        txtItemCode.setText(supplierItem.getItemCode().toString());
        txtItemName.setText(supplierItem.getName());
        cmbItemCategory.setValue(supplierItem.getCategory());
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        boolean executed = supplierService.save(new Supplier(null, txtNIC.getText(),null,
                txtName.getText(),
                txtCompany.getText()
        ));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
            setSupplierIds();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
        boolean executed = supplierService.delete(txtId.getText());
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        boolean executed = supplierService.update(new Supplier(null,txtId.getText(),
                txtNIC.getText(),
                txtName.getText(),
                txtCompany.getText()
        ));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success");
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        setSelectedvalues(supplierService.getById(txtId.getText()));
    }

    private void loadTable(){
        List<Supplier> all = supplierService.getAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        suppliers.addAll(all);
        tblSupplier.setItems(suppliers);
    }

    private void setSelectedvalues(Supplier supplier){
        txtId.setText(supplier.getId().toString());
        txtNIC.setText(supplier.getNic());
        txtName.setText(supplier.getName());
        txtCompany.setText(supplier.getCompany());
    }

    private void setSupplierIds(){
        List<Integer> allIds = supplierService.getAllIds();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        ids.addAll(allIds);
        cmbSupplierId.setItems(ids);
    }

    private void setCmbItemCategory(){
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add("Men");
        category.add("Women");
        category.add("Kids");
        cmbItemCategory.setItems(category);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        loadTable();
        setSupplierIds();
        setCmbItemCategory();
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setSelectedvalues(current);
        });

        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        loadTable2();
        tblItemSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setSelectedValues2(current);
        });
    }
}
