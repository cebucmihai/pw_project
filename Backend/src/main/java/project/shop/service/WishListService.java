package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.dto.ProductDto;
import project.shop.model.User;
import project.shop.model.WishList;
import project.shop.repository.WishListRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WishListService {

    @Autowired
    WishListRepo wishListRepository;

    @Autowired
    ProductService productService;

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList: wishLists) {
            productDtos.add(productService.getDtoFromProduct(wishList.getProduct()));
        }

        return productDtos;
    }
    public void removeFromWishList(User user, Long productId) {
        WishList wishList = wishListRepository.findByUserAndProduct_Id(user, productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found in wishlist"));

        wishListRepository.delete(wishList);
    }
}