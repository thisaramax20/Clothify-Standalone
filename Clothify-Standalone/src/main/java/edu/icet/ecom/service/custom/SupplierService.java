package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.dto.Supplier;
import edu.icet.ecom.service.SuperService;
import javafx.scene.image.Image;

import java.util.List;

public interface SupplierService extends SuperService {
    boolean save(Supplier supplier);
    boolean delete(String supplierCode);
    boolean update(Supplier supplier);
    List<Supplier> getAll();
    Supplier getById(String supplierCode);
    List<String> getAllIds();
}
