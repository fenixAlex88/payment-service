package org.example.payment.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String message;
    private int errorCode;
    private String details;
}
