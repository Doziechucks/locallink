package org.dynamiteproject.locallink.dto.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LocalRecordResponse {
    private String recordId;
    private String title;
    private BigDecimal fee;

}
