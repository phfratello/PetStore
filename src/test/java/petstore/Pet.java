package petstore;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet";  // endereço da entidade Pet

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //incluir - Create - Post
    @Test  //Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintase Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given()  // Dado
                .contentType("application/json")// comum em API REST;   antigas era "text/xml"
                .log().all()
                .body(jsonBody)
                .when()  // Quando
                .post(uri)
                .then() // Então
                .log().all()
                .statusCode(200)
                .body("name", is("CaradRuim"))
                .body("status", is("available"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))
        ;
    }
}