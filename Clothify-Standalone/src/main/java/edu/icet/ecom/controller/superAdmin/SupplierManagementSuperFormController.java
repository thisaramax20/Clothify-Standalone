package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.dto.Supplier;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.SupplierServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierManagementSuperFormController implements Initializable {
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
    public TableView tblItemSupplier;
    public TableColumn colSupplierID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemCategory;
    public JFXComboBox<String> cmbItemCategory;
    public JFXTextField txtId;
    SupplierServiceImpl supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

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

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        boolean executed = supplierService.save(new Supplier(null, txtNIC.getText(),
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
        boolean executed = supplierService.delete(Integer.parseInt(txtId.getText()));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        boolean executed = supplierService.update(new Supplier(null, txtNIC.getText(),
                txtName.getText(),
                txtCompany.getText()
        ),Integer.parseInt(txtId.getText()));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success");
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        setSelectedvalues(supplierService.getById(Integer.parseInt(txtId.getText())));
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
    }
}
