package com.capgeticket.user.errors;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestExceptionHandler(RuntimeException ex, WebRequest request) throws IOException {
        CustomError error = new CustomError(String.valueOf(new Date()), HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(), Collections.singletonList(ex.getMessage()));
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationExceptionHandler(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Personaliza el mensaje de error de MethodArgumentNotValidException
     *
     * @return un ResponseEntity con el mensaje personalizado
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomError customError = new CustomError();

        customError.setTimestamp(String.valueOf((new Date())));
        customError.setStatus(status.value());
        customError.setError(status.name());

        List<String> messages = new ArrayList<String>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            messages.add(error.getField() + ": " + error.getDefaultMessage());
        }
        customError.setMessage(messages);

        return new ResponseEntity<>(customError, headers, status);
    }

    /**
     * Personaliza el mensaje de error de HttpRequestMethodNotSupportedException
     *
     * @return un ResponseEntity con el mensaje personalizado
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        exception.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        body.put("status", status.value());
        body.put("error", exception.getLocalizedMessage());
        body.put("message", builder.toString());

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
