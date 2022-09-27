import dto.CreateUserRequest;
import dto.UpdateUserRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class UpdateUserTest extends AbstractTestWithUser {

    @Test
    @DisplayName("Изменение имени залогированного пользователя")
    @Description("Изменение имени залогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void updateNameLoginUser() {
        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setName("qweqwe");
        updateUser(updateUser, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .body("user.name", is("qweqwe"));
    }

    @Test
    @DisplayName("Изменение email залогированного пользователя")
    @Description("Изменение email залогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void updateEmailLoginUser() {
        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setEmail(getRandomEmail());
        updateUser(updateUser, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .body("user.email", is(updateUser.getEmail()));
    }

    @Test
    @DisplayName("Изменение пароля залогированного пользователя")
    @Description("Изменение пароля залогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void updatePasswordLoginUser() {
        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setPassword("qwerty");
        updateUser(updateUser, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true));
    }

    @Test
    @DisplayName("Изменение имени незалогированного пользователя")
    @Description("Изменение имени незалогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void updateNameNoLoginUser() {
        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setName("qweqwe");
        updateUser(updateUser, null).then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Изменение email незалогированного пользователя")
    @Description("Изменение email незалогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void updateEmailNoLoginUser() {
        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setEmail(getRandomEmail());
        updateUser(updateUser, null).then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Изменение пароля незалогированного пользователя")
    @Description("Изменение пароля неалогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void updatePasswordNoLoginUser() {
        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setPassword("qwerty");
        updateUser(updateUser, null).then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));
    }
}
