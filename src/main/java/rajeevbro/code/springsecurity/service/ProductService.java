package rajeevbro.code.springsecurity.service;

import rajeevbro.code.springsecurity.dto.ProductDto;
import rajeevbro.code.springsecurity.entity.Products;

import java.util.List;

public interface ProductService {
    public String registerProduct(ProductDto productDto);

    public List<Products> getAllProducts();

    public Products findProductsById(Long id);
}
