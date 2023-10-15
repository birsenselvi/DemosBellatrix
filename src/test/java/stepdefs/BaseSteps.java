package stepdefs;

import driver.Driver;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BaseSteps {
    final String url = "https://demos.bellatrix.solutions/";
    protected WebDriver driver;
    protected WebDriverWait wait;

   By lhomePage = By.xpath( "//h1[text()='Shop']");
    @FindBy(xpath = "//p[@class='woocommerce-result-count'][1]")
            protected WebElement resultNumber;
    @FindBy(xpath="//ul[@class='products columns-4']//h2")
            protected List<WebElement>productNames;
    By lsearchBox = By.xpath("//input[@type='search']");

    String addToCart = "//a[@aria-label='Add “%s” to your cart']";
    By lcart = By.xpath("//ul[@class='nav-menu']//li//a[.='Cart']");
    String productToCart="//td[@class='product-name']//a[.='%s']";

    public BaseSteps(){

        driver = Driver.getDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver,this);

    }

    public void gotoHome(){
        driver.get(url);
    }
    public void click(By locator){

        wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        click(element);

    }
    public void click(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

    }
    public void sendKeys(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }

    public void shouldBeVisible(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void searchProduct(String text) {
        sendKeys(lsearchBox, text);
        new Actions(driver).keyDown(Keys.ENTER).perform();

    }
    public void scrollIntoView(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);

    }
    public void addProductToCart(String productName) throws InterruptedException {

        String locator = String.format(addToCart, productName);
        By addToCart = By.xpath(locator);
        click(addToCart);
        Thread.sleep(500);
    }
    public void assertInCart(String productName){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String locator = String.format(productToCart, productName);
        By productInCart = By.xpath(locator);
        shouldBeVisible(productInCart);
    }


}
