package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface AdminService extends SuperService {
    boolean save(Admin admin);
    boolean delete(String username);
    boolean update(Admin admin);
    List<Admin> getAll();
    Admin getById(String username);
}
