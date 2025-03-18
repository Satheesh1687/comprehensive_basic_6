package defaut;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Set;

public class nocodeAutifyTest {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		try {

			driver.get("https://nocode.autify.com/");
			driver.manage().window().maximize();

			WebDriverWait wait = new WebDriverWait(driver, 10);

			WebElement Acceptcookie = wait
					.until(ExpectedConditions.elementToBeClickable((By.xpath("//button[text()='Accept All']"))));

			Acceptcookie.click();
			WebElement startFreeTrialButton = driver.findElement(By.xpath("//a[text()='Start Free Trial']"));
			String link = startFreeTrialButton.getAttribute("href");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.open(arguments[0], '_blank');", link);

			String parentWindow = driver.getWindowHandle();

			Thread.sleep(2000);

			Set<String> allWindows = driver.getWindowHandles();
			for (String windowHandle : allWindows) {
				if (!windowHandle.equals(parentWindow)) {

					driver.switchTo().window(windowHandle);

					WebElement signup = wait
							.until(ExpectedConditions.elementToBeClickable((By.xpath("//button[text()='Sign up']"))));

					signup.click();

					String[] actualResults = {
							driver.findElement(By.xpath("//span[text()=\"First name can't be blank\"]")).getText(),
							driver.findElement(By.xpath("//span[text()=\"Last name can't be blank\"]")).getText(),
							driver.findElement(By.xpath("//span[text()=\"Company name can't be blank\"]")).getText(),
							driver.findElement(By.xpath("//span[text()=\"Company size can't be blank\"]")).getText(),
							driver.findElement(By.xpath("//span[text()=\"Email can't be blank\"]")).getText(),
							driver.findElement(By.xpath("//span[text()=\"Password cannot be blank\"]")).getText(),
							driver.findElement(By.xpath("//span[text()=\"Phone Number can't be blank\"]")).getText() };

					String exptectedresults[] = { "First name can't be blank", "Last name can't be blank",
							"Company name can't be blank", "Company size can't be blank", "Email can't be blank",
							"Password cannot be blank", "Phone Number can't be blank" };

					// Check if actual results match expected results
					if (Arrays.equals(actualResults, exptectedresults)) {
						System.out.println("All messages match!");
					} else {
						System.out.println("Mismatch found!");
					}

					driver.close();
				}
			}

			driver.switchTo().window(parentWindow);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			driver.quit();
		}
	}
}
