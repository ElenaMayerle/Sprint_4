package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalData {
    private final WebDriver driver;

    // Название страницы
    private final By header =  By.xpath(".//div[text()='Для кого самокат']");
    // Имя
    private final By nameOfPerson=By.xpath(".//input[@placeholder='* Имя']");
    // Фамилия
    private final By surnameOfPerson=By.xpath(".//input[@placeholder='* Фамилия']");
    // Адрес
    private final By addressOfPerson=By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Станция метро
    private final By stationOfPerson=By.xpath(".//input[@placeholder='* Станция метро']");
    // Список станций
    private final By stations=By.xpath(".//div[@class='select-search__select']");
    // Телефон
    private final By phoneOfPerson=By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private final By nextButton=By.xpath(".//button[text()='Далее']");

    public PersonalData(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name){
         driver.findElement(nameOfPerson).sendKeys(name);
    }
    public void setSurname(String surname){
        driver.findElement(surnameOfPerson).sendKeys(surname);
    }
    public void setAddress(String address){
        driver.findElement(addressOfPerson).sendKeys(address);
    }
    public void setPhone(String phone){
        driver.findElement(phoneOfPerson).sendKeys(phone);
    }
    public void setStation(String station){
        driver.findElement(stationOfPerson).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(stations));
        WebElement getStation = driver.findElement(By.xpath(".//div[text()='"+station+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getStation);
        getStation.click();
    }

    public void nextButtonClick(){
        driver.findElement(nextButton).click();
    }
    public void waitForLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    public void setPersonalData(String name, String surname, String address, String station, String phone){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setStation(station);
        setPhone(phone);
        nextButtonClick();
    }


}
