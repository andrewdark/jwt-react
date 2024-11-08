package ua.pp.darknsoft.jwt.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_USER", uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UC", columnNames = "user_name")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "user_name", length = 36, nullable = false)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,36}$")
    private String userName;
    @Column(name = "encrypted_password", length = 128, nullable = false)
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
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
