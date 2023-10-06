package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.ParameterType;

import io.cucumber.java.en.*;

public class MyStepDefinitions {
    private WebDriver driver;
    String purchaseMessageXpath;
    WebDriverWait wait;

	@ParameterType(".*")
	public String customString(String value) {
	    return value;
	}
	
    @Given("Open the shop page")
    public void open_the_shop_page() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @When("I add the first {customString} products to the cart and place the order")
    public void I_add_the_first_products_to_the_cart_and_place_the_order(String numberOfItems) { 
        int timeout = 30;
        wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
        int itemsToBuy=Integer.parseInt(numberOfItems);
        String homeButtonXpath = "//a[@href='index.html']";
        String cartPageButtonXpath = "//a[@href='cart.html']";
        String addToCartButtonXpath = "//a[@class='btn btn-success btn-lg']";
        String placeOrderButtonXpath = "//button[@class='btn btn-success']";
        String purchaseButtonXpath = "//button[@onclick='purchaseOrder()']";
        purchaseMessageXpath = "//div[@class='sweet-alert  showSweetAlert visible']/h2";
        String inputFieldNameId = "name";
        String nameInput = "nombre";
        String inputFieldCountryId = "country";
        String countryInput = "pais";
        String inputFieldCityId = "city";
        String cityInput = "ciudad";
        String inputFieldCardId = "card";
        String cardInput = "tarjeta";
        String inputFieldYearId = "year";
        String yearInput = "ano";
        String inputFieldMonthId = "month";
        String monthInput = "mes";
        
        for (int i = 1; i <= itemsToBuy; i++) {
            String itemXpath = "//a[@href='prod.html?idp_="+i+"']";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemXpath)));
            WebElement macBookWebElement = driver.findElement(By.xpath(itemXpath));
            macBookWebElement.click();
            
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addToCartButtonXpath)));
            WebElement addToCartElement = driver.findElement(By.xpath(addToCartButtonXpath));
            addToCartElement.click();
            
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(homeButtonXpath)));
            WebElement homeButton = driver.findElement(By.xpath(homeButtonXpath));
            homeButton.click();
            
            
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cartPageButtonXpath)));
        WebElement cartButton = driver.findElement(By.xpath(cartPageButtonXpath));
        cartButton.click();
    	    
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(placeOrderButtonXpath)));
        WebElement placeOrderButton = driver.findElement(By.xpath(placeOrderButtonXpath));
        placeOrderButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(inputFieldNameId)));
        WebElement nameInputField= driver.findElement(By.id(inputFieldNameId));
        nameInputField.sendKeys(nameInput);

        WebElement countryInputField= driver.findElement(By.id(inputFieldCountryId));
        countryInputField.sendKeys(countryInput);
        
        WebElement cityInputField= driver.findElement(By.id(inputFieldCityId));
        cityInputField.sendKeys(cityInput);
        
        WebElement cardInputField= driver.findElement(By.id(inputFieldCardId));
        cardInputField.sendKeys(cardInput);
        
        WebElement yearInputField= driver.findElement(By.id(inputFieldYearId));
        yearInputField.sendKeys(yearInput);
        
        WebElement monthInputField= driver.findElement(By.id(inputFieldMonthId));
        monthInputField.sendKeys(monthInput);

        WebElement purchaseButton = driver.findElement(By.xpath(purchaseButtonXpath));
        purchaseButton.click();
        
        
    }


    
    @Then("A message with a {customString} text and button OK is displayed")
    public void A_message__with_text_and_button_OK_is_displayed(String welcomeText) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(purchaseMessageXpath)));
    	WebElement purchaseMessage=driver.findElement(By.xpath(purchaseMessageXpath));
    	Assert.assertEquals(purchaseMessage.getText(), welcomeText);
    	
    }
    

    
}
