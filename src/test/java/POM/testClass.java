package POM;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import org.testng.Assert;

public class testClass {
    private WebDriver driver;
    int timeout = 30;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
        
        
    }

    @Test
	public void check() throws InterruptedException {
    	
    	searchPage objSearchPage = new searchPage(driver);
    	resultsPage objResultsPage = new resultsPage(driver);

    	objSearchPage.searchAndStoreAuto();
    	objSearchPage.searchAndStoreAutomation();
    	objSearchPage.checkNoCoincidence();
    	objSearchPage.clickFirstImage();
    	objResultsPage.checkFirstPageIsSteam();
        
        
    	
	}
}


/*     	List<WebElement> autoSearchOptions = ajaxList.findElements(By.tagName("li"));
    	List<WebElement> divElements = new ArrayList<>();

        for (WebElement liElement : autoSearchOptions) {
        	System.out.println(liElement.getText());
            WebElement divElement = liElement.findElement(By.tagName("div"));
            divElements.add(divElement);
        }
        
        searchBar.clear();
        searchBar.sendKeys("automation");

    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ajaxListXpath)));
    	ajaxList = driver.findElements(By.xpath(ajaxListXpath)).get(0);
        
    	List<WebElement> automationSearchOptions = ajaxList.findElements(By.tagName("li"));
        
        List<WebElement> divElements2 = new ArrayList<>();

        for (WebElement liElement : automationSearchOptions) {
        	System.out.println(liElement.getText());
            WebElement divElement = liElement.findElement(By.tagName("div"));
            divElements2.add(divElement);
        }
        
        
        
        for (WebElement li1 : divElements) {
        	for (WebElement li2 : divElements2) {
                if(li1.getText().contentEquals(li2.getText())) {
                	noCoincidence=false;
                	System.out.println("PRIMERO "+li1.getText()+" SEGUNDO "+li2.getText());
                }      
            }
        }
 */