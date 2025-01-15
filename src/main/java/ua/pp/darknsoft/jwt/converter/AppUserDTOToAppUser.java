package ua.pp.darknsoft.jwt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.jwt.dto.AppUserDTO;
import ua.pp.darknsoft.jwt.models.AppUser;

@Component
public class AppUserDTOToAppUser implements Converter<AppUserDTO, AppUser> {

    @Override
    public AppUser convert(AppUserDTO source) {
        return AppUser.builder()
                .userId(source.getUserId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .build();
    }
}
