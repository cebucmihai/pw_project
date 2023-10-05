package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.model.AuthenticationToken;
import project.shop.model.User;
import project.shop.repository.TokenRepo;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepo tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
}