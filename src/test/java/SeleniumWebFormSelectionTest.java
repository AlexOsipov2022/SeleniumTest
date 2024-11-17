import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class SeleniumWebFormSelectionTest {

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

    @Test
    public void testDropdownSelect() {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        final WebElement dropdownSelect = driver.findElement(By.className("form-select"));
        Select simpleDropdownSelect = new Select(dropdownSelect);
        simpleDropdownSelect.selectByValue("3");

        String newValue = dropdownSelect.getAttribute("value");

        Assert.assertEquals(newValue, "3");
    }

    @Test
    public void testDefaultcheckbox() {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        final WebElement checkboxDefault = driver.findElement(By.id("my-check-2"));

        // Кликаем на чекбокс, если он еще не выбран
        if (!checkboxDefault.isSelected()) {
            checkboxDefault.click();
        }
        Assert.assertEquals(checkboxDefault.isSelected(), checkboxDefault.isSelected());
    }

    @Test
    public void testCheckedcheckbox() {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        final WebElement checkboxChecked = driver.findElement(By.id("my-check-1"));

        if (checkboxChecked.isSelected()) {
            checkboxChecked.click();
        }
        Assert.assertEquals(!checkboxChecked.isSelected(), !checkboxChecked.isSelected());
    }

    @Test
    public  void testMultipleSelect() {

        driver.get("https://www.selenium.dev/selenium/web/selectPage.html");

        WebElement selectElement = driver.findElement(By.id("selectWithMultipleEqualsMultiple"));
        Select multiSelect = new Select(selectElement);
        multiSelect.selectByIndex(2);
        multiSelect.deselectByIndex(0);
        multiSelect.selectByVisibleText("Cheddar");

        List<String> actualResult = multiSelect.getAllSelectedOptions().stream().map(WebElement::getText).toList();
        List<String> expectedSelectedOptions = List.of("Parmigiano", "Cheddar");

        Assert.assertEquals(actualResult, expectedSelectedOptions);
    }

    @Test
    public  void  testScrollingSelect() {

        driver.get("https://www.selenium.dev/selenium/web/selectPage.html");

        WebElement scrollingSelect = driver.findElement(By.id("selectWithMultipleLongList"));
        Select selectScrollingSelect = new Select(scrollingSelect);
        selectScrollingSelect.selectByVisibleText("five");
        selectScrollingSelect.selectByVisibleText("six");

        List<String> actualResult = selectScrollingSelect.getAllSelectedOptions().stream().map(WebElement::getText).toList();
        List<String> expectedSelectedOptions = List.of("five", "six");

        Assert.assertEquals(actualResult, expectedSelectedOptions);
    }

    @Test
    public void testSliderArrows() {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        final WebElement sliderExampleRange = driver.findElement(By.name("my-range"));

        sliderExampleRange.click();
        sliderExampleRange.sendKeys(Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT);

        String resultPosition = sliderExampleRange.getAttribute("value");

        Assert.assertEquals(resultPosition, "1");
    }
}