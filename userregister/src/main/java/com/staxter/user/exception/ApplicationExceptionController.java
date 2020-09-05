package com.staxter.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a controller advice ,responsible for handle exceptions, throws all of applications.we can handle all exceptions with methods in this class.
 */
@RestControllerAdvice
@Slf4j
public class ApplicationExceptionController {

    /**
     * this method responsible for handle {@link UserAlreadyExistException}
     * @return {@link ActionResult} for return expected return JSON
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ActionResult userAlreadyExist(){
        log.error("User Already Exist Exception Occurred: {}",UserAlreadyExistException.class);
        return ActionResult.builder()
                .code("USER_ALREADY_EXISTS")
                .description("A user with the given username already exists")
                .build();
    }

    /**
     * this method responsible for handle {@link CheckUserPropertyNullValueException}
     * @return {@link ActionResult} for return expected return JSON
     *
     * NOTE: This exception was not in task Document, but I decided to check them.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CheckUserPropertyNullValueException.class)
    public ActionResult nullValueCheck(){
        log.error("Null value Exception Occurred: {}", CheckUserPropertyNullValueException.class);
        return ActionResult.builder()
                .code("ALL_FILED_REQUIRED")
                .description("All field should be initiated or existed in JSON object")
                .build();
    }
}
