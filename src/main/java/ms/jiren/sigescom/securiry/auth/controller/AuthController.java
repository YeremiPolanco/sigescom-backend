package ms.jiren.sigescom.securiry.auth.controller;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.securiry.auth.dto.AuthResponse;
import ms.jiren.sigescom.securiry.auth.dto.LoginRequest;
import ms.jiren.sigescom.securiry.auth.dto.RegisterRequest;
import ms.jiren.sigescom.securiry.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Permite el acceso desde tu frontend
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
