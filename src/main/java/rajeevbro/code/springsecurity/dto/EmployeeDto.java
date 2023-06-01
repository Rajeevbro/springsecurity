package rajeevbro.code.springsecurity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private String email;
    private Long salary;
    private String phoneNumber;
    private String role;
}
