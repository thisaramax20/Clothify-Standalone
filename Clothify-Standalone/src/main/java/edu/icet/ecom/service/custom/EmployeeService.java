package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    boolean save(Employee employee);
    boolean delete(Integer id);
    boolean update(Employee employee,Integer id);
    List<Employee> getAll();
    Employee getById(Integer id);
}
