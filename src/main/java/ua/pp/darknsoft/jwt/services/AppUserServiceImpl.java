package ua.pp.darknsoft.jwt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.jwt.models.AppUser;
import ua.pp.darknsoft.jwt.repositories.AppUserRepository;

import java.util.Optional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> findByUsername(String userName) {
        return appUserRepository.findByUserName(userName);
    }
}
