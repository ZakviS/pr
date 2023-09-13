package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@Builder
@ToString(of = {"lastName", "firstName", "middleName", "login"}, callSuper = true)
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private Boolean blocked;

    private Date lastPassChange = new Date();

    @Column(columnDefinition = "int default 0")
    private int authFails = 0;

    @Column(columnDefinition = "TEXT")
    private String token;

    private Date tokenCreatedDate;

    private Date tokenExpirationDate;

    //

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Role> roles = new HashSet<>();

    @Column(length = 60)
    private String lastName;

    public Set<Role> getRoles() {
        return roles;
    }

    @Column(length = 60)
    private String firstName;

    @Column(length = 60)
    private String middleName;

    public String getFullName() {
        return getFullName(lastName, firstName, middleName);
    }

    public Set<String> getRolesAsStrings() {
        return roles.stream().map(v -> v.name()).collect(Collectors.toSet());
    }

    /**
     * @return Фамилия Имя Отчество
     */
    public static String getFullName(String lastName, String firstName, String middleName) {
        return Stream.of(lastName, firstName, middleName)
            .filter(name -> name != null && !name.isEmpty())
            .collect(Collectors.joining(" "));
    }

}
