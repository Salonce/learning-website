package salonce.dev.website.account.domain;

public enum Permission {
    // ===== USER/ACCOUNT MANAGEMENT =====
    USER_READ_OWN("user:read:own"),           // View own profile
    USER_UPDATE_OWN("user:update:own"),       // Update own profile
    USER_DELETE_OWN("user:delete:own"),       // Delete own account

    USER_READ_ALL("user:read:all"),           // Admin: view all users
    USER_UPDATE_ANY("user:update:any"),       // Admin: update any user
    USER_DELETE_ANY("user:delete:any"),       // Admin: delete any user
    USER_MANAGE_ROLES("user:manage:roles"),   // Admin: manage user roles

    // ===== COURSE MANAGEMENT =====
    COURSE_READ("course:read"),               // View courses (public)
    COURSE_CREATE("course:create"),           // Editor/Admin: create courses
    COURSE_UPDATE("course:update"),           // Editor/Admin: update courses
    COURSE_DELETE("course:delete"),           // Editor/Admin: delete courses
    COURSE_REORDER("course:reorder"),         // Editor/Admin: reorder courses

    // ===== LESSON MANAGEMENT =====
    LESSON_READ("lesson:read"),               // View lessons (public)
    LESSON_CREATE("lesson:create"),           // Editor/Admin: create lessons
    LESSON_UPDATE("lesson:update"),           // Editor/Admin: update lessons
    LESSON_DELETE("lesson:delete"),           // Editor/Admin: delete lessons
    LESSON_REORDER("lesson:reorder"),         // Editor/Admin: reorder lessons

    // ===== CONTENT BLOCK MANAGEMENT =====
    BLOCK_READ("block:read"),                 // View content blocks (public)
    BLOCK_CREATE("block:create"),             // Editor/Admin: create blocks
    BLOCK_UPDATE("block:update"),             // Editor/Admin: update blocks
    BLOCK_DELETE("block:delete"),             // Editor/Admin: delete blocks

    // ===== ARTICLE MANAGEMENT =====
    ARTICLE_READ("article:read"),             // View articles (public)
    ARTICLE_CREATE("article:create"),         // Editor/Admin: create articles
    ARTICLE_UPDATE("article:update"),         // Editor/Admin: update articles
    ARTICLE_DELETE("article:delete"),         // Editor/Admin: delete articles

    // ===== SYSTEM PERMISSIONS =====
    SYSTEM_SETTINGS("system:settings"),       // Admin: system settings
    SYSTEM_AUDIT("system:audit");             // Admin: view audit logs

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
