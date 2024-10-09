package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.entity.Inventory;
import edu.icet.ecom.repository.custom.InventoryDao;
import edu.icet.ecom.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    @Override
    public boolean save(Inventory entity) {
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
        Inventory inventory = session.get(Inventory.class, id);
        session.remove(inventory);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Inventory entity, Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.get(Inventory.class, id);
        inventory.setCategory(entity.getCategory());
        inventory.setName(entity.getName());
        inventory.setQuantity(entity.getQuantity());
        inventory.setSize(entity.getSize());
        inventory.setPrice(entity.getPrice());
        session.merge(inventory);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Inventory> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Inventory> resultList = session.createQuery("SELECT a FROM Inventory a", Inventory.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Inventory getById(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.find(Inventory.class, id);
        session.close();
        return inventory;
    }

    @Override
    public byte[] getImageData(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.find(Inventory.class, id);
        byte[] imageData = inventory.getImageData();
        session.close();
        return imageData;
    }
}
