package ua.pp.darknsoft.jwt.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.jwt.dto.RegistrationResponseDTO;
import ua.pp.darknsoft.jwt.models.AppUser;
import ua.pp.darknsoft.jwt.repositories.AppUserRepository;

import java.util.Optional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> findByUsername(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    @Override
    public Boolean isExistsByUserName(String userName) {
        return appUserRepository.existsByUserName(userName.toLowerCase());
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean isExists(AppUser appUser) {
        return appUserRepository.existsByUserName(appUser.getUserName().toLowerCase());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AppUser> getAll(Pageable page) {
        return appUserRepository.findAll(page);
    }

    @Override
    public Page<AppUser> getAllEnabled(Pageable page) {
        return appUserRepository.findAllByEnabled(true, page);
    }

    @Override
    public AppUser getReference(Long id) {
        return appUserRepository.getReferenceById(id);
    }

    @Override
    public Page<AppUser> getAllDisabled(Pageable page) {
        return appUserRepository.findAllByEnabled(false, page);
    }

    @Override
    public AppUser createAppUser(RegistrationResponseDTO registrationUser) {
        AppUser savedUser = new AppUser();
        AppUser newAppUser = AppUser.builder()
                .userName(registrationUser.getEmail().toLowerCase())
                .encryptedPassword(registrationUser.getPassword())
                .enabled(true)
                .build();

        if (!isExists(newAppUser))
            savedUser = appUserRepository.save(newAppUser);

        return savedUser;
    }
}
