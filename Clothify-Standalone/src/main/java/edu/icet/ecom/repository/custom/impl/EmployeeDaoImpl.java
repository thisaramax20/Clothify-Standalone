package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.repository.custom.EmployeeDao;
import edu.icet.ecom.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

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

    @Override
    public boolean delete(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Employee employee = session.get(Employee.class, id);
        session.remove(employee);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Employee entity,Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Employee employee = session.get(Employee.class, id);
        employee.setNic(entity.getNic());
        employee.setName(entity.getName());
        employee.setAddress(entity.getAddress());
        employee.setDob(entity.getDob());
        employee.setEmail(entity.getEmail());
        session.merge(employee);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Employee> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Employee> resultList = session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Employee getById(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Employee employee = session.find(Employee.class, id);
        session.close();
        return employee;
    }
}
