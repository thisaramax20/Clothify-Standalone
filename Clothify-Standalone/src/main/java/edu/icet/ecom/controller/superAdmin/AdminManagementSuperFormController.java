package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.impl.AdminServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    public JFXTextField txtTelephone;
    AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
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
        return;
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
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/reportManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddAdminOnAction(ActionEvent actionEvent) {
        boolean executed = adminService.save(new Admin(null, txtNIC.getText(),
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtTelephone.getText(),
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
        boolean executed = adminService.delete(txtId.getText());
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateAdminOnAction(ActionEvent actionEvent) {
        boolean executed = adminService.update(new Admin(null, txtNIC.getText(),
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtTelephone.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        ));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchAdminOnAction(ActionEvent actionEvent) {
        adminService.getById((txtId.getText()));
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
        colID.setCellValueFactory(new PropertyValueFactory<>("username"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //loadTable();
        tblAdminDetals.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setSelectedValues(current);
        });
    }
}
