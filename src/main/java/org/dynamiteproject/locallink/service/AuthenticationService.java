package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;

public interface AuthenticationService {
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest);
}
