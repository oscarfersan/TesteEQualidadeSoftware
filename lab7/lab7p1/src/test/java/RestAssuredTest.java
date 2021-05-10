import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class RestAssuredTest {
    @Test
    public void testStatusToDo() {
        assertEquals(given().when().get("https://jsonplaceholder.typicode.com/todos").getStatusCode(), 200);
    }

    @Test
    public void test2() {
        String expected = "et porro tempora";
        given().get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200).
                body("title[3]", equalTo(expected));
    }
    @Test
    public void test3(){
        given().get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200).body("id[197]",equalTo(198))
        .body("id[198]",equalTo(199));
    }
}