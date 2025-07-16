package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data @AllArgsConstructor
public class LocalRecordResponse {
    private String recordId;
    private String title;
    private BigDecimal fee;

}
