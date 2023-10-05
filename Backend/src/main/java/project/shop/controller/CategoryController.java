package project.shop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.shop.exceptions.CategoryNotFoundException;
import project.shop.model.Category;
import project.shop.service.CategoryService;
import project.shop.utils.Helper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<>("category already exists", HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>("created the category", HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }
    @PostMapping("/update/{categoryID}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryID") Integer categoryID, @Valid @RequestBody Category category) throws CategoryNotFoundException {
        // Check to see if the category exists.
        if (Helper.notNull(categoryService.readCategory(categoryID))) {
            // If the category exists then update it.
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>("updated the category", HttpStatus.OK);
        }

        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>("category does not exist", HttpStatus.NOT_FOUND);
    }
}
