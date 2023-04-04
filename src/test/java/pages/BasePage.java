package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConstantsClass;

import java.time.Duration;

public class BasePage {

    private static WebDriver driver;

    ConstantsClass constants;
    private static WebDriverWait wait;

    public BasePage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        Duration timeout = Duration.ofSeconds(constants.TIME_OUT_SEC);
        wait = new WebDriverWait(driver, timeout);
        driver.manage().window().maximize();
        driver.get(constants.URL);
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public WebDriverWait getWait(){
        return  this.wait;
    }
}
