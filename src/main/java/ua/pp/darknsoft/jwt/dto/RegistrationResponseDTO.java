package ua.pp.darknsoft.jwt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponseDTO {
    private String email;
    private String password;


}
