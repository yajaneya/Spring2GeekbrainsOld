package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.JwtRequest;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.JwtResponse;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.User;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.AppError;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.UserService;
import ru.yajaneya.SpringFM1GeekbrainsDz7.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> authorization(@RequestBody JwtRequest authRequest) {
        return createAuthToken(authRequest, "auth");
    }

    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody JwtRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        userService.save(user); //TODO сделать проверку на наличие такого пользователя в базе и отправку инфы об ошибке
        return createAuthToken(authRequest, "reg");
    }


    private ResponseEntity<?> createAuthToken(JwtRequest authRequest, String m) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError
                    ("Incorrect user or password", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
