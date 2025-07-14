package org.dynamiteproject.locallink.controller;

import org.dynamiteproject.locallink.dto.Request.*;
import org.dynamiteproject.locallink.dto.Response.*;
import org.dynamiteproject.locallink.service.UserServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServices userService;

    public UserController(UserServices userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
    @PostMapping("/createRecord")
    public ResponseEntity<LocalRecordResponse> createRecord(@RequestBody @Valid LocalRecordRequest request){
        LocalRecordResponse response = userService.createRecord(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLocalRecords")
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

    @GetMapping("/viewDisputes")
    public ResponseEntity<GetDisputeResponse> viewDisputes() {
        GetDisputeResponse response = userService.getUnsettledDisputes();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("/settleDispute")
    public ResponseEntity<String> settleDispute(@RequestBody @Valid SettleDisputeRequest request){
        String response = userService.settleDispute(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("/verifyPayment")
    public ResponseEntity<String> verifyPayment(@RequestBody @Valid VerifyPaymentRequest request){
        String response = userService.verifyPayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getOutStandings")
    public ResponseEntity<GetOutStandingResponse> outStandings(@RequestBody @Valid GetOutStandingRequest request) {
        GetOutStandingResponse response = userService.getOutStandings(request);
        return ResponseEntity.ok(response);
    }




}
