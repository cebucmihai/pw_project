package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.shop.dto.cart.AddToCartDto;
import project.shop.dto.cart.CartDto;
import project.shop.model.User;
import project.shop.service.AuthenticationService;
import project.shop.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {

        authenticationService.authenticate(token);



        User user = authenticationService.getUser(token);


        cartService.addToCart(addToCartDto, user );

        return new ResponseEntity<>("Added to cart", HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);


        User user = authenticationService.getUser(token);


        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{cartItemId}/{token}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                 @PathVariable("token") String token) {

        authenticationService.authenticate(token);


        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>( "Item has been removed", HttpStatus.OK);

    }

}