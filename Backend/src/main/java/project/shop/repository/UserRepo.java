package project.shop.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import project.shop.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

   User findByEmail(String email);
}
