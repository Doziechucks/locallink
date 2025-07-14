package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.*;
import org.dynamiteproject.locallink.dto.Response.*;


public interface UserServices {
    LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest);
    GetLocalRecordResponse getLocalRecords(GetLocalRecordRequest getlocalRecordRequest);
    PaymentResponse createPayment(PaymentRequest paymentRequest);
    DisputeResponse createDispute(DisputeRequest disputeRequest);
    GetDisputeResponse getUnsettledDisputes();
    String settleDispute(SettleDisputeRequest request);
    String verifyPayment(VerifyPaymentRequest request);
    GetOutStandingResponse getOutStandings(GetOutStandingRequest request);
}

