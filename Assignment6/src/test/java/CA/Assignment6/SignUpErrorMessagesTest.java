package CA.Assignment6;
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

import java.time.Duration;
import java.util.Set;

public class SignUpErrorMessagesTest {

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
	public void testSignUpErrorMessages() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

		WebElement signup = wait
				.until(ExpectedConditions.elementToBeClickable((By.xpath("//button[text()='Sign up']"))));

		signup.click();

		// Verify error messages

		String[] actualResults = {
				driver.findElement(By.xpath("//span[text()=\"First name can't be blank\"]")).getText(),
				driver.findElement(By.xpath("//span[text()=\"Last name can't be blank\"]")).getText(),
				driver.findElement(By.xpath("//span[text()=\"Company name can't be blank\"]")).getText(),
				driver.findElement(By.xpath("//span[text()=\"Company size can't be blank\"]")).getText(),
				driver.findElement(By.xpath("//span[text()=\"Email can't be blank\"]")).getText(),
				driver.findElement(By.xpath("//span[text()=\"Password cannot be blank\"]")).getText(),
				driver.findElement(By.xpath("//span[text()=\"Phone Number can't be blank\"]")).getText() };

		String[] expectedResults = { "First name can't be blank", "Last name can't be blank",
				"Company name can't be blank", "Company size can't be blank", "Email can't be blank",
				"Password cannot be blank", "Phone Number can't be blank" };

		// Assert to verify the messages
		for (int i = 0; i < actualResults.length; i++) {
			Assert.assertEquals(actualResults[i], expectedResults[i], "Mismatch at index " + i + ": ");
		}
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
