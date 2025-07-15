package org.dynamiteproject.locallink.controller;

import jakarta.annotation.security.PermitAll;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Request.LoginRequest;
import org.dynamiteproject.locallink.dto.Request.StaffCreateAccountRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.dynamiteproject.locallink.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
    @PostMapping("/registerLocal")
    public ResponseEntity<LocalRegistrationResponse> registerUser(@RequestBody @Valid LocalRegistrationRequest request){
        LocalRegistrationResponse response = authService.registerLocal(request);
        return ResponseEntity.ok(response);
    }

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<?> registerStaff(@RequestBody @Valid StaffCreateAccountRequest request){
        try{
            String response = authService.registerStaff(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PermitAll
    @PostMapping("/login")
    private ResponseEntity<?> usersLogin(@RequestBody @Valid LoginRequest request){
        try{
            String response = authService.login(request);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
