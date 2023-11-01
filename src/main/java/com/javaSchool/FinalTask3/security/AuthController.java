package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * The {@code AuthController} class is responsible for handling user authentication via a login endpoint.
 * It processes login requests, validates user credentials, and generates JWT tokens upon successful authentication.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("auth/login")
@RestController
public class AuthController {
    // Fields
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    /**
     * Processes user login by validating the provided credentials, and if successful, generates and returns a JWT token.
     * @param loginRequestBodyDto The request body containing the user's email and password.
     * @return A ResponseEntity containing the JWT access token upon successful login, or an error response in case of failure.
     */
    @ResponseBody
    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequestBodyDTO loginRequestBodyDto)  {
        try {
            // Get the user
            String email = loginRequestBodyDto.getEmail();
            UserEntity user = userRepository.findUserByEmail(email).orElseThrow();

            if(!user.isActive()){
                throw new RuntimeException("User is not active");
            }

            // Check the password
            if(!passwordEncoder.matches(loginRequestBodyDto.getPassword(), user.getPassword())){
                throw new RuntimeException("User password does not match");
            }

            // Generate the token
            String token = jwtUtil.createToken(user);
            AuthResultDTO resultDto = new AuthResultDTO();
            resultDto.setAccessToken(token);

            return ResponseEntity.ok(resultDto);
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
