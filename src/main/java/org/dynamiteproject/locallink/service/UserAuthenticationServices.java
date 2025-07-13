package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.*;
import org.dynamiteproject.locallink.dto.Response.*;
import org.springframework.stereotype.Service;

@Service
public interface UserAuthenticationServices {
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest);
    public LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest);
    public GetLocalRecordResponse getLocalRecords(GetLocalRecordRequest getlocalRecordRequest);
    public PaymentResponse createPayment(PaymentRequest paymentRequest);
    public DisputeResponse createDispute(DisputeRequest disputeRequest);
}

