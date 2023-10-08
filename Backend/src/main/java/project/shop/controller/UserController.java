package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.shop.dto.ResponseDto;
import project.shop.dto.user.SignInDto;
import project.shop.dto.user.SignInResponseDto;
import project.shop.dto.user.SignupDto;
import project.shop.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }



    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }


}
