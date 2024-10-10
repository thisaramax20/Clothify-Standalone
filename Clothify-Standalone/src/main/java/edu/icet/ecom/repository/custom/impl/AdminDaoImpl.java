package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Admin;
import edu.icet.ecom.entity.Employee;
import edu.icet.ecom.repository.custom.AdminDao;
import edu.icet.ecom.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(Admin entity) {
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
        Admin admin = session.get(Admin.class, id);
        session.remove(admin);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Admin entity, Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Admin admin = session.get(Admin.class, id);
        admin.setNic(entity.getNic());
        admin.setName(entity.getName());
        admin.setAddress(entity.getAddress());
        admin.setDob(entity.getDob());
        admin.setEmail(entity.getEmail());
        admin.setHashedPassword(entity.getHashedPassword());
        session.merge(admin);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Admin> getAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Admin> resultList = session.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Admin getById(Integer id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Admin admin = session.find(Admin.class, id);
        session.close();
        return admin;
    }

    @Override
    public byte[] getImageData(Integer id) {
        return new byte[0];
    }

    @Override
    public Admin getHigestIdAdmin() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        return session.createQuery("SELECT a FROM Admin a ORDER BY a.id DESC", Admin.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public Admin getByUsername(String username) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        return session.createQuery("SELECT a FROM Admin a WHERE a.username=:username",Admin.class)
                .setParameter("username",username)
                .getSingleResult();
    }
}
