package org.dynamiteproject.locallink.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dynamiteproject.locallink.dto.Request.DisputeRequest;

import java.util.List;

@Data @AllArgsConstructor
public class GetDisputeResponse {
    List<DisputeRequest> disputes;
}
