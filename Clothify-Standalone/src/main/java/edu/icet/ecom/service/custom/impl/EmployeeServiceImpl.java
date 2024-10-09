package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.SuperDao;
import edu.icet.ecom.repository.custom.impl.EmployeeDaoImpl;
import edu.icet.ecom.service.custom.EmployeeService;
import edu.icet.ecom.util.DaoType;
import org.modelmapper.ModelMapper;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public boolean save(Employee employee) {
        EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.save(new ModelMapper().map(employee, edu.icet.ecom.entity.Employee.class));
    }
}
