package edu.icet.ecom.controller.superAdmin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.impl.InventoryServiceImpl;
import edu.icet.ecom.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryManagementSuperFormController implements Initializable {
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantity;
    public TableView<Inventory> tblInventory;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colCategory;
    public ImageView imageView;
    public JFXComboBox<String> cmbSize;
    public JFXComboBox<String> cmbCategory;
    public JFXTextField txtId;
    InventoryServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.INVENTORY);
    private String imageFilePath;

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
        boolean executed = service.save(new Inventory(null,
                null,
                txtName.getText(),
                cmbSize.getValue(),
                Double.parseDouble(txtPrice.getText()),
                cmbCategory.getValue(),
                Integer.parseInt(txtQuantity.getText()),
                imageFilePath)
        );
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Successful").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
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
        boolean executed = service.update(new Inventory(null,
                txtId.getText(),
                txtName.getText(),
                cmbSize.getValue(),
                Double.parseDouble(txtPrice.getText()),
                cmbCategory.getValue(),
                Integer.parseInt(txtQuantity.getText()),
                imageFilePath)
        );
        if (executed){
            new Alert(Alert.AlertType.INFORMATION,"Successful").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        service.getById(txtId.getText());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSizeValuesToCombo();
        setCategoryValuesToCombo();
        colID.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadTable();
        tblInventory.getSelectionModel().selectedItemProperty().addListener((observableValue, previous, current) -> {
            if (current!=null) setSelectedValues(current);
        });
    }

    private void loadTable(){
        List<Inventory> all = service.getAll();
        ObservableList<Inventory> inventories = FXCollections.observableArrayList();
        inventories.addAll(all);
        tblInventory.setItems(inventories);
    }

    private void setSelectedValues(Inventory inventory){
        txtId.setText(inventory.getItemCode());
        txtQuantity.setText(inventory.getQuantity().toString());
        txtPrice.setText(inventory.getPrice().toString());
        txtName.setText(inventory.getName());
        cmbCategory.setValue(inventory.getCategory());
        cmbSize.setValue(inventory.getSize());
        Image image = service.getImage(inventory.getItemCode());
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
}
