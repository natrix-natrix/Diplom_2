import dto.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public abstract class AbstractTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    protected <T> Response postRequest(T body, String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    protected Response deleteRequest(String url, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(url);
    }

    protected Response patchRequest(UpdateUserRequest user, String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .and()
                .body(user)
                .when()
                .patch(url);
    }

    protected Response createUser(CreateUserRequest user){
        return postRequest(user, null,  "/auth/register");
    }

    protected Response loginUser(LoginRequest user){
        return postRequest(user, null, "/auth/login");
    }

    protected Response deleteUser(String token) {
        return deleteRequest( "/auth/user", token);
    }

    protected Response logoutUser(String refreshToken){
        return postRequest(new LogoutRequest(refreshToken), null, "/auth/logout");
    }

    protected Response updateUser(UpdateUserRequest user, String token){
        return patchRequest(user, token, "/auth/user");
    }

    protected String createUserAndGetAccessToken(CreateUserRequest user) {
        Response response = createUser(user);

        String accessToken = response.then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");
        return accessToken;
    }

    protected Response createOrder(CreateOrderRequest order, String token){
        return postRequest(order, token, "/orders");
    }

    protected String getRandomEmail() {
        return UUID.randomUUID() + "@gmail.com";
    }

    protected Response getOrder(String token){
        return getRequest(token,"/orders");
    }

    protected Response getRequest(String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .when()
                .get(url);
            }
}
