package project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.shop.model.Cart;
import project.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.product.id = :productId")
    void deleteByProductId(@Param("productId") Integer productId);

    Optional<Cart> findByProductId(Integer id);
}