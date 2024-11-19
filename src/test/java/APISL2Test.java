import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class APISL2Test {
    public static void main(String[] args) {
        // URL для запроса
        String apiUrl = "https://kz-solva-release-300.kz.idfaws.com/secure/rest/sign/in";

        // JSON-тело запроса
        String requestBody = "{\n" +
                "    \"login\": \"admmin\",\n" +
                "    \"password\": \"nimda2012\",\n" +
                "    \"captcha\": \"11111\",\n" +
                "    \"remember\": false\n" +
                "}";

        // Выполнение POST-запроса
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic bW9uZXltYW46MTAwNQ==")
                .header("Cookie", "AuthUser=eyJhbGciOiJIUzUxMiJ9.eyJleHRyYSI6e30sIm1ldGFkYXRhIjp7InVzZXJJZCI6MTAwMDAwMCwicGVybWlzc2lvbnMiOnt9fSwiaWF0IjoxNzMyMDM1MzA1LCJzdWIiOiJhZG1taW4ifQ.cat3VqCC_aspJCjrESqabmFKRmeMr4sSRXYX0dvqkfJZ9thxDXbvO_XG6Iy0yvo_rxFkYzXJQsa6EAHtA_4WIA; JSESSIONID=553ffb047f9a481a45d47038ad0f")
                .body(requestBody)
                .post(apiUrl);

        // Проверка статуса ответа
        response.then().statusCode(200); // Замените ожидаемый статус при необходимости
        System.out.println("Ответ: " + response.getBody().asString());

        // Дополнительно вы можете добавить проверки на содержание ответов
        // Например: response.then().body("key", equalTo("expectedValue"));

        System.out.println("Запрос успешно выполнен.");
    }
}
