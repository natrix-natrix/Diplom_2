package dto;

public class CreateUserRequest {
    private String name;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public CreateUserRequest setName(String login) {
        this.name = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
