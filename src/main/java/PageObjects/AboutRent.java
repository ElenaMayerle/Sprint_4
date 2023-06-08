package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Objects;

public class AboutRent {
    private final WebDriver driver;

    // Название страницы
    private final By header =  By.xpath(".//div[text()='Про аренду']");
    // Когда привезти самокат
    private final By wishedDate =  By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Срок аренды
    private final By rentalPeriod =  By.xpath(".//div[@class='Dropdown-control']");
    // Комментарий
    private final By textOfComment =  By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private final By orderButton=By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");

    public AboutRent(WebDriver driver) {
        this.driver = driver;
    }

    public void setDate(String date){

        driver.findElement(wishedDate).sendKeys(date);
        driver.findElement(header).click();
    }
    public void setPeriod (String period){
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriod));
        WebElement getPeriod = driver.findElement(By.xpath(".//div[text()='"+period+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getPeriod);
        getPeriod.click();
    }
    public void setColor(String color){
        if (!Objects.equals(color, "")) {
            driver.findElement(By.xpath(".//*[text()='" + color + "']")).click();
        }
    }
    public void setComment(String comment){
        driver.findElement(textOfComment).sendKeys(comment);
    }

    public void orderButtonClick(){
        driver.findElement(orderButton).click();
    }
    public void waitForLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    public void setAboutRent(String date, String period, String color, String comment){
        setDate(date);
        setPeriod(period);
        setColor(color);
        setComment(comment);
        orderButtonClick();
    }

}
