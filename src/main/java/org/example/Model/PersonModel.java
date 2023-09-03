package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entity.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {

    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private boolean passwordExpired;
    private Set<Role> roles = new HashSet<>();
    //
    private String token;
    //
    private String fullName;

}
