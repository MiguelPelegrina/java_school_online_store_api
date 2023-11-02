package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import com.javaSchool.FinalTask3.domain.user.UserRepository;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleRepository;
import com.javaSchool.FinalTask3.security.dto.AuthResultDTO;
import com.javaSchool.FinalTask3.security.dto.LoginRequestBodyDTO;
import com.javaSchool.FinalTask3.security.dto.RegisterRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@code AuthController} class is responsible for handling user authentication via a login endpoint.
 * It processes login requests, validates user credentials, and generates JWT tokens upon successful authentication.
 */
@RequiredArgsConstructor
@RequestMapping("auth")
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
    @RequestMapping("/login")
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
                ResponseEntity.status(401).body("User password does not match");
            }

            return ResponseEntity.ok(this.generateAuthResultDTO(user));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity register(@RequestBody RegisterRequestBodyDTO registerRequestBodyDTO){
        try{
            // Get the user
            final String email = registerRequestBodyDTO.getEmail();
            final Optional<UserEntity> userInRepository = userRepository.findUserByEmail(email);

            if(userInRepository.isPresent()){
                ResponseEntity.status(401).body("A user with that email already exists");
            }

            UserEntity newUser = UserEntity.builder()
                    .dateOfBirth(registerRequestBodyDTO.getDateOfBirth())
                    .email(registerRequestBodyDTO.getEmail())
                    .isActive(true)
                    .name(registerRequestBodyDTO.getName())
                    .password(passwordEncoder.encode(registerRequestBodyDTO.getPassword()))
                    .surname(registerRequestBodyDTO.getSurname())
                    .phoneNumber(registerRequestBodyDTO.getPhone())
                    .roles(new HashSet<>())
                    .build();

            UserRoleEntity newUserRole = UserRoleEntity.builder()
                    .assignedDate(LocalDate.now())
                    .user(newUser)
                    .role(new RoleEntity("CLIENT"))
                    .build();

            newUser.getRoles().add(newUserRole);

            userRepository.save(newUser);

            return ResponseEntity.ok(this.generateAuthResultDTO(newUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private AuthResultDTO generateAuthResultDTO(UserEntity user){
        String token = jwtUtil.createToken(user);
        AuthResultDTO resultDto = new AuthResultDTO();
        resultDto.setAccessToken(token);
        resultDto.setId(user.getId());
        resultDto.setRole(user.getRoles().stream().map(userRole -> userRole.getRole().getName()).collect(Collectors.toList()));

        return resultDto;
    }
}
