package ua.pp.darknsoft.jwt.services;

import org.springframework.stereotype.Service;
import ua.pp.darknsoft.jwt.dto.RefreshTokenDTO;
import ua.pp.darknsoft.jwt.models.AppRefreshToken;

public interface AppRefreshTokenService {
    AppRefreshToken save(AppRefreshToken dto);
}
