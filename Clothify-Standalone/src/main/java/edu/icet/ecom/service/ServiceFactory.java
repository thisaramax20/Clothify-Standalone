package edu.icet.ecom.service;

import edu.icet.ecom.repository.custom.impl.EmployeeDaoImpl;
import edu.icet.ecom.service.custom.impl.EmployeeServiceImpl;
import edu.icet.ecom.service.custom.impl.InventoryServiceImpl;
import edu.icet.ecom.service.custom.impl.SupplierServiceImpl;
import edu.icet.ecom.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case INVENTORY:return (T) new InventoryServiceImpl();
            case SUPPLIER:return (T) new SupplierServiceImpl();
        }
        return null;
    }
}
