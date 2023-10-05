package project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.shop.model.AuthenticationToken;
import project.shop.model.User;

public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
}
