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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {
    private final LocalRepo localRepo;
    private final RecordRepo recordRepo;
    private final ModelMapper modelMapper;
    private final PaymentRepo paymentRepo;
    private final DisputeRepo disputeRepo;


    public UserServiceImpl(LocalRepo localRepo, RecordRepo recordRepo, ModelMapper modelMapper, PaymentRepo paymentRepo, DisputeRepo disputeRepo) {
        this.localRepo = localRepo;
        this.recordRepo = recordRepo;
        this.modelMapper = modelMapper;
        this.paymentRepo = paymentRepo;
        this.disputeRepo = disputeRepo;
    }

    @Override
    public LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest) {
        Record record = modelMapper.map(localRecordRequest, Record.class);
        Record newRecord = recordRepo.save(record);
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

    @Override
    public GetDisputeResponse getUnsettledDisputes() {
//        List<DisputeRequest> disputes = new ArrayList<>();
//        for(Dispute dispute : disputeRepo.findAll()){
//            if (!dispute.isSettled()){
//                DisputeRequest newDispute = modelMapper.map(dispute, DisputeRequest.class);
//                disputes.add(newDispute);
//            }
//        }
//        return new GetDisputeResponse(disputes);
//    }
        List<DisputeRequest> disputes = disputeRepo.findAll().stream()
                .filter(dispute -> !dispute.isSettled()) // filter unsettled
                .map(dispute -> modelMapper.map(dispute, DisputeRequest.class)) // map to DTO
                .collect(Collectors.toList());

        return new GetDisputeResponse(disputes);
    }

    @Override
    public String settleDispute(SettleDisputeRequest request) {
        Optional<Dispute> optionalDispute = disputeRepo.findAll().stream()
                .filter(dispute -> Objects.equals(dispute.getDisputeId(), request.getDisputeId()))
                .findFirst();

        if (optionalDispute.isEmpty()) {
            throw new ValidationException("Dispute does not exist");
        }

        Dispute dispute = optionalDispute.get();

        if (dispute.isSettled()) {
            return "Dispute already settled";
        }

        dispute.setSettled(true);
        disputeRepo.save(dispute);
        return "Dispute settled";
    }

    @Override
    public String verifyPayment(VerifyPaymentRequest request) {
        Optional<Payment> optionalPayment = paymentRepo.findAll().stream()
                .filter(payment -> Objects.equals(payment.getPaymentId(), request.getPaymentId()))
                .findFirst();

        if (optionalPayment.isEmpty()) {
            throw new ValidationException("Payment does not exist");
        }
        Payment payment = optionalPayment.get();

        if (payment.isVerified()) {
            return "Payment already verified";
        }
        payment.setVerified(true);
        paymentRepo.save(payment);
        return "Payment verified";
    }

    @Override
    public GetOutStandingResponse getOutStandings(GetOutStandingRequest request) {
//        BigDecimal amount = BigDecimal.ZERO;
//        BigDecimal totalPayment = BigDecimal.ZERO;
//        BigDecimal totalFee = BigDecimal.ZERO;
//        List<Record> records = new ArrayList<>();
//        List<Payment> payments = new ArrayList<>();
//        for (Local local : localRepo.findAll()) {
//            if (request.getLocalId().equals(local.getLocalId())) {
//                for (Record record : recordRepo.findRecordsByLocalId(local.getLocalId())) {
//                    if (record.getLocalId().equals(request.getLocalId())) records.add(record);
//                    else throw new ValidationException("Local record does not exist");
//                }
//                for (Payment payment : paymentRepo.findPaymentByLocalId(local.getLocalId())) {
//                    if (payment.getPaymentId().equals(request.getLocalId())) payments.add(payment);
//                }
//            } else throw new ValidationException("Local does not exist");
//        }
//        for (Record record : records) totalFee = totalFee.add(record.getFee());
//        for (Payment payment : payments) {
//            totalPayment = totalPayment.add(payment.getAmount());
//            amount = totalFee.subtract(totalPayment);
//        }
//        return new GetOutStandingResponse(request.getLocalId(), amount);
//    }
        Local local = localRepo.findById(request.getLocalId())
                .orElseThrow(() -> new ValidationException("Local does not exist"));
        List<Record> records = recordRepo.findRecordsByLocalId(local.getLocalId());
        List<Payment> newPayments = paymentRepo.findPaymentsByLocalId(local.getLocalId());
        List<Payment> payments = new ArrayList<>();
        for (Payment payment : newPayments) {
            if (payment.isVerified())
                payments.add(payment);
        }
        if (records.isEmpty()) {
            throw new ValidationException("No records found for local: " + local.getLocalId());
        }
        BigDecimal totalFee = records.stream()
                .map(Record::getFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPayment = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal outstandingAmount = totalFee.subtract(totalPayment);
        return new GetOutStandingResponse(local.getLocalId(), outstandingAmount);
    }

    @Override
    public GetPaymentResponse getUnverifiedPayments(){
        List<PaymentRequest> payments = paymentRepo.findAll().stream()
                .filter(payment -> !payment.isVerified())
                .map(payment -> modelMapper.map(payment, PaymentRequest.class))
                .collect(Collectors.toList());

        return new GetPaymentResponse(payments);
    }

    @Override
    public GetLocalsResponse getAllLocals() {
        List<LocalResponse> locals = localRepo.findAll().stream()
                .map(local -> modelMapper.map(local, LocalResponse.class))
                .collect(Collectors.toList());
        return new GetLocalsResponse(locals);
    }

}
