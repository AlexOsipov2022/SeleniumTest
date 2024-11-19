import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.awt.SystemColor.text;
import static org.testng.Assert.fail;

public class SeleniumWebFormAlertsTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
    }

    @AfterMethod
    protected void quitDriver() {
        driver.quit();
    }

    @Test
    public void testAlerts() {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();
        final Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals("cheese", alertText);
    }

    @Test
    public void testSlowAlert() {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("slow-alert")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        webDriverWait.until(ExpectedConditions.alertIsPresent());

        final Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals("Slow", alertText);
    }

    @Test
    public void testClicklOnAlert() {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept(); //click on alert (button OK)
        //убеждаемся, что нет Алерта, т.е. эт о код, коглда мы ожидаем ошибку и делаем его чтобы не сломался тест.
        //Если есть ошибка, то мы с fail перескакиваем на catch и ничего не делаем
        try {
            driver.switchTo().alert();
            fail("Expected NoAlertPresentException to be thrown, but Alert is shown");
        } catch (NoAlertPresentException e) {
            //pass;
        }
    }

    @Test
    public void testAcceptAlert() {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        // Ссылка с реальным адресом, т.е. переход неа другую страницу
        // т.е. на клик стоит более сложнгый JS - когда мы соглашаемся с переходом
        //если нажимаем ОК, то функция confirm возвращает TRUE
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals("Heading", text);
    }

    @Test
    public void testDismissAlert() {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("confirm")).click();
        driver.switchTo().alert().dismiss();
        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals("Testing Alerts and Stuff", text);
    }

    //Тест Алерта с вводом данных
    @Test
    public void testAcceptPromt() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("prompt")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = webDriverWait.until(ExpectedConditions.alertIsPresent());
        final String enteredText = "Something text"; // в Selenium этот текст не отображается, но передается
        alert.sendKeys(enteredText);
        alert.accept();
        String actualText = driver.findElement(By.xpath("//div[@id='text']/p")).getText();
        Assert.assertEquals(enteredText, actualText);
    }


//    @Test
//    public void testAcceptAlertFun() {
//        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
//
//        doSomethingWithAlert(
//                alert -> alert.accept(),
//                () -> {
//
//                }
//        );
//
//    }

}
