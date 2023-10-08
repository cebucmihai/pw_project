package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.exceptions.AuthenticationFailException;
import project.shop.model.AuthenticationToken;
import project.shop.model.User;
import project.shop.repository.TokenRepo;

import java.util.Objects;

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
    public User getUser(String token) {
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)) {
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if(Objects.isNull(token)) {
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
    }
}