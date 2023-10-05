package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shop.exceptions.CategoryNotFoundException;
import project.shop.model.Category;
import project.shop.repository.CategoryRepo;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public void createCategory(Category category){
        categoryRepo.save(category);
    }
    public List<Category> listCategory(){
        return categoryRepo.findAll();
    }
    public Category readCategory(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryID);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(newCategory.getCategoryName());
            category.setDescription(newCategory.getDescription());
            category.setImageUrl(newCategory.getImageUrl());

            categoryRepo.save(category);
        } else throw new CategoryNotFoundException("The Id is wrong!");
    }
}
