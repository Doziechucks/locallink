package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.data.model.Admin;
import org.dynamiteproject.locallink.data.model.Local;
import org.dynamiteproject.locallink.data.model.RevenueOfficer;
import org.dynamiteproject.locallink.data.model.Role;
import org.dynamiteproject.locallink.data.repository.AdminRepo;
import org.dynamiteproject.locallink.data.repository.LocalRepo;
import org.dynamiteproject.locallink.data.repository.RevenueOfficerRepo;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Request.LoginRequest;
import org.dynamiteproject.locallink.dto.Request.StaffCreateAccountRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.dynamiteproject.locallink.utils.JwtUtils;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ModelMapper modelMapper;
    private final LocalRepo localRepo;
    private final AdminRepo adminRepo;
    private final RevenueOfficerRepo officerRepo;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    String  passwordRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{8,}$";
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,15}$";




    public AuthenticationServiceImpl(ModelMapper modelMapper, LocalRepo localRepo, AdminRepo adminRepo, RevenueOfficerRepo officerRepo, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.localRepo = localRepo;
        this.adminRepo = adminRepo;
        this.officerRepo = officerRepo;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LocalRegistrationResponse registerLocal(LocalRegistrationRequest localRegistrationRequest) {
        if(validateInformationIntegrity(localRegistrationRequest)) {
            localRegistrationRequest.setPassword(passwordEncoder.encode(localRegistrationRequest.getPassword()));
            Local local = modelMapper.map(localRegistrationRequest, Local.class);
            Local newLocal = localRepo.save(local);
            return modelMapper.map(newLocal, LocalRegistrationResponse.class);
        }
        throw new IllegalArgumentException("Invalid credentials");
    }


    public String registerStaff(StaffCreateAccountRequest request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (Character.isUpperCase(request.getEmploymentId().charAt(0))){
            Admin admin = modelMapper.map(request, Admin.class);
            Admin newAdmin = adminRepo.save(admin);
            return jwtUtils.generateToken(newAdmin.getAdminId(), newAdmin.getFirstname(), newAdmin.getEmail(), newAdmin.getRole());

        }
        else{
            RevenueOfficer officer = modelMapper.map(request, RevenueOfficer.class);
            RevenueOfficer newOfficer = officerRepo.save(officer);
            return jwtUtils.generateToken(newOfficer.getOfficerId(), newOfficer.getFirstname(), newOfficer.getEmail(), newOfficer.getRole());

        }
    }

    public String login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();



        // Try Local
        Local local = localRepo.findLocalByEmail(email);
        if (local != null) {
            boolean isValid = passwordEncoder.matches(password, local.getPassword());
            return isValid
                    ? jwtUtils.generateToken(local.getLocalId(), local.getFirstname(), local.getEmail(), local.getRole())
                    : "Invalid login credentials";
        }

        // Try Admin
        Admin admin = adminRepo.findAdminByEmail(email);
        if (admin != null) {
            boolean isValid = passwordEncoder.matches(password, admin.getPassword());
            return isValid
                    ? jwtUtils.generateToken(admin.getAdminId(), admin.getFirstname(), admin.getEmail(), admin.getRole())
                    : "Invalid login credentials";
        }

        // Try Revenue Officer
        RevenueOfficer officer = officerRepo.findRevenueOfficerByEmail(email);
        if (officer != null) {
            boolean isValid = passwordEncoder.matches(password, officer.getPassword());
            return isValid
                    ? jwtUtils.generateToken(officer.getOfficerId(), officer.getFirstname(), officer.getEmail(), officer.getRole())
                    : "Invalid login credentials";
        }

        // No user found
        return "Invalid email or password";
    }
    private Boolean validateInformationIntegrity(LocalRegistrationRequest request){
        if(request.getFirstname() == null || request.getFirstname().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if(request.getLastname() == null || request.getLastname().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty() || !request.getPassword().matches(passwordRegex)){
            throw new IllegalArgumentException("Invalid password try again");
        }
        if( (request.getEmail() == null || request.getEmail().isEmpty()) || !request.getEmail().matches(emailRegex)){
            throw new IllegalArgumentException("Invalid email try again");
        }
        return true;
    }


}
