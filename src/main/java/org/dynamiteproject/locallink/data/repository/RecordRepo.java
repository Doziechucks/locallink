package org.dynamiteproject.locallink.data.repository;
import org.dynamiteproject.locallink.data.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepo extends MongoRepository<Record, String> {
    Record saveRecord(Record record);
    List<Record> findRecordsBy(String title);
    Record findRecordByRecordId(String recordId);
    List<Record> findRecordsByLocalId(String localId);
    List<Record> findRecordsByOfficerId(String officerId);
    Record updateRecord(Record record);
}
