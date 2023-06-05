package graphql;

import dataForGraphQl.GraphQLQuery;
import dataForGraphQl.Movie;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestGraphQL {

    @Test
    public void loginTest() {
        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("query ($id: ID) { movie(id: $id) { name } }");

        Movie id = new Movie();
        id.setId("60d6aab20db4dabb830b403f");

        query.setVariables(id);

        given()
                .contentType(ContentType.JSON)
                .body(query)
                .when().post("http://localhost:3005/graphql/")
                .then().assertThat()
                .body("data.movie.name", equalTo("Pulp Fiction"));

    }

    @Test
    public void testGraphql() {
        RestAssured.baseURI = "https://www.predic8.de/fruit-shop-graphql?";
        String query = "query{products(id:\"7\") { name price category {name} vendor {name id}}}";

        String jsonString = graphqlToJson(query);
        given()
                .log()
                .all()
                .contentType("application/json")
                .body(jsonString)
                .when().log().all().post().then().log().all().assertThat().statusLine("HTTP/1.1 200 OK");

    }

    private static String graphqlToJson(String payload) {
        JSONObject json = new JSONObject();
        json.put("query", payload);
        return json.toString();
    }
}
