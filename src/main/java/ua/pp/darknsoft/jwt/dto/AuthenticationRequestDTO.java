package ua.pp.darknsoft.jwt.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequestDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
