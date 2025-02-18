package org.example.payment.exception;

import org.example.payment.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String RUNTIME_EXCEPTION_DETAILS = "Runtime exception occurred";
    private static final String INTERNAL_SERVER_ERROR_DETAILS = "Internal server error occurred";

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WalletAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleWalletAlreadyExistsException(WalletAlreadyExistsException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, RUNTIME_EXCEPTION_DETAILS);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_DETAILS);
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(CustomException ex, HttpStatus status) {
        return buildErrorResponse(ex, status, ex.getDetails());
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(Exception ex, HttpStatus status, String details) {
        log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .errorCode(status.value())
                .details(details)
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }
}
