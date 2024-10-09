package edu.icet.ecom.repository.custom.impl;

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
    public boolean delete(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Supplier supplier = session.get(Supplier.class, id);
        session.remove(supplier);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Supplier entity, Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Supplier supplier = session.get(Supplier.class, id);
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
    public Supplier getById(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Supplier supplier = session.find(Supplier.class, id);
        session.close();
        return supplier;
    }

    @Override
    public byte[] getImageData(Integer id) {
        return null;
    }
}
