import pageobjects.AboutRent;
import pageobjects.Confirmation;
import pageobjects.MainPage;
import pageobjects.PersonalData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class OrderOfScooterTest extends BeforeAndAfter{
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


        @Test
        public void testOrderUpper() {
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
        public void testOrderLower() {
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

    }
