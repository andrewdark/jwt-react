package ua.pp.darknsoft.jwt.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.jwt.dto.AuthenticationRequestDTO;
import ua.pp.darknsoft.jwt.dto.AuthenticationResponseDTO;
import ua.pp.darknsoft.jwt.dto.RegistrationRequestDTO;
import ua.pp.darknsoft.jwt.dto.security.UserDetailsImpl;
import ua.pp.darknsoft.jwt.models.AppRefreshToken;
import ua.pp.darknsoft.jwt.models.AppUser;
import ua.pp.darknsoft.jwt.utils.jwt.JwtUtils;

import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AppUserService appUserService;
    private final AppRefreshTokenService appRefreshTokenService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(AppUserService appUserService, AppRefreshTokenService appRefreshTokenService, JwtUtils jwtUtils, AuthenticationManager authenticationManager, PasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.appRefreshTokenService = appRefreshTokenService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AuthenticationResponseDTO registration(RegistrationRequestDTO responseDTO) {
        if (appUserService.findByUsername(responseDTO.getEmail().toLowerCase()).isPresent()) {
            return null;
        }
        responseDTO.setEmail(responseDTO.getEmail().toLowerCase());
        responseDTO.setPassword(bCryptPasswordEncoder.encode(responseDTO.getPassword()));
        AppUser user = appUserService.createAppUser(responseDTO);

        AuthenticationResponseDTO response = AuthenticationResponseDTO.builder()
                .userId(user.getUserId())
                .accessToken(jwtUtils.generateJwtAccessToken(user.getEmail()))
                .refreshToken(jwtUtils.generateJwtRefreshToken(user.getEmail()))
                .build();
        return response;
    }

    @Override
    public AuthenticationResponseDTO authenticateUser(AuthenticationRequestDTO authenticationRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail().toLowerCase(), authenticationRequestDTO.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        AuthenticationResponseDTO authenticationResponseDTO = AuthenticationResponseDTO.builder()
                .userId(userDetails.getId())
                .accessToken((jwtUtils.generateJwtAccessToken(userDetails.getUsername())))
                .refreshToken(jwtUtils.generateJwtRefreshToken(userDetails.getUsername()))
                .build();

        AppUser appUser = appUserService.getReference(userDetails.getId());
        AppRefreshToken appRefreshToken = new AppRefreshToken();
        appRefreshToken.setRefreshToken((authenticationResponseDTO.getRefreshToken()));
        appRefreshToken.setAppUser(appUser);
        appRefreshToken.setIpAddress("172.0.0.1");
        appRefreshToken.setBrowserFingerprint("NO-INFORMATION");
        appRefreshTokenService.save(appRefreshToken);
        return authenticationResponseDTO;
    }

    @Override
    public void logout(String refreshToken) {
        if (jwtUtils.validateJwtRefreshToken(refreshToken)) {
            String userName = jwtUtils.getUserNameFromJwtRefreshToken(refreshToken);
            appUserService.findByUsername(userName).ifPresent(appRefreshTokenService::deleteByUserId);
        }

    }

    @Override
    public AuthenticationResponseDTO refresh(String refreshToken) {

        if (jwtUtils.validateJwtRefreshToken(refreshToken)) {
            String userName = jwtUtils.getUserNameFromJwtRefreshToken(refreshToken);
            Optional<AppUser> appUserOptional = appUserService.findByUsername(userName);
            if (appUserOptional.isPresent() && appRefreshTokenService.isOriginal(refreshToken)) {

                AppUser appUser = appUserOptional.get();
                AuthenticationResponseDTO authenticationResponseDTO = AuthenticationResponseDTO.builder()
                        .userId(appUser.getUserId())
                        .accessToken((jwtUtils.generateJwtAccessToken(appUser.getEmail())))
                        .refreshToken(jwtUtils.generateJwtRefreshToken(appUser.getEmail()))
                        .build();

                AppRefreshToken appRefreshToken = new AppRefreshToken();
                appRefreshToken.setRefreshToken((authenticationResponseDTO.getRefreshToken()));
                appRefreshToken.setAppUser(appUser);
                appRefreshToken.setIpAddress("172.0.0.1");
                appRefreshToken.setBrowserFingerprint("NO-INFORMATION");
                appRefreshTokenService.save(appRefreshToken);
                return authenticationResponseDTO;
            }
        }
        return null;
    }
}
