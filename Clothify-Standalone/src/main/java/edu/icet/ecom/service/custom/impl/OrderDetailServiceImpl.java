package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Admin;
import edu.icet.ecom.dto.Inventory;
import edu.icet.ecom.dto.OrderDetails;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.SuperDao;
import edu.icet.ecom.repository.custom.impl.AdminDaoImpl;
import edu.icet.ecom.repository.custom.impl.InventoryDaoImpl;
import edu.icet.ecom.service.ServiceFactory;
import edu.icet.ecom.service.custom.OrderDetailsService;
import edu.icet.ecom.util.DaoType;
import edu.icet.ecom.util.ServiceType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailsService {
    InventoryServiceImpl serviceType = ServiceFactory.getInstance().getServiceType(ServiceType.INVENTORY);

    @Override
    public boolean save(OrderDetails orderDetails) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(OrderDetails orderDetails, Integer id) {
        return false;
    }

    @Override
    public List<OrderDetails> getAll() {
        return List.of();
    }

    @Override
    public OrderDetails getById(Integer id) {
        return null;
    }

    @Override
    public Admin getAdmin(String username) {
        AdminDaoImpl adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
        return new ModelMapper().map(adminDao.getByUsername(username),Admin.class);
    }

    @Override
    public List<Integer> getAllids() {
        return serviceType.getAllIds();
    }

    @Override
    public Inventory getByIdItem(Integer id) {
        return serviceType.getById(id);
    }
}
