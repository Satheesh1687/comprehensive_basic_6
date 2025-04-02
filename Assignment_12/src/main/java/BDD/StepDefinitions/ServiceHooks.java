package BDD.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import BDD.Base.testBase;
import BDD.enums.Browsers;

public class ServiceHooks {
	
	testBase Base;

	
	@Before
	public void initializeTest() {
		Base = new testBase();
		Base.selectBrowser(Browsers.CHROME.name());
	}
   @After
	public void endTest() 
	{
		
		Base.driver.quit();
	}
}
