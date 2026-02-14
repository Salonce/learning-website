package salonce.dev.website.account.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(
            "USER",
            Set.of(
                    // Own account
                    Permission.USER_READ_OWN,
                    Permission.USER_UPDATE_OWN,

                    // Public reading
                    Permission.COURSE_READ,
                    Permission.LESSON_READ,
                    Permission.BLOCK_READ,
                    Permission.ARTICLE_READ
            )
    ),

    EDITOR(
            "EDITOR",
            Set.of(
                    // Own account
                    Permission.USER_READ_OWN,
                    Permission.USER_UPDATE_OWN,

                    // Course management
                    Permission.COURSE_READ,
                    Permission.COURSE_CREATE,
                    Permission.COURSE_UPDATE,
                    Permission.COURSE_DELETE,
                    Permission.COURSE_REORDER,

                    // Lesson management
                    Permission.LESSON_READ,
                    Permission.LESSON_CREATE,
                    Permission.LESSON_UPDATE,
                    Permission.LESSON_DELETE,
                    Permission.LESSON_REORDER,

                    // Block management
                    Permission.BLOCK_READ,
                    Permission.BLOCK_CREATE,
                    Permission.BLOCK_UPDATE,
                    Permission.BLOCK_DELETE,

                    // Article management
                    Permission.ARTICLE_READ,
                    Permission.ARTICLE_CREATE,
                    Permission.ARTICLE_UPDATE,
                    Permission.ARTICLE_DELETE
            )
    ),

    ADMIN(
            "ADMIN",
            Set.of(
                    // Own account
                    Permission.USER_READ_OWN,
                    Permission.USER_UPDATE_OWN,
                    Permission.USER_DELETE_OWN,

                    // User management (admin powers)
                    Permission.USER_READ_ALL,
                    Permission.USER_UPDATE_ANY,
                    Permission.USER_DELETE_ANY,
                    Permission.USER_MANAGE_ROLES,

                    // Course management (all editor permissions)
                    Permission.COURSE_READ,
                    Permission.COURSE_CREATE,
                    Permission.COURSE_UPDATE,
                    Permission.COURSE_DELETE,
                    Permission.COURSE_REORDER,

                    // Lesson management
                    Permission.LESSON_READ,
                    Permission.LESSON_CREATE,
                    Permission.LESSON_UPDATE,
                    Permission.LESSON_DELETE,
                    Permission.LESSON_REORDER,

                    // Block management
                    Permission.BLOCK_READ,
                    Permission.BLOCK_CREATE,
                    Permission.BLOCK_UPDATE,
                    Permission.BLOCK_DELETE,

                    // Article management
                    Permission.ARTICLE_READ,
                    Permission.ARTICLE_CREATE,
                    Permission.ARTICLE_UPDATE,
                    Permission.ARTICLE_DELETE,

                    // System management
                    Permission.SYSTEM_SETTINGS,
                    Permission.SYSTEM_AUDIT
            )
    );

    private final String name;
    private final Set<Permission> permissions;

    Role(String name, Set<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        // Add the role itself as an authority (for @PreAuthorize("hasRole('ADMIN')"))
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}