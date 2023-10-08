package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.shop.dto.ProductDto;
import project.shop.model.Product;
import project.shop.model.User;
import project.shop.model.WishList;
import project.shop.service.AuthenticationService;
import project.shop.service.WishListService;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/add")
    public ResponseEntity<String> addToWishList(@RequestBody Product product,
                                                @RequestParam("token") String token) {
        authenticationService.authenticate(token);


        User user = authenticationService.getUser(token);


        WishList wishList = new WishList(user, product);

        wishListService.createWishlist(wishList);

        return  new ResponseEntity<String>("Succesfully added.", HttpStatus.CREATED);

    }



    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

        authenticationService.authenticate(token);




        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{productId}/token={token}")
    public ResponseEntity<String> removeFromWishList(@PathVariable Long productId,
                                                     @PathVariable("token") String token) {
        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        try {
            wishListService.removeFromWishList(user, productId);
            return new ResponseEntity<>("Successfully removed from wishlist.", HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions (e.g., product not found in wishlist)
            return new ResponseEntity<>("Failed to remove from wishlist.", HttpStatus.BAD_REQUEST);
        }
    }

}