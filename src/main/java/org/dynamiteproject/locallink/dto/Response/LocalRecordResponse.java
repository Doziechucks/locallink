package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
public class LocalRecordResponse {
    private String recordId;
    private String title;
    private BigDecimal fee;

}
