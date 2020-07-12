package selenium.automated.tests;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DevToTests {

    WebDriver driver; //inicjalizacja drivera - pustej przegladarki
    String url = "https://dev.to/"; //zapisujemy w zmiennej url, wartosc linku, ktory ma zostac otworzony w przegladarce

    public void HighlightElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
    }


    @Before
    public void SetUp() {    //per-requirements - warunki poczatkowe
        System.setProperty("webdriver.chrome.driver", "c://SeleniumDrivers//chromedriver.exe");  //okreslenie sciezki do chromedriver
        driver = new ChromeDriver();
        driver.get(url); //otworzenie linku w przegladarce
        driver.manage().window().maximize();

       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // jesli nie mozesz znalexc elementu, pokczekaj 10s, sprawdzacjąc co 5s czy element jest już dostepny
    }

    @Test
    public void OpenDevTo(){

        //na ten momen mamy otworzona strone dev.to - zeby sprawdzic czy faktycznie na niej jestesmy
        //chcemy porownac url ze zmniennej wczesniejszej do obecnego url z przegladarki

        String currentUrl = driver.getCurrentUrl(); //wyciagamy obecny url z przeglaarki i przepisujemy go do zmiennej currentUrl

        //assertTrue - sprawdza poprawnosc warunku, warunkiem tym jest url.equals(currentUrl)) - czy url ze zmiennej jest taki sam jak w zmiennej currentUrl
        //jesli nie - wpisuje meesage "The current URL isn't dev.to", i ustawia test na fail

        assertTrue("The current URL isn't dev.to", url.equals(currentUrl));
    }

    @Test
    public void GoToWeek() {
        WebElement week = driver.findElement(By.cssSelector("#on-page-nav-controls > div > nav > a:nth-child(2)"));
        //znalezienie elemnetu week na stronie
        HighlightElement(week); //podswietlnie elementu week
        week.click(); //klikniecie elementu week

        WebDriverWait wait = new WebDriverWait(driver, 5);// zainicjalizowanie Explicit Wait
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));// poczekaj az url bedzie: https;;//dev.tp/top/week
        //wait.until(ExpectedConditions.attributeContains(week,"class", "item--current"));


        WebElement firstPostOnWeek = driver.findElement(By.className("crayons-story__body")); //odnalezienie pierwszego posta
        HighlightElement(firstPostOnWeek); //podswietlenie pierwszego posta
        WebElement firstPostTitle = driver.findElement(By.cssSelector(".crayons-story__title > a")); //znajdz element za pomocą cssSelector - bedzie t pierszy posat
        HighlightElement(firstPostTitle);
        String linkToFirstPost = firstPostTitle.getAttribute("href"); // wyciagnij z nazwy pierwszego posta link do strony
        firstPostOnWeek.click(); //klikniecie 1 postu
        String currentUrl = driver.getCurrentUrl(); //wyciagnij obecny link

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-article__header__meta")));

        //sprawdz czy link do postu jets taki sam jak obecny url
        assertEquals("url isnt't the same as link(href) value", linkToFirstPost, currentUrl);

    }



}
