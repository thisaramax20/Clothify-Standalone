package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.SupplierItem;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.impl.SupplierItemDaoImpl;
import edu.icet.ecom.service.custom.SupplierItemService;
import edu.icet.ecom.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierItemServiceImpl implements SupplierItemService {
    SupplierItemDaoImpl supplierItemDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIERITEM);

    @Override
    public boolean save(SupplierItem supplierItem) {
        return supplierItemDao.save(new ModelMapper().map(supplierItem, edu.icet.ecom.entity.SupplierItem.class));
    }

    @Override
    public boolean delete(CompositePK_SupplierItem compositePKSupplierItem) {
        return supplierItemDao.deleteCompositePK(compositePKSupplierItem);
    }

    @Override
    public boolean update(SupplierItem supplierItem, CompositePK_SupplierItem compositePKSupplierItem ) {
        return supplierItemDao.updateCompositePK(new ModelMapper().map(supplierItem, edu.icet.ecom.entity.SupplierItem.class),compositePKSupplierItem);
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
        return new ModelMapper().map(supplierItemDao.getByCompositePK(compositePKSupplierItem),SupplierItem.class);
    }
}
