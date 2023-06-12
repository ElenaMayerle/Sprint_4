package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Confirmation {
    private final WebDriver driver;

    // Название страницы
    private final By header =  By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    // Кнопка Да
    private final By yesButton =  By.xpath(".//button[text()='Да']");

    // Заказ оформлен
    private final By success =  By.xpath(".//div[text()='Заказ оформлен']");

    public Confirmation(WebDriver driver) {
        this.driver = driver;
    }
    public void yesButtonClick(){
        driver.findElement(yesButton).click();
    }

    public boolean isOrderSuccess(){
        return driver.findElement(success).isDisplayed();
    }
    public void waitForLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    public void waitForLoadSuccess(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(success));
    }
}
