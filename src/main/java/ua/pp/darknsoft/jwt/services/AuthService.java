package ua.pp.darknsoft.jwt.services;

import ua.pp.darknsoft.jwt.dto.AuthenticationRequestDTO;
import ua.pp.darknsoft.jwt.dto.AuthenticationResponseDTO;
import ua.pp.darknsoft.jwt.dto.RegistrationResponseDTO;

public interface AuthService {
    AuthenticationResponseDTO registration(RegistrationResponseDTO responseDTO);
    AuthenticationResponseDTO authenticateUser(AuthenticationRequestDTO authenticationRequestDTO);
}
