package rajeevbro.code.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rajeevbro.code.springsecurity.dto.EmployeeDto;
import rajeevbro.code.springsecurity.entity.Employee;
import rajeevbro.code.springsecurity.exception.UserNotFoundException;
import rajeevbro.code.springsecurity.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
   @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")

    public String registerEmployee(@RequestBody EmployeeDto employeeDto)
    {

        return employeeService.registerEmployee(employeeDto);
    }


    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

 @GetMapping("/employee/{id}")
    public Employee findEmplyeeById(@PathVariable("id") Long id) throws UserNotFoundException {
        return employeeService.findEmployeeById(id);

    }


    @DeleteMapping("/employee/{id}")
    public String deleteEmplyeeById(@PathVariable("id") Long id) throws Exception {
        return employeeService.deleteEmployeeById(id);

    }


}
