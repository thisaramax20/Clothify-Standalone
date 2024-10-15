package edu.icet.ecom.repository.custom;

import edu.icet.ecom.entity.OrderDetails;
import edu.icet.ecom.entity.Orders;
import edu.icet.ecom.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsDao extends CrudRepository<Orders> {
    List<OrderDetails> getByIdOrderDetails(String orderId);
}
