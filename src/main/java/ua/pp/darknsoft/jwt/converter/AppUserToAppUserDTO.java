package ua.pp.darknsoft.jwt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.jwt.dto.AppUserDTO;
import ua.pp.darknsoft.jwt.models.AppUser;

@Component
public class AppUserToAppUserDTO implements Converter<AppUser, AppUserDTO> {
    @Override
    public AppUserDTO convert(AppUser source) {

        return AppUserDTO.builder()
                .userId(source.getUserId())
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .email(source.getEmail())
                .build();
    }
}
