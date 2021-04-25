import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlazeDemoSteps {
    private WebDriver webDriver;

    @When("I go to {string}")
    public void goToBlaze(String url) {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(url);
    }

    @When("I choose {string} as departure")
    public void selectDeparture(String departure) {
        webDriver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = webDriver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = '" + departure + "']")).click();
        }
    }

    @When("I choose {string} as destination")
    public void selectDestination(String destination) {
        webDriver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = webDriver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = '" + destination + "']")).click();
        }
    }

    @When("I find flights")
    public void findFlights() {
        //webDriver.findElement(By.xpath("//input[@value='Choose This Flight']")).click();
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @When("I choose first flight")
    public void chooseFlight() {
        webDriver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    }
    @When("I purchase flight")
    public void purchaseFlight(){
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }
    @When("I should be shown flight")
    public void assertResult(){
        assertEquals(webDriver.getTitle(), ("BlazeDemo Confirmation"));
    }
}
