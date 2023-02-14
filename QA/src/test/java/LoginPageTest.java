import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class LoginPageTest
{
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod()
    public void beforeMethod()
    {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.navigate().to("http://localhost:3000/login");
    }

    @AfterMethod
    public void close()
    {
        driver.quit();
    }
    @Test
    public void testLoginStaff()//PROVERENO
    {
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys("luka");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("staff");

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div/form/button"));
        submit.click();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //wait ako ima potrebe
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/ul/li[2]/button")));
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/staff");
    }

    @Test
    public void testLoginAdmin()//PROVERENO
    {
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("admin");

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div/form/button"));
        submit.click();

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/ul/li[2]/button")));
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/admin");
    }
}