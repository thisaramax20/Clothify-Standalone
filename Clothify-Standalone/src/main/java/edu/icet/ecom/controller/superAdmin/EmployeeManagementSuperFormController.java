package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.EmployeeServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class EmployeeManagementSuperFormController {
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
        EmployeeServiceImpl employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        boolean executed = employeeService.save(new Employee(txtNIC.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue(),
                txtEmail.getText()
        ));
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Successful").show();
        }
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
