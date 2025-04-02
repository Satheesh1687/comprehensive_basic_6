package BDD.PageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BDD.Base.testBase;

public class universityPage extends testBase {

	private WebDriver driver;

	@FindBy(xpath = "//a[@id='iframe']//h1[text()='IFRAME']")
	public WebElement IFRAME;

	@FindBy(xpath = "//div/a[@class='right carousel-control' and @href='#carousel-example-generic']")
	public WebElement NextButton;

	public void clickIFRAME() {
		IFRAME.click();
	}

	public void validateimage() throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(NextButton));

		NextButton.click();

		File screenshot = ((TakesScreenshot) testBase.driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(screenshot, new File("test-output/firstImage.png"));
	
	}
	public void validateSecondimage() throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(NextButton));

		NextButton.click();

		File screenshot = ((TakesScreenshot) testBase.driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(screenshot, new File("test-output/SecondImage.png"));
	
	}

	public universityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
}