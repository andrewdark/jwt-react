package ua.pp.darknsoft.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private Long userId;
    private String userName;
    private String encryptedPassword;
}
