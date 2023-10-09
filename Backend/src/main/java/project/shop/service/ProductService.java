package project.shop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.dto.ProductDto;
import project.shop.exceptions.ProductNotFoundException;
import project.shop.model.Cart;
import project.shop.model.Category;
import project.shop.model.Product;
import project.shop.model.WishList;
import project.shop.repository.CartRepo;
import project.shop.repository.ProductRepo;
import project.shop.repository.WishListRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepository;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    WishListRepo wishListRepo;

    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }
    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos= new ArrayList<>();
        for(Product product: allProducts){
            productDtos.add(getDtoFromProduct(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws ProductNotFoundException {
        Optional<Product> optProduct = productRepository.findById(productId);
        if(!optProduct.isPresent()){
            throw new ProductNotFoundException("The product with this id doesn't exist!");
        }
        Product product = optProduct.get();
        product = getProductFromDto(productDto , product.getCategory());
        product.setId(productId);
        productRepository.save(product);
    }
    @Transactional
    public void deleteProduct(Integer productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            cartRepo.deleteByProductId(product.getId());

            // Check if the product is in the wishlist
            Optional<WishList> wishListItem = wishListRepo.findByProductId(product.getId());
            if (wishListItem.isPresent()) {
                wishListRepo.deleteByProductId(product.getId());
            }
            productRepository.delete(product);
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
    }
    public Product getProductById(Integer productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product id is invalid " + productId);
        return optionalProduct.get();
    }

}
