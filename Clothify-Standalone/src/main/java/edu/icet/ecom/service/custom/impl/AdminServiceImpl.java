package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.impl.AdminDaoImpl;
import edu.icet.ecom.service.custom.AdminService;
import edu.icet.ecom.util.DaoType;
import edu.icet.ecom.util.EncryptPassword;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDaoImpl adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);

    @Override
    public boolean save(Admin admin) {
        edu.icet.ecom.entity.Admin admin1 = new ModelMapper().map(admin, edu.icet.ecom.entity.Admin.class);
        admin1.setHashedPassword(EncryptPassword.hashingPassword(admin.getPassword()));
        edu.icet.ecom.entity.Admin higestIdAdmin = adminDao.getHigestIdAdmin();
        String username = higestIdAdmin.getUsername();
        String num = username.substring(3);
        int i = Integer.parseInt(num);
        i++;
        admin1.setUsername("AD-"+i);
        return adminDao.save(admin1);
    }

    @Override
    public boolean delete(Integer id) {
        return adminDao.delete(id);
    }

    @Override
    public boolean update(Admin admin, Integer id) {
        edu.icet.ecom.entity.Admin admin1 = new ModelMapper().map(admin, edu.icet.ecom.entity.Admin.class);
        admin1.setHashedPassword(EncryptPassword.hashingPassword(admin.getPassword()));
        return adminDao.update(admin1,id);
    }

    @Override
    public List<Admin> getAll() {
        List<edu.icet.ecom.entity.Admin> all = adminDao.getAll();
        ArrayList<Admin> admins = new ArrayList<>();
        all.forEach(admin -> admins.add(new ModelMapper().map(admin,Admin.class)));
        return admins;
    }

    @Override
    public Admin getById(Integer id) {
        return new ModelMapper().map(adminDao.getById(id),Admin.class);
    }
}
