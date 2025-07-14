package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Request.LoginRequest;
import org.dynamiteproject.locallink.dto.Request.StaffCreateAccountRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;

public interface AuthenticationService {
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest);
    public String registerStaff(StaffCreateAccountRequest request);
    public String login(LoginRequest request);
}
