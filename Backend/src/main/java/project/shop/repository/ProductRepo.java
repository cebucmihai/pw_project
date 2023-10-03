package project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import project.shop.model.Product;
@Service
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
