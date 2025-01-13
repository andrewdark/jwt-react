package ua.pp.darknsoft.jwt.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.jwt.models.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);
    Page<AppUser> findAllByEnabled(Boolean enabled, Pageable pageable);
}
