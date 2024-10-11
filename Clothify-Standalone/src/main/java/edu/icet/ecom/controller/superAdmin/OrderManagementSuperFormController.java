package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderManagementSuperFormController implements Initializable {
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtPrice;
    public TableView<OrderDetails> tblOrders;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public JFXComboBox<Integer> cmbItemCode;
    public Label lblAdminID;
    public Label lblAdminName;
    public Label lblDate;
    public JFXComboBox<String> cmbTransactionType;
    public Label lblNetTotal;
    public Label lblTime;
    public JFXTextField txtItemDescription;
    public JFXTextField txtIQuantity;
    public Label lblOrderID;
    public JFXTextField txtIStockAvailble;
    private String admin;
    OrderDetailServiceImpl orderDetail = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAIL);
    ObservableList<OrderDetails> orderDetailsOngoing = FXCollections.observableArrayList();
    Double total = 0.0;
    private String adminName;
    private Integer adminId;
    private Integer stock;

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

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
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
        }else{
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
        List<Integer> allids = orderDetail.getAllids();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        ids.addAll(allids);
        cmbItemCode.setItems(ids);
    }

    private void setTransactionType(){
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Cash");
        types.add("Card");
        cmbTransactionType.setItems(types);
    }

    private void setItemDetails(Integer id){
        Inventory inventory = orderDetail.getByIdItem(id);
        stock = inventory.getQuantity();
        txtIStockAvailble.setText(stock.toString());
        txtPrice.setText(inventory.getPrice().toString());
        txtItemDescription.setText(inventory.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = LoginFormController.username;
        setAdminDetails();
        loadDateAndTime();
        setItemCodes();
        setTransactionType();
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setItemDetails(current);
        });
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
    }
}
