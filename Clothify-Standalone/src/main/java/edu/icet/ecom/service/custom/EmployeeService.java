package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    boolean save(Employee employee);
    boolean delete(String username);
    boolean update(Employee employee);
    List<Employee> getAll();
    Employee getById(String username);
}
