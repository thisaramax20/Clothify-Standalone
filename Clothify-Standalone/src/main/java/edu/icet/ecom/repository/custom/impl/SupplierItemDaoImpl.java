package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.CompositePK_SupplierItem;
import edu.icet.ecom.entity.SupplierItem;
import edu.icet.ecom.repository.custom.SupplierItemDao;
import edu.icet.ecom.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SupplierItemDaoImpl implements SupplierItemDao {
    @Override
    public boolean save(SupplierItem entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(SupplierItem entity, Integer id) {
        return false;
    }

    @Override
    public List<SupplierItem> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<SupplierItem> resultList = session.createQuery("SELECT a FROM SupplierItem a", SupplierItem.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public SupplierItem getById(Integer id) {
        return null;
    }

    @Override
    public byte[] getImageData(Integer id) {
        return null;
    }

    @Override
    public boolean deleteCompositePK(CompositePK_SupplierItem compositePKSupplierItem) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierItem supplierItem = session.get(SupplierItem.class, compositePKSupplierItem);
        session.remove(supplierItem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateCompositePK(SupplierItem supplierItem, CompositePK_SupplierItem compositePKSupplierItem) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierItem supplierItem2 = session.get(SupplierItem.class, compositePKSupplierItem);
        supplierItem2.setCategory(supplierItem.getCategory());
        supplierItem2.setName(supplierItem.getName());
        session.merge(supplierItem2);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public SupplierItem getByCompositePK(CompositePK_SupplierItem compositePKSupplierItem) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierItem supplierItem = session.find(SupplierItem.class, compositePKSupplierItem);
        session.close();
        return supplierItem;
    }
}
