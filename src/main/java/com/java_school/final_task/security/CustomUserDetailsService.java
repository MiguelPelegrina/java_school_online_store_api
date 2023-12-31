package com.java_school.final_task.security;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The {@code CustomUserDetailsService} class is a custom implementation of the Spring Security {@link UserDetailsService}
 * interface. It is responsible for loading user details from the application's data source, such as a database, and
 * returning a {@link UserDetails} object that Spring Security uses for authentication and authorization.
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    // Fields
    private final UserRepository userRepository;

    /**
     * Loads user details by their unique email address. This method retrieves user information from the data source,
     * including the user's roles, and returns a {@link UserDetails} object for authentication and authorization.
     * @param email The unique email address for which user details are requested.
     * @return {@link UserDetails} object containing the user's details, including username, password, and roles.
     * @throws UsernameNotFoundException If the specified username is not found in the data source.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(email).orElseThrow();

        List<String> roles = user.getRoles().stream().map(role -> "ROLE_" + role.getRole().getName()).toList();

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}
