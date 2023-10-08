package project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.shop.model.User;
import project.shop.model.WishList;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

    Optional<WishList> findByUserAndProduct_Id(User user, Long productId);
    @Modifying
    @Query("DELETE FROM WishList w WHERE w.product.id = :productId")
    void deleteByProductId(@Param("productId") Integer productId);

    Optional<WishList> findByProductId(Integer id);
}
