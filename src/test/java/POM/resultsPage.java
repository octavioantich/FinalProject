package POM;

import java.time.Duration;
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

public class resultsPage {
    private WebDriver driver;
    int timeout = 30;
    WebDriverWait wait;
    String firstWebResultXpath = "//cite";
	
    public resultsPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));// tengo que setear aca el wait porque necesito el driver.
	}
    @Test
	public void checkFirstPageIsSteam() {
    	
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstWebResultXpath)));
        WebElement firstWebResult = driver.findElements(By.xpath(firstWebResultXpath)).get(0);
    	Assert.assertEquals(firstWebResult.getText().contains("store.steampowered.com"), true);
	}
}
