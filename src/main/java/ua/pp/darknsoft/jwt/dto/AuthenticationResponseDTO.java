package ua.pp.darknsoft.jwt.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private Long userId;
    private AppUserDTO user;
    private String accessToken;
    private String refreshToken;

}
