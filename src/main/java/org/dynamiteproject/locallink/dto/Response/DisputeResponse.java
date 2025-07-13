package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dynamiteproject.locallink.data.model.Dispute;

@Data @AllArgsConstructor
public class DisputeResponse {
    private Dispute disputeId;
    private Boolean isSettled;
}
