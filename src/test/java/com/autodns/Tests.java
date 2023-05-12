package com.autodns;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

/**
 * @author Сытина Елена
 */
public class Tests {
    /**
     * Тестовый метод для задания 2.4 убеждается, что число тегов в ответе равно number
     *
     * @param number передаёт в метод ожидаемое число тегов
     */
    @Test(dataProvider = "number")
    public void checkCountOfTagsTest(int number) {
        Response r = given()
                .when()
                .get("https://gateway.autodns.com")
                .then().contentType("text/xml")
                .log().body()
                .extract()
                .response();
        String[] mas = r.htmlPath().prettify().split("\n");
        int size = (int) Arrays.stream(mas).filter(x -> x.contains("<tag")).count();
        Assert.assertEquals(size, number, "Число тегов не соответствует заданному: " +
                " нужно - " + number + " получили - " + size);

    }

    /**
     * DataProvider для теста {@link #checkCountOfTagsTest(int)}
     *
     * @return объект типа Object[][]
     */
    @DataProvider(name = "number")
    public Object[][] getNumber() {
        return new Object[][]{{14}};
    }
}
