package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "admins")
@Getter
public class Admin {
    @MongoId
    private String adminId;
    @Setter
    private String employmentId;
    @Setter
    private String firstname;
    @Setter
    private String lastname;
    @Setter
    private String email;
    @Setter
    private String password;
    @Setter
    private String phoneNumber;
    @Setter
    private Role role = Role.ADMIN;

    public Admin(String employmentId, String firstname, String lastname, String email, String password, String phoneNumber) {
        this.employmentId = employmentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
