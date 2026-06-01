package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.model.LoginUser;
import praktikum.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER = "/api/auth/register";
    private static final String LOGIN = "/api/auth/login";
    private static final String USER = "/api/auth/user";

    @Step("Создание пользователя через API")
    public ValidatableResponse createUser(User user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post(REGISTER)
                .then();
    }

    @Step("Авторизация пользователя через API")
    public ValidatableResponse loginUser(LoginUser loginUser) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(loginUser)
                .post(LOGIN)
                .then();
    }

    @Step("Удаление пользователя через API")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .auth()
                .oauth2(accessToken.replace("Bearer ", ""))
                .delete(USER)
                .then();
    }
}