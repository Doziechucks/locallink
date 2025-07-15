package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends MongoRepository<Payment, String>{
    List<Payment> findPaymentsByLocalId(String paymentId);

}
