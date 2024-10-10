package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.Admin;
import edu.icet.ecom.repository.CrudRepository;

public interface AdminDao extends CrudRepository<Admin> {
    Admin getHigestIdAdmin();
    Admin getByUsername(String username);
}
