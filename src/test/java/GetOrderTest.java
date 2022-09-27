import dto.CreateOrderRequest;
import dto.CreateUserRequest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;

public class GetOrderTest  extends AbstractTestWithUser {
    @Test
    @DisplayName("Получение заказов конкретного пользователя, авторизованный пользователь")
    @Description("Получение заказов конкретного пользователя, авторизованный пользователь\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: true")
    public void getOrderLoginUserTest(){
        CreateOrderRequest order = new CreateOrderRequest();

        order.setIngredients(List.of("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa73"));

        createOrder(order, accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true));

        getOrder(accessToken).then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .body("total", is(1));
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя, без авторизованный пользователь")
    @Description("Получение заказов конкретного пользователя, без авторизованный пользователь\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает success: false")
    public void getOrderNotLoginUserTest(){

        getOrder(null).then().assertThat()
                .statusCode(401)
                .and()
                .body("success", is(false));

    }
}
