package org.dynamiteproject.locallink.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dynamiteproject.locallink.data.model.Role;

@Data @AllArgsConstructor
public class StaffCreateAccountRequest {
    private String employmentId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
