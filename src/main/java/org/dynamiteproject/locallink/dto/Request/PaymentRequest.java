package org.dynamiteproject.locallink.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data @AllArgsConstructor
public class PaymentRequest {
    private String title;
    private String localId;
    private BigDecimal amount;
    private String description;
    private String transactionId;
    private String url;
    private Boolean isVerified;
}
