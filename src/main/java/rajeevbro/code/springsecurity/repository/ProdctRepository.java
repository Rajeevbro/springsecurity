package rajeevbro.code.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rajeevbro.code.springsecurity.entity.Products;

@Repository
public interface ProdctRepository extends JpaRepository<Products,Long> {
}
