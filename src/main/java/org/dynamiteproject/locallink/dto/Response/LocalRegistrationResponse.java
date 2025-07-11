package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LocalRegistrationResponse {
    private String localId;
    private String firstName;
}
