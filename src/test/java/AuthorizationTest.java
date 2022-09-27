import dto.CreateUserRequest;
import dto.LoginRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class AuthorizationTest extends AbstractTest{
    private CreateUserRequest user;
    @Before
    public void creteUser() {
        user = new CreateUserRequest()
                .setName("koshka6")
                .setEmail(getRandomEmail())
                .setPassword("1234");
        createAndLogoutUser(user);
    }

    @After
    public void deleteUser() {
        LoginRequest login = new LoginRequest()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
        loginAndDeleteUser(login);
    }

    @Test
    @DisplayName("Авторизация пользователя")
    @Description("Пользователь может авторизоваться\n" +
            "для авторизации нужно передать все обязательные поля\n" +
            "успешный запрос возвращает id")
    public void userAuthorisationTest(){
    }

    @Test
    @DisplayName("Авторизация пользователя c невалидным email")
    @Description("система вернёт ошибку, если неправильно указать email")
    public  void userAuthorisationInvalidEmailTest(){
        LoginRequest login = new LoginRequest()
                .setEmail("1111" + user.getEmail())
                .setPassword(user.getPassword());
       loginUser(login)
                .then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Авторизация пользователя c невалидным Password")
    @Description("система вернёт ошибку, если неправильно указать Password")
    public  void userAuthorisationInvalidPasswordTest(){
        LoginRequest login = new LoginRequest()
                .setEmail(user.getEmail())
                .setPassword("1111" + user.getPassword());
        loginUser(login)
                .then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));
    }

    private void createAndLogoutUser(CreateUserRequest user) {
        Response response = createUser(user);

        String refreshToken = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("refreshToken");

        logoutUser(refreshToken);
    }

    private void loginAndDeleteUser(LoginRequest login) {
        String accessToken = loginUser(login)
                .then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");

        deleteUser(accessToken).then().assertThat()
                .statusCode(202);
    }

}
