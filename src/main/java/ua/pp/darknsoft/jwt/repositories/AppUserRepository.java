package ua.pp.darknsoft.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.jwt.models.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
