package com.mercadolivre.socialmeli.handler;

        import com.mercadolivre.socialmeli.exception.*;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductIsPromo.class)
    public ResponseEntity<Object> productIsPromo(){
        return new ResponseEntity<>("Product is promotional", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductIsNotPromo.class)
    public ResponseEntity<Object> productIsNotPromo(){
        return new ResponseEntity<>("Product is not promotional", HttpStatus.BAD_REQUEST);
    }

}
