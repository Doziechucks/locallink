package org.dynamiteproject.locallink.dto.Request;

import lombok.Data;

import java.util.List;

@Data
public class LocalRecordRequest {
    private String title;
    private String comments;
    private String officerId;
    private String localName;
    private List<String> url;
}
