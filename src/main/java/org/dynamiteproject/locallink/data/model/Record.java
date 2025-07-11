package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Getter
public class Record {
    @MongoId
    private String recordId;
    @Setter
    private String title;
    @Setter
    private String comments;
    @Setter
    private String OfficerId;
    @Setter
    private String LocalId;
    @Setter
    private List<String> url;

    public Record(String title, String comments, String OfficerId, String LocalId, List<String> url) {
        this.title = title;
        this.comments = comments;
        this.OfficerId = OfficerId;
        this.LocalId = LocalId;
        this.url = url;
    }
}
