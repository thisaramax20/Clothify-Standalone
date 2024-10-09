package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.repository.custom.EmployeeDao;
import edu.icet.ecom.util.HibernateUtil;
import org.hibernate.Session;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(Employee entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
