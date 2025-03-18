
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class NewTabTest {

	 WebDriver driver;
	 String parentWindow;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://nocode.autify.com/");
		driver.manage().window().maximize();
		parentWindow = driver.getWindowHandle();
	}

	@Test
	public void testSwitchToChildWindowAndVerifyTitle() {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement Acceptcookie = wait
				.until(ExpectedConditions.elementToBeClickable((By.xpath("//button[text()='Accept All']"))));

		Acceptcookie.click();
		WebElement startFreeTrialButton = driver.findElement(By.xpath("//a[text()='Start Free Trial']"));
		String link = startFreeTrialButton.getAttribute("href");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open(arguments[0], '_blank');", link);

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

	
		String actualTitle = driver.getTitle();
		String expectedTitle = "Trial Application - Autify"; 
		Assert.assertEquals(actualTitle, expectedTitle, "Child window title does not match!");

		
		driver.close();

		
		driver.switchTo().window(parentWindow);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
