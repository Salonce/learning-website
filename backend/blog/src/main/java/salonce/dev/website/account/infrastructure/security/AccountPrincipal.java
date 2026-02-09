package salonce.dev.website.account.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import salonce.dev.website.account.domain.Account;
import salonce.dev.website.account.domain.Permission;
import salonce.dev.website.account.domain.Role;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record AccountPrincipal(
        Long id,
        String email,
        String name,  // Add name field
        Set<Role> roles
) implements UserDetails {

    // Constructor for backward compatibility (if needed)
    public AccountPrincipal(Long id, String email, Set<Role> roles) {
        this(id, email, "", roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Combine all permissions from all roles + role authorities
        return roles.stream()
                .flatMap(role -> role.getGrantedAuthorities().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return null; // Not needed for OAuth/JWT
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Convenience methods
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public boolean hasPermission(Permission permission) {
        return roles.stream()
                .anyMatch(role -> role.getPermissions().contains(permission));
    }

    public boolean isAdmin() {
        return hasRole(Role.ADMIN);
    }

    public boolean isEditor() {
        return hasRole(Role.EDITOR);
    }

    // Get all permissions from all roles
    public Set<Permission> getAllPermissions() {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .collect(Collectors.toSet());
    }

    // Factory method to create from Account entity
    public static AccountPrincipal from(Account account) {
        return new AccountPrincipal(
                account.getId(),
                account.getEmail(),
                account.getName(),
                account.getRoles()
        );
    }
}