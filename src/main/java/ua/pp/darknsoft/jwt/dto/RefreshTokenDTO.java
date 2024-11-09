package ua.pp.darknsoft.jwt.dto;

import jakarta.persistence.Column;
import lombok.*;
import ua.pp.darknsoft.jwt.models.AppUser;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenDTO {
    private Long tokenId;
    private AppUser appUser;
    private String refreshToken;
    private String ipAddress="172.0.0.1";
    private String browserFingerprint;
}
