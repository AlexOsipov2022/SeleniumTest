import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumWebFormAlertsTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
    }

    //    @AfterMethod
//    protected void quitDriver() {
//        driver.quit();
//    }
//
//    void goAlerts() {
//        driver.findElement(By.linkText("alerts."));
//    }

    @Test
    public void testAlerts() {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();
        final Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals("cheese", alertText);
    }
}
