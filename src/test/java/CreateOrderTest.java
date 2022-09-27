import dto.CreateOrderRequest;
import dto.CreateUserRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;

public class CreateOrderTest extends AbstractTestWithUser {
    @Test
    @DisplayName("Создание заказа с авторизацией и ингридиентами")
    @Description("Создание заказа с авторизацией  и ингридиентами\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void createOrderLoggedUserIngredientsTest() {
        CreateOrderRequest order = new CreateOrderRequest();

        order.setIngredients(List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa73"));

        createOrder(order, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true));
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и без ингридиентов")
    @Description("Создание заказа с авторизацией и без ингридиентов\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void createOrderLoggedUserNoIngredientsTest() {
        CreateOrderRequest order = new CreateOrderRequest();

        createOrder(order, accessToken).then().assertThat()
                .statusCode(400)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и невалидными ингридиентами")
    @Description("Создание заказа с авторизацией  и невалидными ингридиентами\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void createOrderLoggedUserInvalidIngredientsTest() {
        CreateOrderRequest order = new CreateOrderRequest();

        order.setIngredients(List.of("00c0c5a71d1f82001bdaaa00", "00c0c5a71d1f82001bdaaa00"));

        createOrder(order, accessToken).then().assertThat()
                .statusCode(500)
                .and()
                .body("success", is(false));
    }

    @Test
    @DisplayName("Создание заказа с без авторизации и с ингридиентами")
    @Description("Создание заказа с без авторизации и с ингридиентами\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void createOrderNoLoggedUserIngredientsTest() {
        CreateOrderRequest order = new CreateOrderRequest();

        order.setIngredients(List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa73"));

        createOrder(order, null).then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));
    }
}
