import dto.CreateUserRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class CreateUserTest extends AbstractTest {

    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Создание уникального пользователя\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void createUserUniqueTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka3")
                .setEmail(getRandomEmail())
                .setPassword("1234");
        Response response = createUser(user);

        String token = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");

        deleteUser(token).then().assertThat()
                .statusCode(202);
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    @Description("Создание пользователя, который уже зарегистрирован\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void CreateDuplicateUserTest(){
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka4")
                .setEmail(getRandomEmail())
                .setPassword("1234");
        Response response = createUser(user);

        String token = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");

        response = createUser(user);
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));

        deleteUser(token).then().assertThat()
                .statusCode(202);
    }

    @Test
    @DisplayName("Создание пользователя без обязательного полся name")
    @Description("Попытка создания пользователя без обязатльного поля name\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void requiredFieldNameTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setEmail(getRandomEmail())
                .setPassword("1234");
        Response response = createUser(user);

        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного полся email")
    @Description("Попытка создания пользователя без обязатльного поля email\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void requiredFieldEmailTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka4")
                .setPassword("1234");
        Response response = createUser(user);

        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного полся password")
    @Description("Попытка создания пользователя без обязатльного поля password\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void requiredFieldPasswordTest() {
        CreateUserRequest user = new CreateUserRequest()
                .setName("koshka4")
                .setEmail(getRandomEmail());
        Response response = createUser(user);

        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", is(false));
    }
}


