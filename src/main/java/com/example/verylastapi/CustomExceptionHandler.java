package com.example.verylastapi;

import com.beust.jcommander.internal.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(
           MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
   {
       Map<String, Object> responseBody = new LinkedHashMap<>();
       responseBody.put("timestamp", new Date());
       responseBody.put("status", status.value());
       List<ErrorVal> errors = ex.getBindingResult().getFieldErrors()
               .stream()
               .map(x -> new ErrorVal(x.getField(),x.getDefaultMessage()))
               .collect(Collectors.toList());
       responseBody.put("errors", errors);
       return new ResponseEntity<>(responseBody, headers, status);
   }
}