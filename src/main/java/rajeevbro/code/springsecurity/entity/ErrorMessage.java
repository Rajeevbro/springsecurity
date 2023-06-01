package rajeevbro.code.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class ErrorMessage {
    private HttpStatus http;
    private String message;
}
