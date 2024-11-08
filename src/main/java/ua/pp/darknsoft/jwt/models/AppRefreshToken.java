package ua.pp.darknsoft.jwt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
