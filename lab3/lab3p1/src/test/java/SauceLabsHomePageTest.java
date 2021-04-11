import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.firefox.GeckoDriverService;
class SauceLabsHomePageTest extends Object {
    WebDriver browser;
    @BeforeEach
    public void setUp(){
        System. setProperty("webdriver.gecko.driver","C:/WebDriver/bin/geckodriver.exe");
        browser = new FirefoxDriver();
    }
    @AfterEach
    public void tearDown(){
        browser.close();
    }
    @Test
    public void site_header_is_on_home_page(){
        /*System. setProperty("webdriver.gecko.driver","C:/WebDriver/bin/geckodriver.exe");
        //meter firefox options en setup browser.setup.homepage
        WebDriver browser = new FirefoxDriver();
        //*/
        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By. xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));
        //
        //teardown
        //browser.close();
    }
}