package org.dynamiteproject.locallink.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class DisputeRequest {
    private String title;
    private String description;
    private String localId;
    private String url;
    private Boolean isSettled;

}
