package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.*;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean save(Orders orders);
    boolean delete(Integer id);
    boolean update(Orders orders,Integer id);
    List<Orders> getAll();
    Orders getById(Integer id);
    Admin getAdmin(String username);
    List<Integer> getAllids();
    Inventory getByIdItem(Integer id);
}
