package in.reqres;

import data.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static specification.Specification.*;

/**
 * @author Сытина Елена
 */

public class TestsApi {

    /**
     * Тестовый метод для задания 2.1 получает список пользователей со 2-й страницы
     * Убеждается, что имена файлов аватаров пользователей уникальны
     *
     * @param baseUrl параметр String для отделения базового URL-адреса и получения имени файла
     */

    @Test(dataProvider = "baseUrl")
    public void getUsersListAndCheckUniqueFilesTest(String baseUrl) {
        installSpec(requestSpec(), responseSpec());
        Resource resource = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().body()
                .extract().as(Resource.class);

        List<String> files = resource.getData().stream()
                .map(x -> x.getAvatar().replace(baseUrl, ""))
                .collect(Collectors.toList());
        System.out.println(files);
        List<String> distinctFiles = files.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctFiles);
        deleteSpec();
        Assert.assertEquals(files.size(), distinctFiles.size(), "Имена файлов аватаров пользователей " +
                "не уникальны");
    }

    /**
     * Тестовый метод для задания 2.2. Проверка на успешный логин
     *
     * @param email    параметр типа String передаёт email-адрес
     * @param password параметр типа String передаёт пароль
     * @param token    параметр типа String передаёт токен
     */
    @Test(dataProvider = "dataForSuccessLogin")
    public void loginSuccessTest(String email, String password, String token) {
        DataAuthorisation data = new DataAuthorisation(email,
                password);
        installSpec(requestSpec(), responseSpec());
        Token tokenResponse = given()
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .log().body()
                .extract().response()
                .as(Token.class);
        deleteSpec();
        Assert.assertEquals(tokenResponse.getToken(), token, "Ожидали токен " + token +
                "получили токен " + tokenResponse.getToken());
    }

    /**
     * Задание 2.2 Проверка на логин с ошибкой из-за не ведённого пароля
     *
     * @param mail тип String для передачи в метод значения email-адреса
     */
    @Test(dataProvider = "dataForUnsuccessfulLogin")
    public void loginUnsuccessfulTest(String mail, String error) {
        EmailData emailData = new EmailData(mail);
        ErrorData errorData = given()
                .contentType("application/json")
                .body(emailData)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().body()
                .statusCode(400)
                .extract().response()
                .as(ErrorData.class);
        Assert.assertEquals(errorData.getError(), error, "Ожидали получить ошибку: " + error + ", получили " + errorData.getError());

    }

    /**
     * Тестовый метод для задания 2.3 убеждается, что операция LIST/<Resource /> возвращает данные, отсортированные по годам
     */
    @Test
    public void listResourcesSortedTest() {
        installSpec(requestSpec(), responseSpec());
        ResourceColor resource = given()
                .get("/api/unknown")
                .then()
                .log().body()
                .extract().as(ResourceColor.class);
        List<Integer> list = resource.getData().stream().map(DataColor::getYear).collect(Collectors.toList());
        List<Integer> listSorted = list.stream().sorted().collect(Collectors.toList());
        deleteSpec();
        System.out.println(list);
        Assert.assertEquals(list, listSorted, " Данные не отсортированы по годам: " + list);
    }

    /**
     * DataProvider для теста {@link #loginSuccessTest(String, String, String)}
     *
     * @return объект типа Object[][]
     */
    @DataProvider(name = "dataForSuccessLogin")
    public Object[][] getDataForSuccessLogin() {
        return new Object[][]{
                {"eve.holt@reqres.in", "cityslicka", "QpwL5tke4Pnpja7X4"},
        };
    }
    /**
     * DataProvider для теста {@link #loginUnsuccessfulTest(String, String)}
     *
     * @return объект типа Object[][]
     */

    @DataProvider(name = "dataForUnsuccessfulLogin")
    public Object[][] getDataForUnsuccessfulLogin() {
        return new Object[][]{{"peter@klaven", "Missing password"}};
    }
    /**
     * DataProvider для теста {@link #getUsersListAndCheckUniqueFilesTest(String)}
     *
     * @return объект типа Object[][]
     */

    @DataProvider(name = "baseUrl")
    public Object[][] getBaseUrl() {
        return new Object[][]{{"https://reqres.in/img/faces/"}};
    }

}

