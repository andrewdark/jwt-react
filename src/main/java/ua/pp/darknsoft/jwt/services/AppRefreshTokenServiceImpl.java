package ua.pp.darknsoft.jwt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.jwt.dto.RefreshTokenDTO;
import ua.pp.darknsoft.jwt.models.AppRefreshToken;
import ua.pp.darknsoft.jwt.models.AppUser;
import ua.pp.darknsoft.jwt.repositories.AppRefreshTokenRepository;

@Service
@Transactional
public class AppRefreshTokenServiceImpl implements AppRefreshTokenService{
    private final AppRefreshTokenRepository appRefreshTokenRepository;

    public AppRefreshTokenServiceImpl(AppRefreshTokenRepository appRefreshTokenRepository) {
        this.appRefreshTokenRepository = appRefreshTokenRepository;
    }

    @Override
    public AppRefreshToken save(AppRefreshToken dto) {
        AppUser appUser = dto.getAppUser();
        if( appRefreshTokenRepository.findAppRefreshTokenByAppUser(appUser).isPresent()){
            AppRefreshToken persistent = appRefreshTokenRepository.findAppRefreshTokenByAppUser(appUser).get();
            persistent.setRefreshToken(dto.getRefreshToken());
            return appRefreshTokenRepository.save(persistent);
        }

        return appRefreshTokenRepository.save(dto);
    }
}
