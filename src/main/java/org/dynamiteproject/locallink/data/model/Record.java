package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Document
@Getter @NoArgsConstructor
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
    private BigDecimal fee;
    @Setter
    private List<String> url;

    public Record(String title, String comments, String OfficerId, String LocalId, BigDecimal fee, List<String> url) {
        this.title = title;
        this.comments = comments;
        this.OfficerId = OfficerId;
        this.LocalId = LocalId;
        this.fee = fee;
        this.url = url;
    }
}
