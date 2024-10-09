package edu.icet.ecom.service.custom;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.SuperService;

public interface EmployeeService extends SuperService {
    boolean save(Employee employee);
}
