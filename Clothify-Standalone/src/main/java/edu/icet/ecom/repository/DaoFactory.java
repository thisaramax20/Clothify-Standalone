package edu.icet.ecom.repository;

import edu.icet.ecom.repository.custom.impl.EmployeeDaoImpl;
import edu.icet.ecom.repository.custom.impl.InventoryDaoImpl;
import edu.icet.ecom.repository.custom.impl.SupplierDaoImpl;
import edu.icet.ecom.repository.custom.impl.SupplierItemDaoImpl;
import edu.icet.ecom.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case INVENTORY:return (T) new InventoryDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case SUPPLIERITEM:return (T) new SupplierItemDaoImpl();
        }
        return null;
    }
}
