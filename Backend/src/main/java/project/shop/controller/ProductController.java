package project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.shop.dto.ProductDto;
import project.shop.exceptions.ProductNotFound;
import project.shop.model.Category;
import project.shop.repository.CategoryRepo;
import project.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepo categoryRepo;
    @PostMapping("/add")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto){
        final Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<String>("Category doesn't exist !",HttpStatus.BAD_REQUEST);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<String>("Product has been added", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws ProductNotFound {
        final Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<String>("Category doesn't exist !",HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<String>("Product has been updated.", HttpStatus.OK);
    }
}
