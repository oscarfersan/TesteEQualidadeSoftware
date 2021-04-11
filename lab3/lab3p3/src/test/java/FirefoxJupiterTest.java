import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumJupiter.class)
class FirefoxJupiterTest extends Object {
    @Test
    void testWithOneFirefox(FirefoxDriver driver) {
        driver.get("https://www.youtube.com/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    void testWithTwoFirefoxs(FirefoxDriver driver1,
                             FirefoxDriver driver2) {
        driver1.get("http://www.google.es/");
        driver2.get("https://www.netflix.com/pt-en/");
        assertThat(driver1.getTitle(), startsWith("Google"));
        assertThat(driver2.getTitle(), equalTo("Netflix Portugal - Watch TV Shows Online, Watch Movies Online"));
    }
    /*@BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }*/
}