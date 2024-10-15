package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.impl.InventoryDaoImpl;
import edu.icet.ecom.service.custom.InventoryService;
import edu.icet.ecom.util.DaoType;
import javafx.scene.image.Image;
import org.modelmapper.ModelMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    InventoryDaoImpl inventoryDao = DaoFactory.getInstance().getDaoType(DaoType.INVENTORY);

    @Override
    public boolean save(Inventory inventory) {
        edu.icet.ecom.entity.Inventory inventoryEntity = new ModelMapper().map(inventory, edu.icet.ecom.entity.Inventory.class);
        if (inventory.getImagePath()!=null){
            File imageFile = new File(inventory.getImagePath());
            try {
                FileInputStream inputStream = new FileInputStream(imageFile);
                byte[] imageBytes = new byte[(int) imageFile.length()];
                inputStream.read(imageBytes);
                inputStream.close();

                inventoryEntity.setImageData(imageBytes);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else inventoryEntity.setImageData(null);

        inventoryEntity.getId().setItemCode(inventoryEntity.getSupplierItem().getCompositePKSupplierItem().getItemCode());
        return inventoryDao.save(inventoryEntity);
    }

    @Override
    public boolean delete(String id) {
        String supplierId = "";
        for (Inventory inventory :getAll()){
            if (inventory.getId().getItemCode().equals(id)) supplierId = inventory.getId().getSupplierId();
        }
        return inventoryDao.deleteByCompositePK(new CompositePK_SupplierItem(supplierId,id));
    }

    @Override
    public boolean update(Inventory inventory) {
        edu.icet.ecom.entity.Inventory inventory1 = new ModelMapper().map(inventory, edu.icet.ecom.entity.Inventory.class);
        if (inventory.getImagePath()!=null){
            File imageFile = new File(inventory.getImagePath());
            try {
                FileInputStream inputStream = new FileInputStream(imageFile);
                byte[] imageBytes = new byte[(int) imageFile.length()];
                inputStream.read(imageBytes);
                inputStream.close();

                inventory1.setImageData(imageBytes);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            inventory1.setImageData(null);
        }

        return inventoryDao.update(inventory1);
    }

    @Override
    public List<Inventory> getAll() {
        List<edu.icet.ecom.entity.Inventory> all = inventoryDao.getAll();
        ArrayList<Inventory> inventories = new ArrayList<>();
        all.forEach(inventory -> inventories.add(new ModelMapper().map(inventory, Inventory.class)));
        return inventories;
    }

    @Override
    public Inventory getById(String id) {
        String supplierId = "";
        for (Inventory inventory :getAll()){
            if (inventory.getId().getItemCode().equals(id)) supplierId = inventory.getId().getSupplierId();
        }
        return new ModelMapper().map(inventoryDao.searchByCompositePK(new CompositePK_SupplierItem(supplierId,
                id)), Inventory.class);
    }

    @Override
    public Image getImage(String itemCode) {
        byte[] imageData = inventoryDao.getImageData(itemCode);
        System.out.println(imageData);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return new Image(bis);
    }

    @Override
    public List<String> getAllIds() {
        List<Inventory> all = getAll();
        ArrayList<String > ids = new ArrayList<>();
        all.forEach(inventory -> ids.add(inventory.getId().getItemCode()));
        return ids;
    }
}
