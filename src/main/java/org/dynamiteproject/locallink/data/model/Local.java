package org.dynamiteproject.locallink.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "local")
@Getter @NoArgsConstructor
public class Local {
    @MongoId
    private String localId;
    @Setter
    private String firstname;
    @Setter
    private String lastname;
    @Setter
    private String email;
    @Setter
    private String password;
    @Setter
    private String Address;
    @Setter
    private String phoneNumber;
    @Setter
    private Role role = Role.LOCAL;

    public Local(String firstname, String lastname, String email, String password, String address, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.Address = address;
        this.phoneNumber = phoneNumber;
    }


}
