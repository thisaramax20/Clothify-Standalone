package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.AdminServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminManagementSuperFormController implements Initializable {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;
    public DatePicker txtDOB;
    public TableColumn colID;
    public TableColumn colNIC;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colEmail;
    public JFXTextField txtId;
    public TableView<Admin> tblAdminDetals;
    AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);

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

    public void btnAddAdminOnAction(ActionEvent actionEvent) {
        boolean executed = adminService.save(new Admin(null, txtNIC.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtPassword.getText(),
                txtEmail.getText()
        ));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnDeleteAdminOnAction(ActionEvent actionEvent) {
        boolean executed = adminService.delete(Integer.parseInt(txtId.getText()));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateAdminOnAction(ActionEvent actionEvent) {
        boolean executed = adminService.update(new Admin(null, txtNIC.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtEmail.getText(),
                txtPassword.getText()
        ),Integer.parseInt(txtId.getText()));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchAdminOnAction(ActionEvent actionEvent) {
        adminService.getById(Integer.parseInt(txtId.getText()));
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields(){
        txtId.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }

    private void setSelectedValues(Admin admin ){
        txtId.setText(admin.getId().toString());
        txtName.setText(admin.getName());
        txtNIC.setText(admin.getNic());
        txtAddress.setText(admin.getAddress());
        txtEmail.setText(admin.getEmail());
        txtDOB.setValue(admin.getDob());
    }

    private void loadTable(){
        List<Admin> all = adminService.getAll();
        ObservableList<Admin> admins = FXCollections.observableArrayList();
        admins.addAll(all);
        tblAdminDetals.setItems(admins);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
        tblAdminDetals.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setSelectedValues(current);
        });
    }
}
