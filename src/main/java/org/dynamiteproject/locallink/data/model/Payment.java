package org.dynamiteproject.locallink.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Document(collection = "payment")
@NoArgsConstructor
public class Payment {
    @MongoId
    private String paymentId;
    @Setter
    private String title;
    @Setter
    private String localId;
    @Setter
    private BigDecimal amount;
    @Setter
    private String description;
    @Setter
    @Indexed(unique = true)
    private String transactionId;
    @Setter
    private List<String> url;
    @Setter
    private boolean isVerified;

    public Payment(String title, String localId, BigDecimal amount, String description, String transactionId, List<String> url, boolean isVerified) {
        this.title = title;
        this.localId = localId;
        this.amount = amount;
        this.description = description;
        this.transactionId = transactionId;
        this.url = url;
        this.isVerified = isVerified;
    }
}
