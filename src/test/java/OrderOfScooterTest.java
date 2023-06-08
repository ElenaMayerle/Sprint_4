import PageObjects.AboutRent;
import PageObjects.Confirmation;
import PageObjects.MainPage;
import PageObjects.PersonalData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;



@RunWith(Parameterized.class)
public class OrderOfScooterTest {
        private final String name;
        private final String surname;
        private final String address;
        private final String station;
        private final String phone;
        private final String date;
        private final String period;
        private final String colors;
        private final String comment;

        public OrderOfScooterTest(String name, String surname, String address, String station, String phone, String date, String period, String colors, String comment) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.station = station;
            this.phone = phone;
            this.date = date;
            this.period = period;
            this.colors = colors;
            this.comment = comment;
        }

        @Parameterized.Parameters
        public static Object[][] getParams() {
            return new Object[][]{
                    {"Иван", "Иванов", "Москва, ул.Ясногорская, д.13", "Ясенево", "79851234567", "20.06.2023", "семеро суток", "", "Текст комментария"},
                    {"Екатерина", "Петрова", "Москва, ул.Чертановская, д.125, кв.29", "Чертановская", "89851234560", "18.06.2023", "сутки", "чёрный жемчуг", ""},
                    {"Ия", "Константинова", "Москва, г.Зеленоград, пом.14", "Митино", "+79851234560", "15.06.2023", "двое суток", "серая безысходность", "Длинный комментарий для менеджера"}
            };

        }


        private final WebDriver driver= new ChromeDriver();
        @Before
        public void init() {
            driver.get("https://qa-scooter.praktikum-services.ru/");
            driver.manage().window().maximize();
        }

        @Test
        public void TestOrderUpper() {
            MainPage mainPage = new MainPage(driver);
            mainPage.upperButtonClick();
            PersonalData personalData = new PersonalData(driver);
            personalData.waitForLoad();
            personalData.setPersonalData(name, surname, address, station, phone);
            AboutRent aboutRent = new AboutRent(driver);
            aboutRent.waitForLoad();
            aboutRent.setAboutRent(date, period, colors, comment);
            Confirmation confirmation = new Confirmation(driver);
            confirmation.waitForLoad();
            confirmation.yesButtonClick();
            confirmation.waitForLoadSuccess();
            Assert.assertTrue("Заказ не создался", confirmation.isOrderSuccess());
        }

        @Test
        public void TestOrderLower() {
        MainPage mainPage = new MainPage(driver);
        mainPage.cookieClick();
        mainPage.scroll();
        mainPage.lowerButtonClick();
        PersonalData personalData = new PersonalData(driver);
        personalData.waitForLoad();
        personalData.setPersonalData(name, surname, address, station, phone);
        AboutRent aboutRent = new AboutRent(driver);
        aboutRent.waitForLoad();
        aboutRent.setAboutRent(date, period, colors, comment);
        Confirmation confirmation = new Confirmation(driver);
        confirmation.waitForLoad();
        confirmation.yesButtonClick();
        confirmation.waitForLoadSuccess();
        Assert.assertTrue("Заказ не создался", confirmation.isOrderSuccess());
    }

        @After
        public void teardown() {
            driver.quit();
        }
    }
