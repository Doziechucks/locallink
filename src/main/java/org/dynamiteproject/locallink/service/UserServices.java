package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.*;
import org.dynamiteproject.locallink.dto.Response.*;


public interface UserServices {
    public LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest);
    public GetLocalRecordResponse getLocalRecords(GetLocalRecordRequest getlocalRecordRequest);
    public PaymentResponse createPayment(PaymentRequest paymentRequest);
    public DisputeResponse createDispute(DisputeRequest disputeRequest);
    public GetDisputeResponse getUnsettledDisputes();
    public String settleDispute(SettleDisputeRequest request);
    public String verifyPayment(VerifyPaymentRequest request);
}

