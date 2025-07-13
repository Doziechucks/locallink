package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.data.model.Local;
import org.dynamiteproject.locallink.data.repository.LocalRepo;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ModelMapper modelMapper;
    private final LocalRepo localRepo;

    public AuthenticationServiceImpl(ModelMapper modelMapper, LocalRepo localRepo) {
        this.modelMapper = modelMapper;
        this.localRepo = localRepo;
    }

    @Override
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest) {
        Local local = modelMapper.map(localRegistrationRequest, Local.class);
        Local newLocal = localRepo.save(local);
        return modelMapper.map(newLocal, LocalRegistrationResponse.class);
    }

}
