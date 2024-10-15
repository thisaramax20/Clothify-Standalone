package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LoginFormController;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.AdminServiceImpl;
import edu.icet.ecom.service.custom.impl.LoginServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class AccountSettingsSuperFormController {
    public JFXTextField txtCurrentPassword;
    public JFXTextField txtTempNewPassword;
    public JFXTextField txtNewPassword;

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

    public void btnProceedOnAction(ActionEvent actionEvent) {
        LoginServiceImpl loginService = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        String username = LoginFormController.username;
        boolean executed = false;
        if(loginService.validateUser(username,txtCurrentPassword.getText())){
            String newPassword = txtNewPassword.getText();
            if (txtTempNewPassword.getText().equals(newPassword)){
                AdminServiceImpl adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
                 executed = adminService.changePassword(username, newPassword);
            }else{
                new Alert(Alert.AlertType.ERROR,"Your new password combination does not match.").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Your old password is incorrect.").show();
        }
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }
}
