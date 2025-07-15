package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Local;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepo extends MongoRepository<Local, String> {
    Local findLocalByEmail(String email);

}
