import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
import java.util.jar.JarOutputStream;

import static org.testng.Assert.*;

public class HomePageTest
{
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod()
    public void beforeMethod()
    {
        driver = new ChromeDriver();
        driver.navigate().to("http://localhost:3000");
    }

    @AfterMethod
    public void close()
    {
        driver.quit();
    }

    @Test
    public void testPrevoz() //Provera da li su ubaceni svi prevozi u select box. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        Select s = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[2]/div[2]/select"))); //Staviti pravi XPath od naseg select boxa.
        List<WebElement> op = s.getOptions();
        int size = op.size();
        List<String> options = new ArrayList<>();
        for(int i =0; i<size ; i++)
        {
            String option = op.get(i).getText();
            options.add(option);
        }
        List<String> ocekivaniPrevoz = new ArrayList<>();
        ocekivaniPrevoz.add("Autobus");
        ocekivaniPrevoz.add("Avion");
        ocekivaniPrevoz.add("Brod");
        ocekivaniPrevoz.add("Samostalni prevoz");
        ocekivaniPrevoz.add("Voz");
        ocekivaniPrevoz.add("...");

        assertTrue(options.equals(ocekivaniPrevoz)); //Ako equals vrati true prolazi nam test.
        //assertEquals(options, ocekivaniPrevoz);
    }

    @Test
    public void testBrojStavkiPoStrani() //Provera da li je select box lepo napravljen. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        Select s = new Select(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/select")));
        List<WebElement> ponude = s.getOptions();
        int size = ponude.size();

        assertEquals(size, 4); //Prvo da proverimo da li imamo 4 opcije uopste inace ne vredi proveravati nastavak ako ovo nije zadovoljeno.
        String[] izborPonudaPoStrani = {"25", "50", "100", "200"};
        for(int i=0; i<size; i++)
        {
            String option = ponude.get(i).getText();
            assertEquals(option, izborPonudaPoStrani[i]); //Proveriti da li se u dropdown meniju redom nalaze vrednosti 50,25,100,200.
        }
    }

    @Test
    public void testPodrazumevanogBrojaStavkiPoStrani() //Provera da li je defaultno selektovano 50 stavki po strani. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement podrazumevanaVrednost = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/select"));
        Select s = new Select(podrazumevanaVrednost);

        assertEquals(s.getFirstSelectedOption().getText(),"50"); //Podrazumevana vrednost treba da nam bude 50.
    }

    @Test
    public void testDaLiImaBrojStavkiPoStrani25() //Provera da li izbor broja stavki po strani radi za 25. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        Select s = new Select(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/select")));
        s.selectByValue("25"); //provera za 25

        List<WebElement> rows25 = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        //assertEquals(rows25.size()-3, 25); //Provera da li na strani ima izlistanih 25 ponuda
        assertTrue(rows25.size()-3 <= 25); //Provera da je na strani izlistano maksimum 25 ponuda jer ih mozda u bazi nema vise al je bitno da ne bude vise od 25.
    }
    @Test
    public void testDaLiImaBrojStavkiPoStrani50() //Provera da li izbor broja stavki po strani radi za 50. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        Select s = new Select(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/select")));
        s.selectByValue("50"); //provera za 50

        List<WebElement> rows50 = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        //assertEquals(50, rows50.size()); //Provera da li na strani ima izlistanih 50 ponuda
        assertTrue(rows50.size()-3 <= 50); //Provera da je na strani izlistano maksimum 50 ponuda jer ih mozda u bazi nema vise al je bitno da ne bude vise od 25.
    }
    @Test
    public void testDaLiImaBrojStavkiPoStrani100() //Provera da li izbor broja stavki po strani radi za 100. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        Select s = new Select(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/select")));
        s.selectByValue("100"); //provera za 100

        List<WebElement> rows100 = driver.findElements(By.className("my-2"));  // Smestamo sve ponude koje su izlistane na strani.
        //assertEquals(100, rows100.size()); //Provera da li na strani ima izlistanih 100 ponuda
        assertTrue(rows100.size()-3 <= 100); //Provera da je na strani izlistano maksimum 100 ponuda jer ih mozda u bazi nema vise al je bitno da ne bude vise od 25.
    }
    @Test
    public void testDaLiImaBrojStavkiPoStrani200() //Provera da li izbor broja stavki po strani radi za 200. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        Select s = new Select(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[3]/select")));
        s.selectByValue("200"); //provera za 200

        List<WebElement> rows200 = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        //assertEquals(200, rows200.size()-3); //Provera da li na strani ima izlistanih 200 ponuda
        assertTrue(rows200.size()-3 <= 200); //Provera da je na strani izlistano maksimum 200 ponuda jer ih mozda u bazi nema vise al je bitno da ne bude vise od 25.
    }

    @Test
    public void testPojedinacnePonude() //Provera da li pojedinacna ponuda sadrzi sve sto je potrebno. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[1]")));
        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani

        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li sadrzi potrebne podatke.
        {
            int broj = i-2;
            //rows.get(i).findElement(By.className("card-body"));
            WebElement naziv = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]"));
            WebElement destinacija = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/h5"));
            WebElement slika = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/img"));
            WebElement terminPutovanja = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[2]"));
            WebElement cena = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[3]"));
            WebElement tipPrevoza = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[4]"));

            // Provera da li postoje sve ove stavke, samo ako sve postoje test prolazi.
            assertNotNull(naziv);
            assertNotNull(destinacija);
            assertNotNull(slika);
            assertNotNull(terminPutovanja);
            assertNotNull(cena);
            assertNotNull(tipPrevoza);
        }
    }

    @Test(enabled=false)
    public void testPretragePoNazivuAranzmana() //Provera da li radi pretraga po nazivu aranzmana. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement naziv = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        naziv.sendKeys("Kragujevac 2022-07-10_4665358"); // Pre ovoga treba napraviti posebnu ponudu sa bas tim imenom kako bi proverili da li ga pronalazi.
        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(13))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div/a/div/h5")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertEquals(rows.size()-3, 1); // Treba da ostane samo 1 element koji ce da bude pronadjen i smesten u listu Rows

        WebElement nazivPonude = rows.get(3).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div/a/div/h5"));
        assertEquals(nazivPonude.getText().split(" ")[0], "Kragujevac 2022-07-10_4665358"); // Poredimo da li je naziv pronadjene ponude jednak zadatom nazivu.
    }

    @Test
    public void testPretragePoDeluNazivaAranzmana() //Provera da li radi pretraga po delu naziva aranzmana. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement naziv = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        naziv.sendKeys("P"); // Pre ovoga treba napraviti posebnu ponudu sa bas tim imenom kako bi proverili da li ga pronalazi.
        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div/a/div/h5")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        //System.out.println("NASAO SAM : " + (rows.size()-3));
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li naziv sadrzi poslati deo naziva.
        {
            int broj = i-2;
            WebElement nazivPonude = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/h5"));
            //System.out.println(nazivPonude.getText());
            assertTrue(nazivPonude.getText().contains("P")); // Poredimo da li je naziv pronadjene ponude sadrzi zadati deo naziva.
        }
    }

    @Test (enabled=false)
    public void testPretragePoGradu() //Provera da li radi pretraga po gradu. Nije provereno...
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement grad = driver.findElement(By.xpath("//*[@id=\"city\"]"));
        grad.sendKeys("Test"); // Pre ovoga treba napraviti posebnu ponudu sa bas tim gradom kako bi proverili da li ga pronalazi.
        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div/a/div/h5")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj grad.
        {
            int broj = i-2;
            WebElement nazivPonude = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]"));
            //System.out.println(nazivPonude.getText());
            assertEquals(nazivPonude.getText().split(",")[0], "Test"); // Poredimo da li grad postoji u ponudi aranzmana.
        }
    }

    @Test (enabled=false)
    public void testPretragePoDrzavi() //Provera da li radi pretraga po drzavi. Nije provereno...
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement drzava = driver.findElement(By.xpath("//*[@id=\"country\"]"));
        drzava.sendKeys("Srbija"); // Pre ovoga treba napraviti posebnu ponudu za bas tu drzavu kako bi proverili da li ga pronalazi.
        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div/a/div/h5")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj grad.
        {
            int broj = i-2;
            WebElement drzavaPonude = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]"));
            //System.out.println(drzavaPonude.getText());
            assertEquals(drzavaPonude.getText().split(",")[1], "Srbija"); // Poredimo da li drzava postoji u ponudi aranzmana.
        }
    }

    @Test
    public void testPretragePoTipuPrevozaAvion() //Provera da li radi pretraga po tipu prevoza za avion. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();

        Select s = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[2]/div[2]/select")));
        s.selectByValue("Avion"); //provera za Avion

        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[4]")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj prevoz.
        {
            int broj = i-2;
            WebElement tipPrevoza = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[4]"));
            assertEquals(tipPrevoza.getText().split(" ")[1], "Avion"); // Poredimo da li se tip prevoza poklapa
            //System.out.println(rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]")).getText());
        }
    }

    @Test
    public void testPretragePoTipuPrevozaVoz() //Provera da li radi pretraga po tipu prevoza za voz. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();

        Select s = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[2]/div[2]/select")));
        s.selectByValue("Voz"); //provera za Voz

        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[4]")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj prevoz.
        {
            int broj = i-2;
            WebElement tipPrevoza = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[4]"));
            assertEquals(tipPrevoza.getText().split(" ")[1], "Voz"); // Poredimo da li se tip prevoza poklapa.
            System.out.println(rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]")).getText());
        }
    }
    @Test
    public void testPretragePoTipuPrevozaAutobus() //Provera da li radi pretraga po tipu prevoza za autobus. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();

        Select s = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[2]/div[2]/select")));
        s.selectByValue("Autobus"); //provera za Autobus

        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[4]")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj prevoz.
        {
            int broj = i-2;
            WebElement tipPrevoza = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[4]"));
            assertEquals(tipPrevoza.getText().split(" ")[1], "Autobus"); // Poredimo da li se tip prevoza poklapa.
            //System.out.println(rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]")).getText());
        }
    }
    @Test
    public void testPretragePoTipuPrevozaSamostalniPrevoz() //Provera da li radi pretraga po tipu prevoza za samostalni prevoz. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();

        Select s = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[2]/div[2]/select")));
        s.selectByValue("Samostalni prevoz"); //provera za Samostalni prevoz

        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[4]")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj prevoz.
        {
            int broj = i-2;
            WebElement tipPrevoza = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[4]"));
            assertEquals(tipPrevoza.getText().split(" ")[1], "Samostalni"); // Poredimo da li se tip prevoza poklapa
            //System.out.println(rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]")).getText());
        }
    }
    @Test
    public void testPretragePoTipuPrevozaBrod() //Provera da li radi pretraga po tipu prevoza za brod. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();

        Select s = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[2]/div[2]/select")));
        s.selectByValue("Brod"); //provera za Brod

        WebElement filter = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[5]/div[1]/button"));
        filter.click(); // Posle ovoga bi trebalo da ostane samo taj jedan aranzman koji smo trazili.

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[4]")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        for(int i=3; i<rows.size(); i++) // Za svaku ponudu koja postoji na stranici proveravamo da li ima taj prevoz.
        {
            int broj = i-2;
            WebElement tipPrevoza = rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[4]"));
            assertEquals(tipPrevoza.getText().split(" ")[1], "Brod"); // Poredimo da li se tip prevoza poklapa
            //System.out.println(rows.get(i).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div["+broj+"]/a/div/p[1]")).getText());
        }
    }

    @Test
    public void testRezervacijeAranzmana() //Proveravamo da li je moguca rezervacija za aranzman. PROVERENO
    {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/a")).click();
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]/a/div/p[4]")));

        List<WebElement> rows = driver.findElements(By.className("my-2")); // Smestamo sve ponude koje su izlistane na strani.
        assertNotNull(rows);
        WebElement aranzman = rows.get(0).findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div[1]"));
        aranzman.click();

        WebElement secondResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[6]/div/a")));

        WebElement dostupnost = driver.findElement(By.xpath("/html/body/div/div[2]/div[4]/div[3]/div[2]"));
        assertEquals(dostupnost.getText(), "Dostupno");

        WebElement prijava = driver.findElement(By.xpath("/html/body/div/div[2]/div[6]/div/a"));
        prijava.sendKeys(Keys.ENTER);

        WebElement thirdResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[3]/div/button")));

        WebElement ime = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[1]/input"));
        ime.sendKeys("Test");

        WebElement prezime = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/input"));
        prezime.sendKeys("Test");

        WebElement email = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[3]/input"));
        email.sendKeys("test@gmail.com");

        WebElement kontakt = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[4]/input"));
        kontakt.sendKeys("034343434");

        WebElement adresa = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[5]/input"));
        adresa.sendKeys("Testna 13");

        WebElement nacinPlacanja = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[6]/input"));
        nacinPlacanja.sendKeys("Kes");

        WebElement brojOdraslih = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[7]/div[1]/input"));
        brojOdraslih.clear();
        brojOdraslih.sendKeys("2");

        WebElement brojDece = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[7]/div[2]/input"));
        brojDece.clear();
        brojDece.sendKeys("1");

        WebElement komentar = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div/textarea"));
        komentar.sendKeys("No comment");

        WebElement submit = driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/button"));
        submit.sendKeys(Keys.ENTER);
    }
}