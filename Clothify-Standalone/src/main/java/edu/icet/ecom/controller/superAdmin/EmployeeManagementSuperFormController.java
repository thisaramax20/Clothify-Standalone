package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.EmployeeServiceImpl;
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

public class EmployeeManagementSuperFormController implements Initializable {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public DatePicker txtDOB;
    public TableView<Employee> tblEmployeeDetals;
    public TableColumn colID;
    public TableColumn colNIC;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colEmail;
    public JFXTextField txtId;
    EmployeeServiceImpl employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

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

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
        boolean executed = employeeService.save(new Employee(null,txtNIC.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtEmail.getText()
        ));
        if (executed) {
            new Alert(Alert.AlertType.INFORMATION,"Successfully added.🆗").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR, "There was an error. Please try again or contact your supervisor.").show();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        boolean executed = employeeService.delete(Integer.parseInt(txtId.getText()));
        if (executed) {
            new Alert(Alert.AlertType.INFORMATION,"Successfully deleted.🆗").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR, "There was an error. Please try again or contact your supervisor.").show();
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        boolean executed = employeeService.update(new Employee(null,txtNIC.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtEmail.getText()
        ),Integer.parseInt(txtId.getText()));
        if (executed) {
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated.🆗").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR, "There was an error. Please try again or contact your supervisor.").show();
        }
    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
        Employee employee = employeeService.getById(Integer.parseInt(txtId.getText()));
        if (employee!=null) {
            setSelectedFields(employee);
        }else {
            new Alert(Alert.AlertType.ERROR,"There is no one by that name.").show();
        }
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void loadTable(){
        List<Employee> all = employeeService.getAll();
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employees.addAll(all);
        tblEmployeeDetals.setItems(employees);
    }

    private void clearFields(){
        txtId.setText("");
        txtAddress.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtEmail.setText("");
    }

    private void setSelectedFields(Employee employee){
        txtId.setText(employee.getId().toString());
        txtName.setText(employee.getName());
        txtNIC.setText(employee.getNic());
        txtAddress.setText(employee.getAddress());
        txtEmail.setText(employee.getEmail());
        txtDOB.setValue(employee.getDob());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        loadTable();
        tblEmployeeDetals.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) ->{
            if (current!=null) setSelectedFields(current);
        } );
    }
}
