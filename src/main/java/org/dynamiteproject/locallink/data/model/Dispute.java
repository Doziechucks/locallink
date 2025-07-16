package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "dispute")
@Getter @NoArgsConstructor
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

    public Dispute(String title, String description, String localId, List<String> url, boolean isSettled) {
        this.title = title;
        this.description = description;
        this.localId = localId;
        this.url = url;
        this.isSettled = isSettled;
    }

}
