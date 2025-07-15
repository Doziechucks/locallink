package org.dynamiteproject.locallink.data.repository;

import org.dynamiteproject.locallink.data.model.Dispute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisputeRepo extends MongoRepository<Dispute, String> {
}
