package rajeevbro.code.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rajeevbro.code.springsecurity.dto.ProductDto;
import rajeevbro.code.springsecurity.entity.Products;
import rajeevbro.code.springsecurity.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;



    @PostMapping("/products")
    public String registerProduct(@RequestBody ProductDto productDto){
        return productService.registerProduct(productDto);

    }
    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Products> getAllProducts()
    {
        return productService.getAllProducts();

    }
    @GetMapping("products/{id}")
    public Products findProductsById(@PathVariable Long id)
    {
        return productService.findProductsById(id);

    }
}
