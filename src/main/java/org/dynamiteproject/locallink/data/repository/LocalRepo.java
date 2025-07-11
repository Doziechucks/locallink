package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Local;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepo extends MongoRepository<Local, String> {
    Local saveLocal(Local local);
    List<Local> findLocalByLocalId(String localId);
    Local findLocalByEmail(String email);
    List<Local> findLocalByFirstname(String firstname);
    List<Local> findLocalByLastname(String lastname);
    List<Local> findLocalByAddress(String address);
}
