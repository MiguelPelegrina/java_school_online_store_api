package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("auth/login")
@RestController
public class AuthController {
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @ResponseBody
    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequestBodyDTO loginRequestBodyDto)  {

        try {
            // TODO Do it with authentication
            /*Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestBodyDto.getEmail(), loginRequestBodyDto.getPassword()
                    )
            );
            /*String email = authentication.getName();
            AuthResultDTO user = new AuthResultDTO();
            String token = jwtUtil.createToken(user);
            AuthResultDTO loginRes = new AuthResultDTO(token);*/

            String email = loginRequestBodyDto.getEmail();
            UserEntity user = userRepository.findUserByEmail(email).orElseThrow();

            // Check the password
            if(!passwordEncoder.matches(loginRequestBodyDto.getPassword(), user.getPassword())){
                throw new RuntimeException("User password does not match");
            }

            String token = jwtUtil.createToken(user);
            AuthResultDTO resultDto = new AuthResultDTO();
            resultDto.setAccessToken(token);

            return ResponseEntity.ok(resultDto);

        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /*public ResponseEntity<AuthResultDTO> createSecurityToken(LoginRequestBodyDTO loginRequestBodyDto){
        // Find the user
        String email = loginRequestBodyDto.getEmail();
        UserEntity user = userRepository.findUserByEmail(email).orElseThrow();

        // Check the password
        if(!passwordEncoder.matches(loginRequestBodyDto.getPassword(), user.getPassword())){
            throw new RuntimeException("User password does not match");
        }

        /* Get the roles
        List<UserRoleEntity> userRoles = userRoleRepository.findByUser(user).orElseThrow();
        List<RoleEntity> roles = userRoles.stream().map(UserRoleEntity::getRole).toList();*/

        /*String token = tokenManager.createTokenByEmail(user.getEmail());
        AuthResultDTO resultDto = new AuthResultDTO();
        resultDto.setAccessToken(token);

        return ResponseEntity.ok(resultDto);
    }*/
}
