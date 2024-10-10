package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.dto.OrderDetails;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean save(OrderDetails orderDetails);
    boolean delete(Integer id);
    boolean update(OrderDetails orderDetails ,Integer id);
    List<OrderDetails> getAll();
    OrderDetails getById(Integer id);
    Admin getAdmin(String username);
    List<Integer> getAllids();
    Inventory getByIdItem(Integer id);
}
