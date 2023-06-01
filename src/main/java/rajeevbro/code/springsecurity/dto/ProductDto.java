package rajeevbro.code.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class ProductDto {
    private String productName;
    private Integer quantity;
    private String originCompany;

}
