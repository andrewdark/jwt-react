package ua.pp.darknsoft.jwt.services;

import ua.pp.darknsoft.jwt.models.AppRefreshToken;
import ua.pp.darknsoft.jwt.models.AppUser;

public interface AppRefreshTokenService {
    AppRefreshToken save(AppRefreshToken dto);
    void deleteByUserId(AppUser appUser);
}
