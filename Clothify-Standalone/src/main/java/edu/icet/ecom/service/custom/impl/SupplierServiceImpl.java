package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Supplier;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.SuperDao;
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
        return supplierDao.save(new ModelMapper().map(supplier,edu.icet.ecom.entity.Supplier.class));
    }

    @Override
    public boolean delete(Integer id) {
        return supplierDao.delete(id);
    }

    @Override
    public boolean update(Supplier supplier, Integer id) {
        return supplierDao.update(new ModelMapper().map(supplier,edu.icet.ecom.entity.Supplier.class),id);
    }

    @Override
    public List<Supplier> getAll() {
        List<edu.icet.ecom.entity.Supplier> all = supplierDao.getAll();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        all.forEach(supplier -> suppliers.add(new ModelMapper().map(supplier, Supplier.class)));
        return suppliers;
    }

    @Override
    public Supplier getById(Integer id) {
        return new ModelMapper().map(supplierDao.getById(id), Supplier.class);
    }

    @Override
    public List<Integer> getAllIds() {
        List<Supplier> all = getAll();
        ArrayList<Integer> ids = new ArrayList<>();
        all.forEach(supplier -> ids.add(supplier.getId()));
        return ids;
    }
}
