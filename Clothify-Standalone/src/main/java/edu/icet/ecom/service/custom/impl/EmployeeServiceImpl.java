package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.repository.DaoFactory;
import edu.icet.ecom.repository.SuperDao;
import edu.icet.ecom.repository.custom.impl.EmployeeDaoImpl;
import edu.icet.ecom.service.custom.EmployeeService;
import edu.icet.ecom.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
    @Override
    public boolean save(Employee employee) {
        return employeeDao.save(new ModelMapper().map(employee, edu.icet.ecom.entity.Employee.class));
    }

    @Override
    public boolean delete(Integer id) {
        return employeeDao.delete(id);
    }

    @Override
    public boolean update(Employee employee, Integer id) {
        return employeeDao.update(new ModelMapper().map(employee, edu.icet.ecom.entity.Employee.class),id);
    }

    @Override
    public List<Employee> getAll() {
        List<edu.icet.ecom.entity.Employee> all = employeeDao.getAll();
        ArrayList<Employee> employees = new ArrayList<>();
        all.forEach(employee -> employees.add(new ModelMapper().map(employee,Employee.class)));
        return employees;
    }

    @Override
    public Employee getById(Integer id) {
        return new ModelMapper().map(employeeDao.getById(id),Employee.class);
    }
}
