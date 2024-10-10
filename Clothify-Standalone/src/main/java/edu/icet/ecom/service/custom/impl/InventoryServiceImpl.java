package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Inventory;
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
        return inventoryDao.save(inventoryEntity);
    }

    @Override
    public boolean delete(Integer id) {
        return inventoryDao.delete(id);
    }

    @Override
    public boolean update(Inventory inventory, Integer id) {
        return inventoryDao.update(new ModelMapper().map(inventory,edu.icet.ecom.entity.Inventory.class),id);
    }

    @Override
    public List<Inventory> getAll() {
        List<edu.icet.ecom.entity.Inventory> all = inventoryDao.getAll();
        ArrayList<Inventory> inventories = new ArrayList<>();
        all.forEach(inventory -> inventories.add(new ModelMapper().map(inventory, Inventory.class)));
        return inventories;
    }

    @Override
    public Inventory getById(Integer id) {
        return new ModelMapper().map(inventoryDao.getById(id), Inventory.class);
    }

    @Override
    public Image getImage(Integer id) {
        byte[] imageData = inventoryDao.getImageData(id);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return new Image(bis);
    }

    @Override
    public List<Integer> getAllIds() {
        List<Inventory> all = getAll();
        ArrayList<Integer> ids = new ArrayList<>();
        all.forEach(inventory -> ids.add(inventory.getId()));
        return ids;
    }
}
