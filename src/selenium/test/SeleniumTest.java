package selenium.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    WebDriver driver;

    @Before
    public void setup() {
        // Always before running any test setup the ChromeDriver
        // to be the default driver
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        
        // Implicitly wait 10s if no element found
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testNavigate() {
        String baseUrl = "https://www.americanas.com.br/";

        // Get started by accessing the URL
        driver.get(baseUrl);

        // Search for PS4
        driver.findElement(By.name("conteudo")).sendKeys("PS4");

        // Click search button
        driver.findElement(By.cssSelector("#h_search-btn")).click();
        
        // Get the first product saying console and click it
        driver.findElement(By.cssSelector("a[href='/produto/1938309341?pfm_carac=ps4&pfm_page=search&pfm_pos=grid&pfm_type=search_page']")).click();

        // Fill out the zipcode to show freight options
        // and then click OK button next to it
        driver.findElement(By.name("cep")).sendKeys("51020280");
        driver.findElement(By.xpath("//button[text()='ok']")).click();

        // Printing freight options
        List<WebElement> freights = driver.findElements(By.className("freight-option-price"));
        for(WebElement freight : freights) {
            System.out.println(freight.getText());
        }

        // Click buy button
        driver.findElement(By.id("buy-button")).click();

        // Select guarantee: all 3 options share the same class.
        // Get all over them and select +12 (2nd element)
        List<WebElement> elements = driver.findElements(By.className("jHrhCG"));
        elements.get(1).click();

        // Click continue button to checkout
        driver.findElement(By.id("btn-continue")).click();

        // Confirm one item has been added to cart
        List<WebElement> items = driver.findElements(By.cssSelector("li.basket-product"));
        Assert.assertEquals(1, items.size());
    }
}
