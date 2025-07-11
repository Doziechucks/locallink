package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Getter
@AllArgsConstructor
public class Dispute {
    @MongoId
    private String disputeId;
    @Setter
    private String title;
    @Setter
    private String description;
    @Setter
    private String localId;
    @Setter
    private List<String> url;
    @Setter
    private boolean isSettled;

}
