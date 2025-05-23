package ua.pp.darknsoft.jwt.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.jwt.dto.AppUserDTO;
import ua.pp.darknsoft.jwt.services.AppUserService;
import ua.pp.darknsoft.jwt.utils.jwt.JwtUtils;

import java.util.Objects;

@RestController
public class MainController {

    private final JwtUtils jwtUtils;
    private final AppUserService appUserService;

    public MainController(JwtUtils jwtUtils, AppUserService appUserService) {
        this.jwtUtils = jwtUtils;
        this.appUserService = appUserService;
    }

    @GetMapping(value = "/api/test")
    public ResponseEntity<Page<AppUserDTO>> test(Pageable pageable) {

        Page<AppUserDTO> people = appUserService.getAll(pageable);
        return ResponseEntity.ok(people);
    }

    @GetMapping(value = "/api/sec")
    public ResponseEntity<?> testSec() {
        return ResponseEntity.ok("SECURED PAGE");
    }
}
