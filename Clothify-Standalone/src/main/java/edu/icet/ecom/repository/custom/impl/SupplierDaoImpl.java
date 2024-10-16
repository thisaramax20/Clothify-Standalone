package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.entity.Inventory;
import edu.icet.ecom.entity.Supplier;
import edu.icet.ecom.repository.custom.SupplierDao;
import edu.icet.ecom.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(Supplier entity) {
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
        Supplier supplier = getById(id);
        session.remove(supplier);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Supplier entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Supplier supplier = getById(entity.getSupplierCode());
        supplier.setNic(entity.getNic());
        supplier.setName(entity.getName());
        supplier.setCompany(entity.getCompany());
        session.merge(supplier);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Supplier> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Supplier> resultList = session.createQuery("SELECT a FROM Supplier a", Supplier.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Supplier getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Supplier supplier = session.createQuery("SELECT a FROM Supplier a WHERE a.supplierCode=:supplierCode", Supplier.class)
                .setParameter("supplierCode", id)
                .getSingleResultOrNull();
        session.close();
        return supplier;
    }

    @Override
    public Supplier getHigestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            return session.createQuery("SELECT a FROM Supplier a ORDER BY a.id DESC", Supplier.class)
                    .setMaxResults(1)
                    .getSingleResultOrNull();
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

}
