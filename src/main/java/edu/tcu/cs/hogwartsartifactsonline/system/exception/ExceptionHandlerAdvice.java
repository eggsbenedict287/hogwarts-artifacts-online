package edu.tcu.cs.hogwartsartifactsonline.system.exception;

import edu.tcu.cs.hogwartsartifactsonline.artifact.ArtifactNotFoundException;
import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import edu.tcu.cs.hogwartsartifactsonline.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// How we handle exceptions in the Spring API, how we send the error to the client
@RestControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(ArtifactNotFoundException.class) // How let Spring know, cause its dumb as rocks
    @ResponseStatus(HttpStatus.NOT_FOUND) // Kind of redundant in ths case
    Result handleArtifactNotFoundException(ArtifactNotFoundException ex){
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }
}
