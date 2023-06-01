package rajeevbro.code.springsecurity.service;

import rajeevbro.code.springsecurity.dto.EmployeeDto;
import rajeevbro.code.springsecurity.entity.Employee;
import rajeevbro.code.springsecurity.exception.UserNotFoundException;

import java.util.List;

public interface EmployeeService {
    public String registerEmployee(EmployeeDto employeeDto);

    public List<Employee> getAllEmployee();

    public Employee findEmployeeById(Long id) throws UserNotFoundException;

    String deleteEmployeeById(Long id);
}
