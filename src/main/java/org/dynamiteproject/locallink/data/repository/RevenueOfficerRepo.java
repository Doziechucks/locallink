package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.RevenueOfficer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueOfficerRepo extends MongoRepository<RevenueOfficer, String> {
    RevenueOfficer saveRevenueOfficer(RevenueOfficer revenueOfficer);
    RevenueOfficer findRevenueOfficerByOfficerId(String officerId);
    List<RevenueOfficer> findRevenueOfficerByFirstname(String firstname);
    List<RevenueOfficer> findRevenueOfficerByLastname(String lastname);
    RevenueOfficer findRevenueOfficerByEmail(String email);
    RevenueOfficer findRevenueOfficerByAppointmentId(String appointmentId);

}
