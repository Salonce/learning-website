package salonce.dev.todolist.account.domain;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    MODERATOR("MODERATOR");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
