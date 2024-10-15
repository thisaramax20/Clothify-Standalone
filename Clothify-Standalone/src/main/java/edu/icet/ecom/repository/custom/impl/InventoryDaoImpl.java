package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Admin;
import edu.icet.ecom.entity.CompositePK_SupplierItem;
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
        Inventory inventory = getById(id);
        session.remove(inventory);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Inventory entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.get(Inventory.class,entity.getId());
        inventory.setCategory(entity.getCategory());
        inventory.setName(entity.getName());
        inventory.setQuantity(entity.getQuantity());
        inventory.setSize(entity.getSize());
        inventory.setPrice(entity.getPrice());
        inventory.setImageData(entity.getImageData());
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

        String supplierId = "";
        for (Inventory inventory:getAll()){
            if (inventory.getId().getItemCode().equals(id)) supplierId = inventory.getId().getSupplierId();
        }
        Inventory inventory = searchByCompositePK(new CompositePK_SupplierItem(supplierId, id));
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
                    .getSingleResultOrNull();
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

    @Override
    public boolean deleteByCompositePK(CompositePK_SupplierItem compositePKSupplierItem) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.get(Inventory.class,compositePKSupplierItem);
        session.remove(inventory);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Inventory searchByCompositePK(CompositePK_SupplierItem compositePKSupplierItem) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Inventory inventory = session.get(Inventory.class,compositePKSupplierItem);
        if (inventory!=null){
            return inventory;
        }else return null;
    }
}
