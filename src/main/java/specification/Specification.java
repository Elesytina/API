package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author Сытина Елена
 */
public class  Specification {
    /**
     * Метод определяет спецификацию запроса
     *
     * @return объект класса RequestSpecification
     */
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
    }

    /**
     * Метод определяет спецификацию ответа
     *
     * @return объект класса ResponseSpecification
     */
    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    /**
     * Метод устанавливает спецификацию запроса
     *
     * @param requestSpec передаёт ссылку на объект RequestSpecification
     */
    public static void installSpec(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    /**
     * Метод устанавливает спецификацию ответа
     *
     * @param responseSpec передаёт ссылку на объект ResponseSpecification
     */

    public static void installSpec(ResponseSpecification responseSpec) {
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Метод устанавливает спецификации запроса и ответа
     *
     * @param requestSpec  передаёт ссылку на объект RequestSpecification
     * @param responseSpec передаёт ссылку на объект ResponseSpecification
     */
    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Метод удаляет установленные спецификации запроса и ответа
     */
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}



