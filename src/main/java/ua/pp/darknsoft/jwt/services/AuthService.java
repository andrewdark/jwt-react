package ua.pp.darknsoft.jwt.services;

import ua.pp.darknsoft.jwt.dto.AuthenticationRequestDTO;
import ua.pp.darknsoft.jwt.dto.AuthenticationResponseDTO;
import ua.pp.darknsoft.jwt.dto.RegistrationRequestDTO;

public interface AuthService {
    AuthenticationResponseDTO registration(RegistrationRequestDTO responseDTO);
    AuthenticationResponseDTO authenticateUser(AuthenticationRequestDTO authenticationRequestDTO);
    void logout(String refreshToken);
    AuthenticationResponseDTO refresh(String refreshToken);
}
