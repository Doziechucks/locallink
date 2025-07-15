package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends MongoRepository<Admin, String> {
    Admin findAdminByEmail(String email);


}
