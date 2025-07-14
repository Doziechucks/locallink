package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends MongoRepository<Payment, String>{
    Payment savePayment(Payment payment);
    Payment findByPaymentId(String paymentId);
    List<Payment> findPaymentsByLocalId(String paymentId);
    Payment findByTransactionId(String transactionId);
    List<Payment> findPaymentByVerified(Boolean isVerified);
    List<Payment> findPaymentByTitle(String title);
    Payment findPaymentByTransactionId(String transactionId);
    Payment updatePayment(Payment payment);
}
