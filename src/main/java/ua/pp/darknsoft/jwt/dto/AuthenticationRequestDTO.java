package ua.pp.darknsoft.jwt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequestDTO {
    private String userName;
    private String password;
}
