package edu.icet.ecom.repository.custom.impl;

import edu.icet.ecom.entity.Admin;
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
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Admin admin = getById(id);
        session.remove(admin);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Admin entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Admin admin = getById(entity.getUsername());
        admin.setNic(entity.getNic());
        admin.setName(entity.getName());
        admin.setAddress(entity.getAddress());
        admin.setDob(entity.getDob());
        System.out.println(entity.getEmail());
        admin.setEmail(entity.getEmail());
        if (entity.getHashedPassword()!=null) admin.setHashedPassword(entity.getHashedPassword());
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
    public Admin getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Admin admin = session.createQuery("SELECT a FROM Admin a WHERE a.username=:username", Admin.class)
                .setParameter("username", id)
                .getSingleResultOrNull();
        session.close();
        return admin;
    }

    @Override
    public Admin getHigestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Admin admin;
        try {
            admin = session.createQuery("SELECT a FROM Admin a ORDER BY a.id DESC", Admin.class)
                    .setMaxResults(1)
                    .getSingleResultOrNull();
            return admin;
        } catch (Exception e) {
            return null;
        }
    }
}
