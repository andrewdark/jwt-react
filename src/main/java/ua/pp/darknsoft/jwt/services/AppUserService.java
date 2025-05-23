package ua.pp.darknsoft.jwt.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.darknsoft.jwt.dto.AppUserDTO;
import ua.pp.darknsoft.jwt.dto.RegistrationRequestDTO;
import ua.pp.darknsoft.jwt.models.AppUser;

import java.util.Optional;

public interface AppUserService {
   Optional<AppUser> findByUsername(String userName);
   Optional<AppUserDTO> findByAccessToken(String accessToken);
   Boolean isExistsByUserName(String userName);
   Boolean isExists(AppUser appUser);
   AppUser createAppUser(RegistrationRequestDTO registrationUser);
   public Page<AppUser> getAllDisabled(Pageable page);
   Page<AppUserDTO> getAll(Pageable page);
   Page<AppUser> getAllEnabled(Pageable page);

   AppUser getReference(Long id);

}
