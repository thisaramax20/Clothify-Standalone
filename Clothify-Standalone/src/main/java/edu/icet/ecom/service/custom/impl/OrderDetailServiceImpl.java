package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.dto.Orders;
import edu.icet.ecom.entity.OrderDetails;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.custom.impl.AdminDaoImpl;
import edu.icet.ecom.repository.custom.impl.OrderDetailsDaoImpl;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.OrderDetailsService;
import edu.icet.ecom.util.DaoType;
import edu.icet.ecom.util.EmailSending;
import edu.icet.ecom.util.JasperReports;
import edu.icet.ecom.util.ServiceType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailsService {
    InventoryServiceImpl serviceType = ServiceFactory.getInstance().getServiceType(ServiceType.INVENTORY);
    OrderDetailsDaoImpl orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);

    @Override
    public boolean save(Orders orders) {
        edu.icet.ecom.entity.Orders orders1 = new ModelMapper().map(orders, edu.icet.ecom.entity.Orders.class);
        List<OrderDetails> orderDetails = new ArrayList<>();
        orders1.getOrderDetails().forEach(orderDetails1 -> {
            orderDetails.add(new ModelMapper().map(orderDetails1,OrderDetails.class));
        });
        orders1.setOrderDetails(orderDetails);
        if (orderDetailsDao.save(orders1)){
            String invoiceFilePath = JasperReports.getInstance().createInvoice(orders);
            if (invoiceFilePath!=null) EmailSending.sendInvoice(orders.getCustomerEmail(),invoiceFilePath);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        return orderDetailsDao.delete(id);
    }

    @Override
    public boolean update(Orders orders, Integer id) {
        return false;
    }

    @Override
    public List<Orders> getAll() {
        return List.of();
    }

    @Override
    public Orders getById(String id) {
        return new ModelMapper().map(orderDetailsDao.getById(id),Orders.class);
    }

    @Override
    public Admin getAdmin(String username) {
        AdminDaoImpl adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
        return new ModelMapper().map(adminDao.getById(username),Admin.class);
    }

    @Override
    public List<String> getAllids() {
        return serviceType.getAllIds();
    }

    @Override
    public Inventory getByIdItem(String itemCode) {
        return serviceType.getById(itemCode);
    }

    @Override
    public String getOrderId() {
        edu.icet.ecom.entity.Orders lastOrder = orderDetailsDao.getHigestId();
        if (lastOrder==null){
            return "OR-1";
        }
        String orderId = lastOrder.getOrderId();
        int currentOrderId = Integer.parseInt(orderId.substring(3));
        return "OR-"+ ++currentOrderId;
    }

    @Override
    public List<edu.icet.ecom.dto.OrderDetails> getOrderDetails(String orderId) {
        List<OrderDetails> byIdOrderDetails = orderDetailsDao.getByIdOrderDetails(orderId);
        ArrayList<edu.icet.ecom.dto.OrderDetails> orderDetails = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        if (!byIdOrderDetails.isEmpty()) {
            byIdOrderDetails.forEach(orderDetails1 -> orderDetails.add(mapper.map(orderDetails1,edu.icet.ecom.dto.OrderDetails.class)));
            return orderDetails;
        }
        return Collections.emptyList();
    }
}
