package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Admin;
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
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Employee employee = getById(id);
        session.remove(employee);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Employee entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Employee employee = getById(entity.getUsername());
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
    public Employee getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Employee employee = session.createQuery("SELECT a FROM Employee a WHERE a.username=:username", Employee.class)
                .setParameter("username", id)
                .getSingleResultOrNull();
        session.close();
        return employee;
    }

    @Override
    public Employee getHigestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            return session.createQuery("SELECT a FROM Employee a ORDER BY a.id DESC", Employee.class)
                    .setMaxResults(1)
                    .getSingleResultOrNull();
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }

    }

}
