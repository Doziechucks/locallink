package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class GetLocalRecordResponse {
    private List<LocalRecordResponse> localRecords;
}
