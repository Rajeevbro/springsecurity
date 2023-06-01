package rajeevbro.code.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rajeevbro.code.springsecurity.dto.EmployeeDto;
import rajeevbro.code.springsecurity.entity.Employee;
import rajeevbro.code.springsecurity.exception.UserNotFoundException;
import rajeevbro.code.springsecurity.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public String registerEmployee(EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        Employee employee = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .role(employeeDto.getRole())
                .salary(employeeDto.getSalary())
                .phoneNUmber(employeeDto.getPhoneNumber())
                .build();
        employeeRepository.save(employee);
        return "Sucesss";




    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) throws UserNotFoundException {


           Optional<Employee> employee= employeeRepository.findById(id);
           if(employee.isPresent()){
               return employee.get();
           }
           else{
               throw new UserNotFoundException("User Not found with Id " + id);
           }




    }

    @Override
    public String deleteEmployeeById(Long id) {
         employeeRepository.deleteById(id);

       return "sucess";
    }
}
