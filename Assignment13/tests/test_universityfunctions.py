import pytest

from PageObject.loading_Page.Login_Page import LoadinguniversityPage  
import Urls.Url_ as data 


@pytest.fixture()
def class_fixture(request, initialize_browser): 
    request.cls.driver = initialize_browser
    request.cls.Login_Page = LoadinguniversityPage(request.cls.driver)   
    request.cls.driver.get(data.BASE_URL)   
    request.cls.driver.set_window_size(1920, 1080)
    request.cls.driver.implicitly_wait(5)


@pytest.mark.usefixtures("class_fixture")
class TestuniversityPage:

    def test_universityPage_functions(self):                 
        # Step 1: Log in to the university page
        flag, err_msg = self.Login_Page.login_universityPage()
        assert flag, err_msg

        # Step 2: Switch to the new tab and verify the title
        expected_title = "WebDriver | Dropdown Menu(s) | Checkboxe(s) | Radio Button(s)"
        flag, err_msg = self.Login_Page.switch_to_new_tab_and_verify_title(expected_title)
        assert flag, err_msg

        # Step 3: Select and verify dropdown
        flag, err_msg = self.Login_Page.select_and_verify_dropdown()
        assert flag, err_msg
        
        # Step 4: Select and verify checkboxes
        flag, err_msg = self.Login_Page.select_and_verify_checkboxes()
        assert flag, err_msg
        
        # Step 5: Select and verify radio buttons
        flag, err_msg = self.Login_Page.select_and_verify_radio_buttons("green")  # Pass "green" as a string
        assert flag, err_msg
