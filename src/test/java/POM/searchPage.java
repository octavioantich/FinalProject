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
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class searchPage {

    private WebDriver driver;
    String searchBarXpath = "//textarea[@name = 'q']";
	String ajaxListXpath = "//ul[@role = 'listbox']";
    int timeout = 30;
    WebDriverWait wait;
    boolean noCoincidence=true;
    WebElement firstResultImage=null;
    WebElement searchBar;
    WebElement ajaxList;
    List<WebElement> autoSearchOptions;
    List<WebElement> automationSearchOptions;
    List<String> busquedasParaComparar1;
    List<String> busquedasParaComparar2;
    
	public searchPage(WebDriver driver) {
		this.driver=driver;
    	wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
    	searchBar = driver.findElement(By.xpath(searchBarXpath));
	}

	
	public void searchAndStoreAuto() {
        searchBar.clear();
    	searchBar.sendKeys("auto");
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ajaxListXpath)));
    	ajaxList = driver.findElements(By.xpath(ajaxListXpath)).get(0);
    	
    	autoSearchOptions = ajaxList.findElements(By.tagName("li"));
    	busquedasParaComparar1 = new ArrayList<>();
        for (WebElement li : autoSearchOptions) {
            System.out.println(li.getText());
            busquedasParaComparar1.add(li.getText());
        }
    	
	}

	public void searchAndStoreAutomation() throws InterruptedException {
        searchBar.clear();
        searchBar.sendKeys("automation");

        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ajaxListXpath)));
    	ajaxList = driver.findElements(By.xpath(ajaxListXpath)).get(0);
    	
    	automationSearchOptions = ajaxList.findElements(By.tagName("li"));
    	busquedasParaComparar2 = new ArrayList<>();
        for (WebElement li : automationSearchOptions) {
            System.out.println(li.getText());
            busquedasParaComparar2.add(li.getText());
        }
    	
	}
	
	public void checkNoCoincidence() throws InterruptedException {
		for (String busqueda1 : busquedasParaComparar1) {
        	for (String busqueda2 : busquedasParaComparar2) {
                if(busqueda1.contentEquals(busqueda2)) {
                	noCoincidence=false;
                }      
            }
        }
        Assert.assertEquals(noCoincidence, true);
	}
	
    public void clickFirstImage() {
        for (WebElement li1 : automationSearchOptions) {
        	if (li1.getAttribute("class").contains("sbct sbre") && (firstResultImage==null)) {
        		firstResultImage=li1;
        	}
        }
        wait.until(ExpectedConditions.elementToBeClickable(firstResultImage));
        firstResultImage.click();
        
    }

 }
