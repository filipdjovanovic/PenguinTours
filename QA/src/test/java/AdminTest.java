import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class AdminTest
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
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("admin");
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div/form/button"));
        submit.click();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/ul/li[2]/button")));
    }

    @AfterMethod
    public void close()
    {
        driver.quit();
    }

    @Test
    public void testDodajAranzman() //Provera da li radi dodavanje aranzmana.
    {
        //Aranzman
        WebElement naziv = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[1]/input[1]"));
        naziv.sendKeys("Kragujevac");

        WebElement cena = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[1]/input[2]"));
        cena.clear();
        cena.sendKeys("100");

        Select tipPrevoza = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[1]/select")));
        tipPrevoza.selectByValue("Avion");

        WebElement komentar = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[2]/textarea"));
        komentar.sendKeys("No comment");

        //Smestaj
        WebElement nazivSmestaj = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[3]/div[1]/div[2]/input[1]"));
        nazivSmestaj.sendKeys("NazivSmestaj");

        Select kategorija = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[3]/div[1]/div[2]/select[1]")));
        kategorija.selectByValue("1");

        Select tipSmestaja = new Select(driver.findElement(By.xpath("//*[@id=\"type\"]")));
        tipSmestaja.selectByValue("1/2");

        Select internet = new Select(driver.findElement(By.xpath("//*[@id=\"internet\"]")));
        internet.selectByValue("true");
        Select tv = new Select(driver.findElement(By.xpath("//*[@id=\"tv\"]")));
        tv.selectByValue("false");
        Select klima = new Select(driver.findElement(By.xpath("//*[@id=\"ac\"]")));
        klima.selectByValue("true");
        Select frizider = new Select(driver.findElement(By.xpath("//*[@id=\"fridge\"]")));
        frizider.selectByValue("false");
        Select sef = new Select(driver.findElement(By.xpath("//*[@id=\"safe\"]")));
        sef.selectByValue("true");

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); //wait ako ima potrebe
        WebElement dodajSmestaj = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[3]/div[2]/div/button"));
        dodajSmestaj.sendKeys("\n"); //Klik na Unesi smestaj - tu koliko znam treba da se izabere tek posle toga smestaj
        //dodajSmestaj.click();

        //Program

        WebElement datum = driver.findElement(By.xpath("//*[@id=\"date\"]"));
        datum.sendKeys("04-04-2023");

        WebElement gradProgram = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[1]/div/input"));
        gradProgram.sendKeys("Kragujevac");

        WebElement drzavaProgram = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[2]/div/input"));
        drzavaProgram.sendKeys("Srbija");

        Select kontinentProgram = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[3]/div/select")));
        kontinentProgram.selectByValue("Evropa");

        WebElement opis = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[2]/textarea"));
        opis.sendKeys("Opis");

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[4]/div/input")));

        WebElement slikaLokacije = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[4]/div/input"));
        slikaLokacije.sendKeys("images/25.jpg"); //Otvori choose file deo

        WebElement secondResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[5]/div/button")));

        //Umesto .click() na svakom dugmetu koristimo .sendKeys("\n") da ne bi koristili skrol na stranici
        WebElement dodajLokaciju = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[5]/div/button"));
        dodajLokaciju.sendKeys(Keys.ENTER); //Klik na Unesi lokaciju - tu koliko znam treba da se izabere tek posle toga lokacija
        //dodajLokaciju.click();

        WebElement thirdResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[6]/div/button")));

        WebElement dodajProgram = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[4]/div/div[2]/div[1]/div[6]/div/button"));
        dodajProgram.sendKeys(Keys.ENTER); //Klik na Unesi program - tu koliko znam treba da se izabere tek posle toga program
        //dodajProgram.click();

        WebElement fourthResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[5]/div/button")));

        WebElement dodajAranzman = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div[5]/div/button"));
        dodajAranzman.sendKeys(Keys.ENTER);
        //dodajAranzman.click();

        //Posle bi trebalo izlistati sve ponude i pronaci bas ovu specificnu ponudu kako bi proverili da je ubacena na pravi nacin.
        WebElement promenaKartice = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/ul/li[2]/button"));
        promenaKartice.click();

        WebElement pretraga = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        pretraga.sendKeys("Kragujevac");

        WebElement fifthResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/form/div/div/button")));

        WebElement dugme = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div/div/button"));
        dugme.sendKeys(Keys.ENTER);

        WebElement sixthResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/h5")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        //assertEquals(rows.size()-3, 1); // Treba da ostane samo 1 element koji ce da bude pronadjen i smesten u listu Rows

        WebElement nazivPonude = rows.get(3).findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/h5"));
        assertEquals(nazivPonude.getText().split(" ")[0], "Kragujevac"); // Poredimo da li je naziv pronadjene ponude jednak zadatom nazivu.


    }

    @Test
    public void brisanjeAranzmana() //Provera da li radi brisanje aranzmana. PROVERENO
    {
        WebElement promenaKartice = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/ul/li[2]/button"));
        promenaKartice.click();

        WebElement pretraga = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        pretraga.sendKeys("Beograd");

        List<WebElement> rows = driver.findElements(By.className("my-2"));

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div/div/button")));

        WebElement dugme = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/form/div/div/button"));
        dugme.sendKeys(Keys.ENTER);

        WebElement secondResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/button")));

        WebElement obrisi = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/button"));
        obrisi.click();

        promenaKartice.click();
        List<WebElement> rows1 = driver.findElements(By.className("my-2"));
        assertTrue(rows1.size() < rows.size());
    }

    @Test (enabled=false)
    public void azuriranjeAranzmana() //Provera da li radi azuriranje. Nije provereno...
    {
        //Uraditi...
    }
}