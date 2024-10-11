package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Supplier;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.impl.SupplierDaoImpl;
import edu.icet.ecom.service.custom.SupplierService;
import edu.icet.ecom.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierDaoImpl supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public boolean save(Supplier supplier) {
        edu.icet.ecom.entity.Supplier supplier1 = new ModelMapper().map(supplier, edu.icet.ecom.entity.Supplier.class);
        edu.icet.ecom.entity.Supplier higestId = supplierDao.getHigestId();
        if (higestId==null){
            supplier1.setSupplierCode("SP-1");
        }else{
            int currentId = Integer.parseInt(higestId.getSupplierCode());
            supplier1.setSupplierCode("SP-"+ ++currentId);
        }
        return supplierDao.save(supplier1);
    }

    @Override
    public boolean delete(String supplierCode) {
        return supplierDao.delete(supplierCode);
    }

    @Override
    public boolean update(Supplier supplier) {
        return supplierDao.update(new ModelMapper().map(supplier,edu.icet.ecom.entity.Supplier.class));
    }

    @Override
    public List<Supplier> getAll() {
        List<edu.icet.ecom.entity.Supplier> all = supplierDao.getAll();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        all.forEach(supplier -> suppliers.add(new ModelMapper().map(supplier, Supplier.class)));
        return suppliers;
    }

    @Override
    public Supplier getById(String supplierCode) {
        return new ModelMapper().map(supplierDao.getById(supplierCode), Supplier.class);
    }

    @Override
    public List<Integer> getAllIds() {
        List<Supplier> all = getAll();
        ArrayList<Integer> ids = new ArrayList<>();
        all.forEach(supplier -> ids.add(supplier.getId()));
        return ids;
    }
}
