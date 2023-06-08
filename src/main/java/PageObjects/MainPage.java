package PageObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    // Кнопка Заказать вверху страницы
    private final By orderButtonUpper = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");

    // Кнопка Заказать внизу страницы
    private final By orderButtonLower = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    // Вопросы о важном
    private final By questions = By.xpath(".//div[@class='accordion__item']");

    // Закрыть куки
    private final By cookie = By.xpath(".//button[@class='App_CookieButton__3cvqF']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void upperButtonClick() {
        driver.findElement(orderButtonUpper).click();
    }

    public void lowerButtonClick() {
        driver.findElement(orderButtonLower).click();
    }

    public void questionClick(int index) {
        driver.findElements(questions).get(index).click();
    }

    public String getAnswer(int index){
        driver.findElement(By.xpath(".//div[@id='accordion__panel-"+index+"']/p")).isDisplayed();
        return driver.findElement(By.xpath(".//div[@id='accordion__panel-"+index+"']/p")).getText();
    }
    public void cookieClick(){
        driver.findElement(cookie).click();
    }


    public void scroll(){
        WebElement element = driver.findElement(questions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitForLoad(int index){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='accordion__panel-"+index+"']/p")));
    }

}

