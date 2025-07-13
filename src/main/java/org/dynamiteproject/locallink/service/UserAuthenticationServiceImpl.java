package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.data.model.Dispute;
import org.dynamiteproject.locallink.data.model.Payment;
import org.dynamiteproject.locallink.data.model.Record;
import org.dynamiteproject.locallink.data.model.Local;
import org.dynamiteproject.locallink.data.repository.DisputeRepo;
import org.dynamiteproject.locallink.data.repository.LocalRepo;
import org.dynamiteproject.locallink.data.repository.PaymentRepo;
import org.dynamiteproject.locallink.data.repository.RecordRepo;
import org.dynamiteproject.locallink.dto.Request.*;
import org.dynamiteproject.locallink.dto.Response.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class UserAuthenticationServiceImpl implements UserAuthenticationServices{
    private final LocalRepo localRepo;
    private final RecordRepo recordRepo;
    private final ModelMapper modelMapper;
    private final PaymentRepo paymentRepo;
    private final DisputeRepo disputeRepo;

    public UserAuthenticationServiceImpl(LocalRepo localRepo, RecordRepo recordRepo, ModelMapper modelMapper, PaymentRepo paymentRepo, DisputeRepo disputeRepo) {
        this.localRepo = localRepo;
        this.recordRepo = recordRepo;
        this.modelMapper = modelMapper;
        this.paymentRepo = paymentRepo;
        this.disputeRepo = disputeRepo;
    }

    @Override
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest) {
        Local local = modelMapper.map(localRegistrationRequest, Local.class);
        Local newLocal = localRepo.save(local);
        return modelMapper.map(newLocal, LocalRegistrationResponse.class);
    }

    @Override
    public LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest) {
        Record record = modelMapper.map(localRecordRequest, Record.class);
        Record newRecord = recordRepo.saveRecord(record);
        return modelMapper.map(newRecord, LocalRecordResponse.class);
    }

    @Override
    public GetLocalRecordResponse getLocalRecords(GetLocalRecordRequest getlocalRecordRequest) {
        List<LocalRecordResponse> newRecords = recordRepo.findRecordsByLocalId(getlocalRecordRequest.getLocalId())
                .stream()
                .map(record -> modelMapper.map(record, LocalRecordResponse.class))
                .collect(Collectors.toList());
        return new GetLocalRecordResponse(newRecords);

    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = modelMapper.map(paymentRequest, Payment.class);
        Payment newPayment = paymentRepo.save(payment);
        return modelMapper.map(newPayment, PaymentResponse.class);
    }

    @Override
    public DisputeResponse createDispute(DisputeRequest disputeRequest) {
        Dispute dispute = modelMapper.map(disputeRequest, Dispute.class);
        Dispute newDispute = disputeRepo.save(dispute);
        return modelMapper.map(newDispute, DisputeResponse.class);
    }

}
