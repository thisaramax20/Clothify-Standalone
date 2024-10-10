package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.entity.SupplierItem;
import edu.icet.ecom.repository.CrudRepository;

public interface SupplierItemDao extends CrudRepository<SupplierItem> {
    boolean deleteCompositePK(CompositePK_SupplierItem compositePKSupplierItem);
    boolean updateCompositePK(SupplierItem supplierItem,CompositePK_SupplierItem compositePKSupplierItem);
    SupplierItem getByCompositePK(CompositePK_SupplierItem compositePKSupplierItem);
}
