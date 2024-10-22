package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.controller.LoginFormController;
import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.dto.OrderDetails;
import edu.icet.ecom.dto.Orders;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.impl.OrderDetailServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtPrice;
    public TableView<OrderDetails> tblOrders;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public JFXComboBox<String> cmbItemCode;
    public Label lblAdminID;
    public Label lblAdminName;
    public Label lblDate;
    public JFXComboBox<String> cmbTransactionType;
    public Label lblNetTotal;
    public Label lblTime;
    public JFXTextField txtItemDescription;
    public JFXTextField txtIQuantity;
    public Label lblOrderID;
    private final Stage stage = LandingPageController.getStage();
    public JFXTextField txtIStockAvailble;
    public JFXTextField txtOrderId;
    Double total = 0.0;
    private String adminName;
    private Integer adminId;
    private Integer stock;
    private String admin;
    OrderDetailServiceImpl orderDetail = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAIL);
    ObservableList<OrderDetails> orderDetailsOngoing = FXCollections.observableArrayList();

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
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/supplierManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadOrderDetailPageOnAction(MouseEvent mouseEvent) {
        return;
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
        String codeText = colItemCode.getText();
        OrderDetails orderDetails;
        for (OrderDetails details : orderDetailsOngoing){
            if(details.getItemCode().equals(codeText)) {
                orderDetails = details;
                orderDetailsOngoing.remove(orderDetails);
            }
        }
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields(){
        txtPrice.setText("");
        txtItemDescription.setText("");
        txtIStockAvailble.setText("");
        txtOrderId.setText("");
        txtCustomerEmail.setText("");
        txtIQuantity.setText("");
        cmbItemCode.setValue("");
        cmbTransactionType.setValue("");
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        Integer quantity = Integer.parseInt(txtIQuantity.getText());
        if (quantity>stock){
            new Alert(Alert.AlertType.ERROR,"Stock is less than what you order.").show();
        }else{
            orderDetailsOngoing.add(new OrderDetails(null,lblOrderID.getText(),
                    cmbItemCode.getValue(),
                    txtItemDescription.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    quantity
            ));
            tblOrders.setItems(orderDetailsOngoing);
        }
    }

    public void btnProceedToNetTotalOnAction(ActionEvent actionEvent) {
        orderDetailsOngoing.forEach(orderDetails -> {
            total+=orderDetails.getPrice()*orderDetails.getQuantity();
        });
        lblNetTotal.setText(total.toString());
    }

    public void btnPayementCompleteOnAction(ActionEvent actionEvent) {
        boolean executed = orderDetail.save(new Orders(null, txtCustomerEmail.getText(),
                lblOrderID.getText(),
                cmbTransactionType.getValue(),
                total,
                adminId,
                adminName,
                orderDetailsOngoing)
        );
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
            nextOrder();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) {
        List<OrderDetails> detailsDto = orderDetail.getOrderDetails(txtOrderId.getText());
        if (!detailsDto.isEmpty()) {
            ObservableList<OrderDetails> orderDetails1 = FXCollections.observableArrayList();
            orderDetails1.addAll(detailsDto);
            tblOrders.setItems(orderDetails1);
        }else new Alert(Alert.AlertType.ERROR,"There is no order by that id").show();
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        boolean executed = orderDetail.delete(txtOrderId.getText());
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void setAdminDetails(){
        Admin admin1 = orderDetail.getAdmin(admin);
        adminId = admin1.getId();
        lblAdminID.setText(adminId.toString());
        adminName =admin1.getName();
        lblAdminName.setText(adminName);
    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + "-" + now.getMinute() + "-" + now.getSecond());
        }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setItemCodes(){
        List<String> allids = orderDetail.getAllids();
        ObservableList<String> ids = FXCollections.observableArrayList();
        ids.addAll(allids);
        cmbItemCode.setItems(ids);
    }

    private void setTransactionType(){
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Cash");
        types.add("Card");
        cmbTransactionType.setItems(types);
    }

    private void setItemDetails(String itemCode){
        Inventory inventory = orderDetail.getByIdItem(itemCode);
        stock = inventory.getQuantity();
        txtIStockAvailble.setText(stock.toString());
        txtPrice.setText(inventory.getPrice().toString());
        txtItemDescription.setText(inventory.getName());
    }

    private void setOrderId(){
        lblOrderID.setText(orderDetail.getOrderId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = LoginFormController.username;
        setAdminDetails();
        loadDateAndTime();
        setItemCodes();
        setTransactionType();
        setOrderId();
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setItemDetails(current);
        });
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
    }

    public void btnNextOrderOnAction(ActionEvent actionEvent) {
        nextOrder();
    }

    private void nextOrder(){
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/superAdmin/OrderManagementSuper.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
