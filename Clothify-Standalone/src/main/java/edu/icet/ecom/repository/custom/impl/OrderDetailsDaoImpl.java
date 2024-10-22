package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Inventory;
import edu.icet.ecom.entity.OrderDetails;
import edu.icet.ecom.entity.Orders;
import edu.icet.ecom.entity.Supplier;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.SuperDao;
import edu.icet.ecom.repository.custom.OrderDetailsDao;
import edu.icet.ecom.util.DaoType;
import edu.icet.ecom.util.HibernateUtil;
import java.util.Collections;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(Orders entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<edu.icet.ecom.entity.OrderDetails> orderDetails = entity.getOrderDetails();
            for (OrderDetails details : orderDetails){
                session.persist(details);
                InventoryDaoImpl inventoryDao = DaoFactory.getInstance().getDaoType(DaoType.INVENTORY);
                Inventory inventory = inventoryDao.getById(details.getItemCode());
                if (inventory!=null){
                    inventory.setQuantity(inventory.getQuantity()-details.getQuantity());
                    session.merge(inventory);
                }else {
                    return false;
                }
            }
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<edu.icet.ecom.entity.OrderDetails> orderDetails = getByIdOrderDetails(id);
            for (OrderDetails details : orderDetails){
                InventoryDaoImpl inventoryDao = DaoFactory.getInstance().getDaoType(DaoType.INVENTORY);
                Inventory inventory = inventoryDao.getById(details.getItemCode());
                if (inventory!=null){
                    inventory.setQuantity(inventory.getQuantity()+details.getQuantity());
                    session.update(inventory);
                }else {
                    return false;
                }
                session.remove(details);
            }
            Orders byId = getById(id);
            session.remove(byId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean update(Orders entity) {
        return false;
    }

    @Override
    public List<Orders> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Orders> resultList = session.createQuery("SELECT a FROM Orders a", Orders.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Orders getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Orders orders = session.createQuery("SELECT a FROM Orders a WHERE a.orderId=:orderId", Orders.class)
                .setParameter("orderId", id)
                .getSingleResult();
        session.close();
        return orders;
    }

    @Override
    public Orders getHigestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            return session.createQuery("SELECT a FROM Orders a ORDER BY a.id DESC", Orders.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<OrderDetails> getByIdOrderDetails(String orderId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<OrderDetails> orderDetails = session.createQuery("SELECT a FROM OrderDetails a WHERE a.orderId=:orderId", OrderDetails.class)
                .setParameter("orderId", orderId)
                        .getResultList();
        session.close();
        if (orderDetails!=null) return orderDetails;
        return Collections.emptyList();
    }
}
