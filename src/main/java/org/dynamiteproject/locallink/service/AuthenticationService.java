package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Request.LoginRequest;
import org.dynamiteproject.locallink.dto.Request.StaffCreateAccountRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.dynamiteproject.locallink.dto.Response.LoginResponse;

public interface AuthenticationService {
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest);
    public LoginResponse registerStaff(StaffCreateAccountRequest request);
    public LoginResponse login(LoginRequest request);
}
