package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface AdminService extends SuperService {
    boolean save(Admin admin);
    boolean delete(Integer id);
    boolean update(Admin admin,Integer id);
    List<Admin> getAll();
    Admin getById(Integer id);
}
