import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumJupiter.class)
class HTMLUnitTest extends Object {
    @Test
    void test(HtmlUnitDriver driver) {
        driver.get("https://www.google.es/");
        assertThat(driver.getTitle(),
                containsString("Google"));
    }
}