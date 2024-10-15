package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.entity.Inventory;
import edu.icet.ecom.repository.CrudRepository;

public interface InventoryDao extends CrudRepository<Inventory> {
    byte[] getImageData(String itemCode);
    boolean deleteByCompositePK(CompositePK_SupplierItem compositePKSupplierItem);
    Inventory searchByCompositePK(CompositePK_SupplierItem compositePKSupplierItem);
}
