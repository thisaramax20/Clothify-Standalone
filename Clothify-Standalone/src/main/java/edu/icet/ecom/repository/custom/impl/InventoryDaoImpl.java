package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Admin;
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
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.get(Inventory.class, id);
        session.remove(inventory);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Inventory entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = getById(entity.getItemCode());
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
    public Inventory getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.createQuery("SELECT a FROM Inventory a WHERE a.itemCode=:itemCode", Inventory.class)
                .setParameter("itemCode", id)
                .getSingleResult();
        session.close();
        return inventory;
    }

    @Override
    public Inventory getHigestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            return session.createQuery("SELECT a FROM Inventory a ORDER BY a.id DESC", Inventory.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }

    }

    @Override
    public byte[] getImageData(String itemCode) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = getById(itemCode);
        byte[] imageData = inventory.getImageData();
        session.close();
        return imageData;
    }
}
