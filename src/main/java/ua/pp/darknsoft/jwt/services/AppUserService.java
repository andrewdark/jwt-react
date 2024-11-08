package ua.pp.darknsoft.jwt.services;

import ua.pp.darknsoft.jwt.models.AppUser;

import java.util.Optional;

public interface AppUserService {
   Optional<AppUser> findByUsername(String userName);
}
