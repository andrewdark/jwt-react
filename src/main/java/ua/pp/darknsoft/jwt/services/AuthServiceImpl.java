package ua.pp.darknsoft.jwt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.jwt.dto.AuthenticationResponseDTO;
import ua.pp.darknsoft.jwt.dto.RegistrationResponseDTO;
import ua.pp.darknsoft.jwt.models.AppUser;
import ua.pp.darknsoft.jwt.utils.jwt.JwtUtils;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AppUserService appUserService, JwtUtils jwtUtils) {
        this.appUserService = appUserService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AuthenticationResponseDTO registration(RegistrationResponseDTO responseDTO) {
        if (appUserService.findByUsername(responseDTO.getEmail().toLowerCase()).isPresent()) {
            return null;
        }
        AppUser user = appUserService.createAppUser(responseDTO);

        AuthenticationResponseDTO response = AuthenticationResponseDTO.builder()
                .userId(user.getUserId())
                .accessToken(jwtUtils.generateJwtAccessToken(user.getUserName()))
                .refreshToken(jwtUtils.generateJwtRefreshToken(user.getUserName()))
                .build();
        return response;
    }
}
