package selenium.automated.tests;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class DevToTests {

    WebDriver driver; //inicjalizacja drivera - pustej przegladarki

    @Before
    public void setUp() {    //per-requirements - warunki poczatkowe
        System.setProperty("webdriver.chrome.driver", "c://SeleniumDrivers//chromedriver.exe");  //okreslenie sciezki do chromedriver

        driver = new ChromeDriver();  //nadpisanie drivera, jako przegladarke chrome
    }

    @Test
    public void OpenDevTo(){
        String url = "https://dev.to/"; //zapisujemy w zmiennej url, wartosc linku, ktory ma zostac otworzony w przegladarce
        driver.get(url); //otworzenie linku w przegladarce

        //na ten momen mamy otworzona strone dev.to - zeby sprawdzic czy faktycznie na niej jestesmy
        //chcemy porownac url ze zmniennej wczesniejszej do obecnego url z przegladarki

        String currentUrl = driver.getCurrentUrl(); //wyciagamy obecny url z przeglaarki i przepisujemy go do zmiennej currentUrl

        //assertTrue - sprawdza poprawnosc warunku, warunkiem tym jest url.equals(currentUrl)) - czy url ze zmiennej jest taki sam jak w zmiennej currentUrl
        //jesli nie - wpisuje meesage "The current URL isn't dev.to", i ustawia test na fail

        assertTrue("The current URL isn't dev.to", url.equals(currentUrl));
    }




}
