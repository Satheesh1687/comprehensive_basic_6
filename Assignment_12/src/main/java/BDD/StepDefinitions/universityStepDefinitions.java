package BDD.StepDefinitions;

import java.io.IOException;

import BDD.Base.testBase;
import BDD.PageObjects.universityPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class universityStepDefinitions extends testBase {

	universityPage landingpage = new universityPage(driver);

	@Given("I launch the URL {string}")
	public void i_launch_the_url(String url) {

		driver.get(url);

	}

	@When("I verify the page title")
	public void i_verify_the_page_title() {
		String expectedTitle = "WebDriverUniversity.com";
		String actualTitle = driver.getTitle();
		if (!actualTitle.equals(expectedTitle)) {
			throw new AssertionError("Page title mismatch! Expected: " + expectedTitle + ", Actual: " + actualTitle);
		}
	}

	@When("I click on the {string} link")
	public void i_click_on_the_link(String string) {
		landingpage.clickIFRAME();

	}

	@Then("a new tab should open")
	public void a_new_tab_should_open() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

	}

	@Then("I switch to the new tab")
	public void i_switch_to_the_new_tab() {

		String expectedNewTabTitle = "WebDriver | IFrame";
		String actualTitle = driver.getTitle();
		if (!actualTitle.equals(expectedNewTabTitle)) {
			throw new AssertionError(
					"Page title mismatch! Expected: " + expectedNewTabTitle + ", Actual: " + actualTitle);
		}
	}

	@Then("I verify the image is present")
	public void i_verify_the_image_is_present() {

		driver.switchTo().frame("frame");
	}

	@When("I click on the right arrow button")
	public void i_click_on_the_right_arrow_button() throws InterruptedException, IOException {
		landingpage.validateimage();
	}

	@Then("the image should change accordingly")
	public void the_image_should_change_accordingly() throws InterruptedException, IOException {

		landingpage.validateSecondimage();

	}

}
