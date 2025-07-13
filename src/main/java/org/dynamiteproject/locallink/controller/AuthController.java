package org.dynamiteproject.locallink.controller;

import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.dynamiteproject.locallink.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
    @PostMapping("/register")
    public ResponseEntity<LocalRegistrationResponse> registerUser(@RequestBody @Valid LocalRegistrationRequest request){
        LocalRegistrationResponse response = authService.registerLocal(request);
        return ResponseEntity.ok(response);
    }


}
