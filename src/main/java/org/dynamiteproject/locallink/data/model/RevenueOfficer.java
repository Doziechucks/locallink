package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter @AllArgsConstructor
public class RevenueOfficer {
    @MongoId
    private String officerId;
    @Setter
    private String appointmentId;
    @Setter
    private String firstname;
    @Setter
    private String lastname;
    @Setter
    private String email;
    @Setter
    private String password;
    @Setter
    private Role role = Role.OFFICER;


}
