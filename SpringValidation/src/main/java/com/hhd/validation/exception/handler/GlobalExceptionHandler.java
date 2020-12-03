package com.hhd.validation.exception.handler;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

/**
 * @author hengda.hu
 * @date 2020/12/3 10:40
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
        BindingResult bindingResult = ex.getBindingResult();
        String s = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            s += fieldError.getField() + ":" + fieldError.getDefaultMessage() + ";";
        }
        return s;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        String s = "";
        for (ConstraintViolation constraintViolation : constraintViolations) {
            s += constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage() + ";";
        }
        return s;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return getStackTrace(ex);
    }


}
