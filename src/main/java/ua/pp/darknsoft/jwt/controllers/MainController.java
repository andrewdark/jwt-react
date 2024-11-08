package ua.pp.darknsoft.jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.jwt.utils.jwt.JwtUtils;

import java.util.Objects;

@RestController
public class MainController {

    private final JwtUtils jwtUtils;

    public MainController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @GetMapping(value = "/api/test")
    public ResponseEntity<?> test(@RequestParam String name) {
        String jwt = Objects.isNull(name) ? "TEST PAGE" : jwtUtils.generateJwtAccessToken(name);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping(value = "/api/sec")
    public ResponseEntity<?> testSec() {
        return ResponseEntity.ok("SECURED PAGE");
    }
}
