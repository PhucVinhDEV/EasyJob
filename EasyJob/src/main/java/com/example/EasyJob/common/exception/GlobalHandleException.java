package com.example.EasyJob.common.exception;

import com.example.EasyJob.common.reponsese.ApiReponsese;
import com.example.EasyJob.common.util.ResponseUtil;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalHandleException {
    private void logError(Exception e) {
        log.error("{} occurred.", e.getMessage().toLowerCase(Locale.ROOT));
//        log.error(e.getMessage(), e);
    }

        private final Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap = new HashMap<>() {{
            put(ValidationException.class, HttpStatus.BAD_REQUEST);
            put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
            put(InvalidFormatException.class, HttpStatus.BAD_REQUEST);
            put(ConstraintViolationException.class, HttpStatus.BAD_REQUEST);
            put(AppException.class, HttpStatus.BAD_REQUEST);
            put(AppAuthorizationException.class, HttpStatus.FORBIDDEN);
        }};

    private ResponseEntity<ApiReponsese<Void>> buildErrorResponse(Exception e, HttpStatus status) {
        logError(e);
        return ResponseUtil.error(e, status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            ValidationException.class,
            MethodArgumentNotValidException.class,
            InvalidFormatException.class,
            ConstraintViolationException.class,
            AppException.class,
            AppAuthorizationException.class
    })
    public ResponseEntity<ApiReponsese<Void>> handleKnownExceptions(Exception exception) {
        HttpStatus status = exceptionStatusMap.get(exception.getClass());
        return buildErrorResponse(exception, status);
    }
    /**
     * Catch all other exceptions not explicitly handled
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiReponsese<Void>> handleGenericException(Exception exception) {
        return buildErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
