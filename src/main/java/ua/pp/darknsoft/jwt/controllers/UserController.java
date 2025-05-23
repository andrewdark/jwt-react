package ua.pp.darknsoft.jwt.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.jwt.dto.AppUserDTO;
import ua.pp.darknsoft.jwt.services.AppUserService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping(value = "/me")
    public ResponseEntity<AppUserDTO> me(@RequestHeader(HttpHeaders.AUTHORIZATION) String authKey) {

        if (StringUtils.hasText(authKey) && authKey.startsWith("Bearer ")) {
            String accessToken = authKey.substring(7, authKey.length());
            Optional<AppUserDTO> appUserDTOOptional = appUserService.findByAccessToken(accessToken);
            if (appUserDTOOptional.isPresent()) {
                return ResponseEntity.ok(appUserDTOOptional.get());
            } else ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER_NOT_FOUND_EXCEPTION");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
