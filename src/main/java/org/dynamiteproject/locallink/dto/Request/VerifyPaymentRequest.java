package org.dynamiteproject.locallink.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class VerifyPaymentRequest {
    private String paymentId;
}
