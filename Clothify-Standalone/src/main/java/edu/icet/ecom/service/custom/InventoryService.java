package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.service.SuperService;
import javafx.scene.image.Image;

import java.util.List;

public interface InventoryService extends SuperService {
    boolean save(Inventory inventory);
    boolean delete(String itemCode);
    boolean update(Inventory inventory);
    List<Inventory> getAll();
    Inventory getById(String itemCode);
    Image getImage(String itemCode);
    List<String> getAllIds();
}
