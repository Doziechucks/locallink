package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.RevenueOfficer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RevenueOfficerRepo extends MongoRepository<RevenueOfficer, String> {
    RevenueOfficer findRevenueOfficerByEmail(String email);


}
