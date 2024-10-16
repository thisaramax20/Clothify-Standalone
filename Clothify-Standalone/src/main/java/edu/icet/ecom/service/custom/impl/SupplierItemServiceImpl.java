package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.SupplierItem;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.entity.Inventory;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.SuperDao;
import edu.icet.ecom.repository.custom.impl.InventoryDaoImpl;
import edu.icet.ecom.repository.custom.impl.SupplierItemDaoImpl;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.SuperService;
import edu.icet.ecom.service.custom.SupplierItemService;
import edu.icet.ecom.util.DaoType;
import edu.icet.ecom.util.ServiceType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierItemServiceImpl implements SupplierItemService {
    SupplierItemDaoImpl supplierItemDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIERITEM);
    InventoryServiceImpl inventoryService = ServiceFactory.getInstance().getServiceType(ServiceType.INVENTORY);

    @Override
    public boolean save(SupplierItem supplierItem) {
        edu.icet.ecom.entity.SupplierItem supplierItem1 = new ModelMapper().map(supplierItem, edu.icet.ecom.entity.SupplierItem.class);
        InventoryDaoImpl inventoryDao = DaoFactory.getInstance().getDaoType(DaoType.INVENTORY);
        Inventory higestId = inventoryDao.getHigestId();
        CompositePK_SupplierItem compositePKSupplierItem;
        if (higestId!=null){
            int currentId = Integer.parseInt(higestId.getSupplierItem().getCompositePKSupplierItem().getItemCode().substring(3));
            compositePKSupplierItem = new CompositePK_SupplierItem(supplierItem.getSupplierId(),"IN-"+ ++currentId);
        }else {
            compositePKSupplierItem = new CompositePK_SupplierItem(supplierItem.getSupplierId(),"IN-1");
        }
        supplierItem1.setCompositePKSupplierItem(compositePKSupplierItem);
        boolean executed = supplierItemDao.save(supplierItem1);
        if (executed){
            return inventoryService.save(new edu.icet.ecom.dto.Inventory(compositePKSupplierItem,
                    supplierItem.getName(),
                    null, null, supplierItem.getCategory(),
                    null, null,
                    supplierItem1,null));
        }else return false;
    }

    @Override
    public boolean delete(CompositePK_SupplierItem compositePKSupplierItem) {
        boolean executed = supplierItemDao.deleteCompositePK(compositePKSupplierItem);
        if (executed){
            return inventoryService.delete(compositePKSupplierItem.getItemCode());
        }else return false;
    }

    @Override
    public boolean update(SupplierItem supplierItem, CompositePK_SupplierItem compositePKSupplierItem,String itemName,String category) {
        boolean executed = supplierItemDao.updateCompositePK(new ModelMapper().map(supplierItem, edu.icet.ecom.entity.SupplierItem.class), compositePKSupplierItem);
        if (executed){
            return inventoryService.update(new edu.icet.ecom.dto.Inventory(compositePKSupplierItem,
                    itemName,
                    null, null, category,
                    null, null,
                    null,null)
            );
        }else return false;
    }

    @Override
    public List<SupplierItem> getAll() {
        List<edu.icet.ecom.entity.SupplierItem> all = supplierItemDao.getAll();
        ArrayList<SupplierItem> supplierItems = new ArrayList<>();
        all.forEach(supplierItem -> supplierItems.add(new ModelMapper().map(supplierItem,SupplierItem.class)));
        return supplierItems;
    }

    @Override
    public SupplierItem getById(CompositePK_SupplierItem compositePKSupplierItem) {
        edu.icet.ecom.entity.SupplierItem supplierItem = supplierItemDao.getByCompositePK(compositePKSupplierItem);
        return supplierItem!=null ? new ModelMapper().map(supplierItem,SupplierItem.class) :null;
    }
}
