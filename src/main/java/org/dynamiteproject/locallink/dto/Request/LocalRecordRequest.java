package org.dynamiteproject.locallink.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data @AllArgsConstructor
public class LocalRecordRequest {
    private String title;
    private String comments;
    private String officerId;
    private String localId;
    private List<String> url;
    private BigDecimal fee;
}
