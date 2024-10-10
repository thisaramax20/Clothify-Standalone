package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.SupplierItem;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface SupplierItemService extends SuperService {
    boolean save(SupplierItem supplierItem);
    boolean delete(CompositePK_SupplierItem compositePKSupplierItem);
    boolean update(SupplierItem supplierItem,CompositePK_SupplierItem compositePKSupplierItem);
    List<SupplierItem> getAll();
    SupplierItem getById(CompositePK_SupplierItem compositePKSupplierItem );
}
