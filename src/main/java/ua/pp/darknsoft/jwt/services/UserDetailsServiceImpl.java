package ua.pp.darknsoft.jwt.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.jwt.dto.security.UserDetailsImpl;
import ua.pp.darknsoft.jwt.models.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        AppUser user = new AppUser(1L, "dark", "dark");
        return UserDetailsImpl.build(user);
    }
}
