package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.dto.ProductDto;
import project.shop.exceptions.ProductNotFound;
import project.shop.model.Category;
import project.shop.model.Product;
import project.shop.repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepository;
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

    public void updateProduct(ProductDto productDto, Integer productId) throws ProductNotFound {
        Optional<Product> optProduct = productRepository.findById(productId);
        if(!optProduct.isPresent()){
            throw new ProductNotFound("The product with this id doesn't exist!");
        }
        Product product = optProduct.get();
        //productRepository.delete(product);
        product = getProductFromDto(productDto , product.getCategory());
        product.setId(productId);
        productRepository.save(product);
    }
}
