import dto.CreateUserRequest;
import dto.LoginRequest;
import dto.UpdateUserRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class UpdateUserTest extends AbstractTest {

    @Test
    @DisplayName("Изменение имени залогированного пользователя")
    @Description("Изменение имени залогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void updateNameLoginUser() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka7")
                .setEmail(getRandomEmail())
                .setPassword("1234");

        String accessToken = createUserAndGetAccessToken(user);

        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setName("qweqwe");
        updateUser(updateUser, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .body("user.name", is("qweqwe"));

        deleteUser(accessToken).then().assertThat()
                .statusCode(202);
    }

    @Test
    @DisplayName("Изменение email залогированного пользователя")
    @Description("Изменение email залогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void updateEmailLoginUser() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka777")
                .setEmail(getRandomEmail())
                .setPassword("1234");

        String accessToken = createUserAndGetAccessToken(user);

        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setEmail(getRandomEmail());
        updateUser(updateUser, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .body("user.email", is(updateUser.getEmail()));

        deleteUser(accessToken).then().assertThat()
                .statusCode(202);
    }

    @Test
    @DisplayName("Изменение пароля залогированного пользователя")
    @Description("Изменение пароля залогированного пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void updatePasswordLoginUser() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka777")
                .setEmail(getRandomEmail())
                .setPassword("1234");

        String accessToken = createUserAndGetAccessToken(user);

        UpdateUserRequest updateUser = new UpdateUserRequest()
                .setPassword("qwerty");
        updateUser(updateUser, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true));

        deleteUser(accessToken).then().assertThat()
                .statusCode(202);
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
