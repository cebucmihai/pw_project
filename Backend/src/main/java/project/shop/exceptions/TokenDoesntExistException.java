package project.shop.exceptions;

public class TokenDoesntExistException extends RuntimeException{
    public TokenDoesntExistException(String message){
        super(message);
    }
}
