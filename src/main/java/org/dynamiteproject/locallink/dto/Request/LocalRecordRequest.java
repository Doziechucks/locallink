package org.dynamiteproject.locallink.dto.Request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LocalRecordRequest {
    private String title;
    private String comments;
    private String officerId;
    private String localName;
    private List<String> url;
    private BigDecimal fee;
}
