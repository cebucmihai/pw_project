package project.shop.exceptions;

public class CategoryNotFoundException extends IllegalArgumentException{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
