package org.example.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDTO {
    private String message;
    private int errorCode;
    private String details;
}
