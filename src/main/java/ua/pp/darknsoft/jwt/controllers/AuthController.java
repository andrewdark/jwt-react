package ua.pp.darknsoft.jwt.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.pp.darknsoft.jwt.dto.AuthenticationRequestDTO;
import ua.pp.darknsoft.jwt.dto.AuthenticationResponseDTO;
import ua.pp.darknsoft.jwt.dto.RegistrationResponseDTO;
import ua.pp.darknsoft.jwt.services.AuthService;

import java.net.URI;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationResponseDTO registrationResponseDTO, HttpServletResponse response) {
        AuthenticationResponseDTO authenticationResponseDTO = authService.registration(registrationResponseDTO);
        if (Objects.isNull(authenticationResponseDTO)) return ResponseEntity.badRequest()
                .body("Error: Username is already taken!");
        //Set cookie with  RefreshToken
        if (Objects.nonNull(authenticationResponseDTO.getRefreshToken())) {
            response.addCookie(getRefreshTokenCookie(authenticationResponseDTO.getRefreshToken(), getRefreshMaxAge()));
        }
        return ResponseEntity.ok(authenticationResponseDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO authenticationRequest, HttpServletResponse response) throws Exception {
        try {
            AuthenticationResponseDTO responseDTO = authService.authenticateUser(authenticationRequest);
            //Set cookie with  RefreshToken
            if (Objects.nonNull(responseDTO.getRefreshToken())) {
                response.addCookie(getRefreshTokenCookie(responseDTO.getRefreshToken(), getRefreshMaxAge()));
            }
            return ResponseEntity.ok(responseDTO);

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "refresh-token", defaultValue = "") String requestRefreshTokenCookie,
                                    HttpServletResponse response) {
        String refreshToken = null;
        if (Strings.isNotBlank(requestRefreshTokenCookie)) {
            refreshToken = requestRefreshTokenCookie;
            response.addCookie(getRefreshTokenCookie(null, 0)); //Delete Cookie
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refresh() {
        return ResponseEntity.ok().build();
    }


    //UTILS PRIVATE
    //TODO: consider duration with example.app.jwtRefreshExpirationMs
    private int getRefreshMaxAge() {
        return (int) TimeUnit.DAYS.toSeconds(60);
    }

    private Cookie getRefreshTokenCookie(String refreshToken, int maxAge) {
        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        URI uri = URI.create(baseUrl + "/api/auth/").normalize();
        cookie.setDomain(uri.getHost());
        cookie.setPath(uri.getPath());
        return cookie;
    }
}
