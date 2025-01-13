package ua.pp.darknsoft.jwt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequestDTO {
    @NotNull(message = "should not be null")
    private String firstName;
    @NotBlank(message = "Name is mandatory")
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;


}
