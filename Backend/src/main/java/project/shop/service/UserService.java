package project.shop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.dto.ResponseDto;
import project.shop.dto.user.SignInDto;
import project.shop.dto.user.SignInResponseDto;
import project.shop.dto.user.SignupDto;
import project.shop.exceptions.AuthenticationFailException;
import project.shop.exceptions.TokenDoesntExistException;
import project.shop.exceptions.UserAlreadyExistException;
import project.shop.model.AuthenticationToken;
import project.shop.model.User;
import project.shop.repository.UserRepo;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Objects;
import javax.xml.bind.DatatypeConverter;
@Service
public class UserService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new UserAlreadyExistException("user already exist!");
        }


        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword);

        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created succesfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {


        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }


        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (Objects.isNull(token)) {
            throw new TokenDoesntExistException("token is not present");
        }

        return new SignInResponseDto("sucess", token.getToken());

    }
}