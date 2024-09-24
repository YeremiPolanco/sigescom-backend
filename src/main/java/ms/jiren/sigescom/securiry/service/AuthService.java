package ms.jiren.sigescom.securiry.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.securiry.jwt.JwtService;
import ms.jiren.sigescom.securiry.repository.UserRepository;
import ms.jiren.sigescom.securiry.repository.user.User;
import ms.jiren.sigescom.securiry.auth.dto.AuthResponse;
import ms.jiren.sigescom.securiry.auth.dto.LoginRequest;
import ms.jiren.sigescom.securiry.auth.dto.RegisterRequest;
import ms.jiren.sigescom.securiry.repository.user.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .country(request.getCountry())
                .roles(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
