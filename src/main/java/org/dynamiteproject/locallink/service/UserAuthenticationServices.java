package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.LocalRecordRequest;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRecordResponse;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface UserAuthenticationServices {
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest);
    public LocalRecordResponse createRecord(LocalRecordRequest localRecordRequest);

}

