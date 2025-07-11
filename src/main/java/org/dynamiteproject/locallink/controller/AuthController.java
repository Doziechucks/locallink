package org.dynamiteproject.locallink.controller;

import org.dynamiteproject.locallink.dto.Request.LocalRecordRequest;
import org.dynamiteproject.locallink.dto.Request.getLocalRecordRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRecordResponse;
import org.dynamiteproject.locallink.dto.Response.getLocalRecordResponse;
import org.dynamiteproject.locallink.service.UserAuthenticationServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAuthenticationServices userService;

    public AuthController(UserAuthenticationServices userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
    @PostMapping("/register")
    public ResponseEntity<LocalRegistrationResponse> registerUser(@RequestBody @Valid LocalRegistrationRequest request){
        LocalRegistrationResponse response = userService.registerLocal(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
    @PostMapping("/createRecord")
    public ResponseEntity<LocalRecordResponse> createRecord(@RequestBody @Valid LocalRecordRequest request){
        LocalRecordResponse response = userService.createRecord(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRecords")
    public ResponseEntity<getLocalRecordResponse> getRecords(@RequestBody @Valid getLocalRecordRequest request){
        getLocalRecordResponse response = userService.getLocals(request);
        return ResponseEntity.ok(response);
    }

}
