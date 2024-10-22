package edu.icet.ecom.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.controller.LandingPageController;
import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.impl.InventoryServiceImpl;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryManagementFormController implements Initializable {
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantity;
    public TableView<Inventory> tblInventory;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colCategory;
    public ImageView imageView;
    public JFXComboBox<String> cmbSize;
    public JFXComboBox<String> cmbCategory;
    private final Stage stage = LandingPageController.getStage();
    public JFXTextField txtId;
    InventoryServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.INVENTORY);
    private String imageFilePath;
    private Inventory inventory1;
    private List<Inventory> all;

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
        return;
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
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/admin/orderManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
        boolean executed = service.delete(txtId.getText());
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Successful").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
        boolean executed = service.update(new Inventory(inventory1.getId(),
                txtName.getText(),
                cmbSize.getValue(),
                Double.parseDouble(txtPrice.getText()),
                cmbCategory.getValue(),
                Integer.parseInt(txtQuantity.getText()),
                imageFilePath, null,inventory1.getSupplierIdTemp())
        );
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Successful").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        Inventory byId = service.getById(txtId.getText());
        if (byId!=null){
            setSelectedValues(byId);
        }else {
            new Alert(Alert.AlertType.ERROR,"There is no item by that code.").show();
        }
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields(){
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }

    public void btnSelectImageOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            imageFilePath = file.getAbsolutePath();
        }
    }

    private void loadTable(){
        all = service.getAll();
        ObservableList<Inventory> inventories = FXCollections.observableArrayList();
        inventories.addAll(all);
        tblInventory.setItems(inventories);
    }

    private void setSelectedValues(Inventory inventory){
        txtId.setText(inventory.getId().getItemCode());
        txtName.setText(inventory.getName());
        cmbCategory.setValue(inventory.getCategory());
        txtQuantity.setText(inventory.getQuantity().toString());
        txtPrice.setText(inventory.getPrice().toString());
        cmbSize.setValue(inventory.getSize());
        Image image = service.getImage(inventory.getId().getItemCode());
        imageView.setImage(image);
    }

    private void setSizeValuesToCombo(){
        ObservableList<String> sizes = FXCollections.observableArrayList();
        sizes.add("Small");
        sizes.add("Medium");
        sizes.add("Large");
        sizes.add("Extra Large");
        cmbSize.setItems(sizes);
    }

    private void setCategoryValuesToCombo(){
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add("Men");
        category.add("Women");
        category.add("Kids");
        cmbCategory.setItems(category);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSizeValuesToCombo();
        setCategoryValuesToCombo();
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadTable();
        tblInventory.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) {
                inventory1 = current;
                setSelectedValues(current);
            }
        });
    }
}
