package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LocalResponse {
    private String localId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
}
