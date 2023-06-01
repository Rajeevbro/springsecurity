package rajeevbro.code.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rajeevbro.code.springsecurity.dto.ProductDto;
import rajeevbro.code.springsecurity.entity.Products;
import rajeevbro.code.springsecurity.repository.ProdctRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProdctRepository prodctRepository;
    @Override
    public String registerProduct(ProductDto productDto) {

        Products products = Products.builder()
                .productName(productDto.getProductName())
                .quantity(productDto.getQuantity())
                .originCompany(productDto.getOriginCompany())
                .build();
        prodctRepository.save(products);
        return "Success";
    }

    @Override
    public List<Products> getAllProducts() {
        return prodctRepository.findAll();
    }

    @Override
    public Products findProductsById(Long id) {

        Optional<Products> product = prodctRepository.findById(id);
        if(product.isPresent())
        {
            return product.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with Id is not available");

    }


}
