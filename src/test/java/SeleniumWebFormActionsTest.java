import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumWebFormActionsTest {

        private Actions actions;

//    // проверяет есть ли акшионс, чтобы было только одно акшионс,
//    если нету, то возвращает актионс//

        private Actions getActions() {
            if (actions == null) {
                actions = new Actions(driver);
            }
            return actions;
        }

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
        public void testSliderActions() {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            final WebElement slider = driver.findElement(By.className("form-range"));

            getActions()
                    .clickAndHold(slider)                    //Actions
                    .moveByOffset(-200, 0)    //Actions сдвинули мышку по координатам от схваченной мышки
                    .release()                               //Actions отпустить мышку
                    .perform();                              //void - Actions не выполняются, пока не укажим Perform

            Assert.assertEquals("0", slider.getAttribute("value"));
        }

        @Test
        public void testSliderClick() {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            final WebElement slider = driver.findElement(By.className("form-range"));

            final Dimension size = slider.getSize();
            int sliderWidth = size.getWidth();

            getActions()
                    .moveToElement(slider)                                  //мышка передвинулась на середину элемента(всегда)
                    .moveByOffset(sliderWidth * 2/10, 0)     //сдвинули мышку вправо на 2/10 ширины
                    .click()
                    .perform();

            Assert.assertEquals("7", slider.getAttribute("value"));
        }
        @Test
        public void testKeyActions() {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            final WebElement slider = driver.findElement(By.className("form-range"));

            WebElement textArea = driver.findElement(By.name("my-textarea"));

            getActions()
                    .click(textArea)
                    .keyDown(Keys.SHIFT)          //Нажали клавишу и не отпускаем
                    .sendKeys("abCDe")
                    .keyUp(Keys.SHIFT)
                    .perform();

            Assert.assertEquals("ABCDE", textArea.getAttribute("value"));
        }
        @Test
        public void testDragAndDrop() {
            driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");
            final WebElement test1 = driver.findElement(By.id("test1"));
            getActions()
                    .clickAndHold(test1)
                    .moveByOffset(100, 50)
                    .pause(1000)          //Нажали клавишу и не отпускаем
                    .moveByOffset(100, 50)
                    .release()
                    .perform();
        }
        @Test
        public void testDragAndDropBy() {
            driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");
            final WebElement test2 = driver.findElement(By.id("test2"));
            getActions()
                    .dragAndDropBy(test2,500, 500)
                    .perform();
        }
        @Test
        public void testDragAndDropByElement() {
            driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");
            final WebElement test1 = driver.findElement(By.id("test1"));
            final WebElement test3 = driver.findElement(By.id("test3"));
            getActions()
                    .dragAndDrop(test1,test3)
                    .perform();

            Assert.assertEquals(test1.getLocation(), test3.getLocation());
        }

    }
}
