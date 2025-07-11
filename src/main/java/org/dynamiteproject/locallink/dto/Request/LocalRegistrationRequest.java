package org.dynamiteproject.locallink.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LocalRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
}
