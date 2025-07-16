package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LocalRegistrationResponse {
    private String localId;
    private String firstname;
}
