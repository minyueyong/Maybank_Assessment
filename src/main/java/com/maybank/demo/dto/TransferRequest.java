package com.maybank.demo.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferRequest {

    @NotNull(message = "Source Account Id is required")
    private Long sourceAccountId;

    @NotNull(message = "Destination Account Id is required")
    private Long destinationAccountId;

    @NotNull(message = "Transferred Amount  is required")
    @Positive(message = "Transfer amount must be greater than zero")
    private Double amount;
}
