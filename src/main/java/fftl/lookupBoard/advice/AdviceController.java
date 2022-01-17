package fftl.lookupBoard.advice;

import fftl.lookupBoard.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(Exception.class)
    protected Response Exceiption(Exception e){
        return new Response(false, e.getMessage());
    }
}
