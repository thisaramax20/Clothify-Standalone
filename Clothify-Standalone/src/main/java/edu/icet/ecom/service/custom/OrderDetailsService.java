package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.*;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean save(Orders orders);
    boolean delete(String id);
    boolean update(Orders orders,Integer id);
    List<Orders> getAll();
    Orders getById(String id);
    Admin getAdmin(String username);
    List<String> getAllids();
    Inventory getByIdItem(String itemCode);
    String getOrderId();
    List<OrderDetails> getOrderDetails(String orderId);
}
