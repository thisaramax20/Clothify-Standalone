package edu.icet.ecom.service.custom.impl;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.repository.DaoFactory;
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
        edu.icet.ecom.entity.Employee employee1 = new ModelMapper().map(employee, edu.icet.ecom.entity.Employee.class);
        edu.icet.ecom.entity.Employee employeeDaoById = employeeDao.getHigestId();
        if (employeeDaoById==null){
            employee1.setUsername("EM-1");
        }else{
            int currentId = Integer.parseInt(employeeDaoById.getUsername().substring(3));
            employee1.setUsername("EM-"+ ++currentId);
        }
        return employeeDao.save(employee1);
    }

    @Override
    public boolean delete(String username) {
        return employeeDao.delete(username);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(new ModelMapper().map(employee, edu.icet.ecom.entity.Employee.class));
    }

    @Override
    public List<Employee> getAll() {
        List<edu.icet.ecom.entity.Employee> all = employeeDao.getAll();
        ArrayList<Employee> employees = new ArrayList<>();
        all.forEach(employee -> employees.add(new ModelMapper().map(employee,Employee.class)));
        return employees;
    }

    @Override
    public Employee getById(String username) {
        return new ModelMapper().map(employeeDao.getById(username),Employee.class);
    }
}
