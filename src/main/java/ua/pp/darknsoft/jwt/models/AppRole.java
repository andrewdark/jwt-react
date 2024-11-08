package ua.pp.darknsoft.jwt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "APP_ROLE", uniqueConstraints = {@UniqueConstraint(name = "APP_ROLE_UC", columnNames = "role_name")})
public class AppRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(name = "role_name", length = 36, nullable = false)
    @Pattern.List({
            @Pattern(regexp = "^ROLE_[A-Z0-9]{2,36}$"),
            @Pattern(regexp = "^[A-Z]")
    })
    private String roleName;
    @NotNull
    private LocalDateTime created = LocalDateTime.now();
    @Version
    private Long version;
    @OneToMany(mappedBy = "appRole")
    private Set<RoledUser> roledUsers = new HashSet<>();
}
