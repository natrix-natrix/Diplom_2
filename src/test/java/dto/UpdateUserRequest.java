package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequest {
    private String name;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public UpdateUserRequest setName(String login) {
        this.name = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UpdateUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UpdateUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
