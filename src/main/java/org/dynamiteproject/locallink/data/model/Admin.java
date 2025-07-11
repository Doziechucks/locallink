package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
@Getter @AllArgsConstructor
public class Admin {
    @MongoId
    private String adminId;
    @Setter
    private String firstname;
    @Setter
    private String lastname;
    @Setter
    private String email;
    @Setter
    private String employmentId;
    @Setter
    private String phone;
    @Setter
    private Role role = Role.ADMIN;
}
