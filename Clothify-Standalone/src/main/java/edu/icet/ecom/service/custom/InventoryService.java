package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.service.SuperService;
import javafx.scene.image.Image;

import java.util.List;

public interface InventoryService extends SuperService {
    boolean save(Inventory inventory);
    boolean delete(Integer id);
    boolean update(Inventory inventory,Integer id);
    List<Inventory> getAll();
    Inventory getById(Integer id);
    Image getImage(Integer id);
}
