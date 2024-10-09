package edu.icet.ecom.repository;

import edu.icet.ecom.repository.custom.impl.EmployeeDaoImpl;
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
        }

        return null;
    }
}
