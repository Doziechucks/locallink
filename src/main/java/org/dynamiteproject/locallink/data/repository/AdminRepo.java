package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends MongoRepository<Admin, String> {
    Admin saveAdmin(Admin admin);
    Admin findAdminByAdminId(String adminId);
    List<Admin> findAdminByEmploymentId(String employmentId);
    Admin findAdminByEmail(String email);
    List<Admin> findAdminByFirstname(String name);
    List<Admin> findAdminByLastname(String name);

}
