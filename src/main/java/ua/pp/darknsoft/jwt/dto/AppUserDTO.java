package ua.pp.darknsoft.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class AppUserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roleList = new ArrayList<>();
}
