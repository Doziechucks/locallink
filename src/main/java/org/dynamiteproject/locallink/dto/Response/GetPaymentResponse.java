package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dynamiteproject.locallink.dto.Request.PaymentRequest;


import java.util.List;

@Data @AllArgsConstructor
public class GetPaymentResponse {
    List<PaymentRequest> payments ;
}
