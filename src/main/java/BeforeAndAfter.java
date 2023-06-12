import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BeforeAndAfter {
    public WebDriver driver = new ChromeDriver();

    @Before
    public void init() {
        driver.get(Const.SCOOTER_URL);
        driver.manage().window().maximize();
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
