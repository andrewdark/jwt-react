package ua.pp.darknsoft.jwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_USER", uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UC", columnNames = "email")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", length = 36, nullable = false)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,36}$")
    private String firstName;

    @Column(name = "last_name", length = 36, nullable = false)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,36}$")
    private String lastName;

    @Column(name = "email", length = 36, nullable = false)
   // @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,36}$")
    private String email;

    @Column(name = "encrypted_password", length = 128, nullable = false)
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    @JsonIgnore
    private String encryptedPassword;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    @NotNull
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Version
    private Long version;

    @OneToMany(mappedBy = "appUser")
    private Set<RoledUser> roledUsers = new HashSet<>();
}
