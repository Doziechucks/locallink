package org.dynamiteproject.locallink.controller;

import org.dynamiteproject.locallink.dto.Request.*;
import org.dynamiteproject.locallink.dto.Response.*;
import org.dynamiteproject.locallink.service.UserAuthenticationServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<GetLocalRecordResponse> getRecords(@RequestBody @Valid GetLocalRecordRequest request){
        GetLocalRecordResponse response = userService.getLocalRecords(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest request){
        PaymentResponse response = userService.createPayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/dispute")
    public ResponseEntity<DisputeResponse> dispute(@RequestBody @Valid DisputeRequest request){
        DisputeResponse response = userService.createDispute(request);
        return ResponseEntity.ok(response);
    }

}
