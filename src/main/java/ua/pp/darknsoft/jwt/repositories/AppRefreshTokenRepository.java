package ua.pp.darknsoft.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.jwt.models.AppRefreshToken;

public interface AppRefreshTokenRepository extends JpaRepository<AppRefreshToken, Long> {
}
