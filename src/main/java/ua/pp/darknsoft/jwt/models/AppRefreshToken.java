package ua.pp.darknsoft.jwt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = @Index(columnList = "refresh_token"))
public class AppRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "browser_fingerprint", nullable = false)
    private String browserFingerprint;

    @OneToOne
    @JoinColumn(name = "user_id", unique=true)
    private AppUser appUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppRefreshToken)) return false;

        AppRefreshToken that = (AppRefreshToken) o;

        return appUser.equals(that.appUser);
    }

    @Override
    public int hashCode() {
        return appUser.hashCode();
    }
}
