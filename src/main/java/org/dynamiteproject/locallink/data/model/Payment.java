package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Getter @AllArgsConstructor
public class Payment {
    @MongoId
    private String paymentId;
    @Setter
    private String title;
    @Setter
    private String description;
    @Setter
    @Indexed(unique = true)
    private String transactionId;
    @Setter
    private List<String> url;
    @Setter
    private boolean isVerified;
}
