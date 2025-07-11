package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.data.model.Record;
import org.dynamiteproject.locallink.data.model.Local;
import org.dynamiteproject.locallink.data.repository.LocalRepo;
import org.dynamiteproject.locallink.data.repository.RecordRepo;
import org.dynamiteproject.locallink.dto.Request.LocalRecordRequest;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRecordResponse;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.modelmapper.ModelMapper;


public class UserAuthenticationServiceImpl implements UserAuthenticationServices{
    private final LocalRepo localRepo;
    private final RecordRepo recordRepo;
    private final ModelMapper modelMapper;

    public UserAuthenticationServiceImpl(LocalRepo localRepo, RecordRepo recordRepo, ModelMapper modelMapper) {
        this.localRepo = localRepo;
        this.recordRepo = recordRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest) {
        Local local = modelMapper.map(localRegistrationRequest, Local.class);
        Local newLocal = localRepo.save(local);
        return modelMapper.map(newLocal, LocalRegistrationResponse.class);
    }

    @Override
    public LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest) {
        Record record = modelMapper.map(localRecordRequest, Record.class);
        Record newRecord = recordRepo.saveRecord(record);
        return modelMapper.map(newRecord, LocalRecordResponse.class);
    }
}
