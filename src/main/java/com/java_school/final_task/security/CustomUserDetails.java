package com.java_school.final_task.security;

import com.java_school.final_task.domain.userRole.UserRoleEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * The {@code CustomUserDetails} class represents a custom implementation of the Spring Security {@link UserDetails}
 * interface. It holds user-related information required for authentication and authorization.
 */
@Data
public class CustomUserDetails implements UserDetails {
    // Fields
    private int id;
    private String email;
    private String password;
    private Set<UserRoleEntity> roles;

    /**
     * Returns a list of authorities (roles) associated with the user.
     * @return List with the user's roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(UserRoleEntity role : roles){
            // Prefixes role names with "ROLE_" as per Spring Security conventions.
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().getName()));
        }

        return authorities;
    }

    /**
     * Returns the user's password.
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user's email address, which is used as the unique username.
     * @return The user's email address.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Checks whether the user's account has expired.
     * @return true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks whether the user's account is locked.
     * @return true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks whether the user's credentials (e.g., password) have expired.
     * @return true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks whether the user is enabled or disabled.
     * @return true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
