package ua.pp.darknsoft.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.jwt.models.AppRefreshToken;
import ua.pp.darknsoft.jwt.models.AppUser;

import java.util.Optional;

public interface AppRefreshTokenRepository extends JpaRepository<AppRefreshToken, Long> {
    Optional<AppRefreshToken> findAppRefreshTokenByAppUser(AppUser appUser);
    Optional<AppRefreshToken> findAppRefreshTokenByRefreshToken(String refreshToken);
}
