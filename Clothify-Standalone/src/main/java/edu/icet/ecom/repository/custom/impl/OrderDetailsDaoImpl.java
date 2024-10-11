package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Inventory;
import edu.icet.ecom.entity.OrderDetails;
import edu.icet.ecom.entity.Orders;
import edu.icet.ecom.repository.custom.OrderDetailsDao;
import edu.icet.ecom.util.HibernateUtil;
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

                Inventory inventory = session.get(Inventory.class, details.getItemCode());
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
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(Orders entity, Integer id) {
        return false;
    }

    @Override
    public List<Orders> getAll() {
        return List.of();
    }

    @Override
    public Orders getById(Integer id) {
        return null;
    }

    @Override
    public byte[] getImageData(Integer id) {
        return new byte[0];
    }
}
