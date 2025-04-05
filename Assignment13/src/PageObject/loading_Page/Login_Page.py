import time
import locators.locators as locators
import utils.common_defines as cd
from utils.lib.LoggerUtils import set_logger
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import Select
from selenium.webdriver.common.by import By
from PageObject.Base_Page.base import BaseClass


class LoadinguniversityPage(BaseClass):

    def __init__(self, driver):
        super().__init__(driver)
        self.logger = set_logger(__name__) 
    
    def login_universityPage(self):
        dropdown = self.find_element(self.driver, cd.LOCATE_BY_XPATH, locators.DROPDOWN_LINK)
        hover = ActionChains(self.driver).move_to_element(dropdown)
        hover.perform()
        self.logger.info("Click dropdown")
        dropdownlink = self.wait_element_to_be_clickable(self.driver, cd.LOCATE_BY_XPATH, locators.DROPDOWN_LINK)
        dropdownlink.click()
        return True, "Login successful"

    def switch_to_new_tab_and_verify_title(self, expected_title):
        try:
            current_window = self.driver.current_window_handle
            all_windows = self.driver.window_handles
            for window in all_windows:
                if window != current_window:
                    self.driver.switch_to.window(window)
                    break
            actual_title = self.driver.title
            if actual_title == expected_title:
                self.logger.info(f"Title matches: {actual_title}")
                return True, "Title verification successful."
            else:
                self.logger.error(f"Title mismatch. Expected: {expected_title}, Found: {actual_title}")
                return False, f"Title mismatch. Expected: {expected_title}, Found: {actual_title}"
        except Exception as e:
            self.logger.error(f"An error occurred: {str(e)}")
            return False, f"An error occurred: {str(e)}"
    
    def select_and_verify_dropdown(self):
        try:
            dropdown_options = self.find_element(self.driver, cd.LOCATE_BY_ID, locators.DROPDOWN_MENU)
            select = Select(dropdown_options)
            select.select_by_visible_text("Python")
            selected_option = select.first_selected_option.text
            if selected_option == "Python":
                self.logger.info("Successfully selected 'Python' option!")
                return True, "Dropdown verification successful."
            else:
                self.logger.error(f"Failed to select 'Python'. Selected option is: {selected_option}")
                return False, f"Failed to select 'Python'. Selected option is: {selected_option}"
        except Exception as e:
            self.logger.error(f"An error occurred: {str(e)}")
            return False, f"An error occurred: {str(e)}"

    def select_and_verify_checkboxes(self):
        try:
            checkbox_container = self.find_element(self.driver, cd.LOCATE_BY_XPATH, locators.CHECKBOXES)
            checkboxes = checkbox_container.find_elements(By.TAG_NAME, "input")
            checked_count = 0
            unchecked_count = 0
            for checkbox in checkboxes:
                if checkbox.get_attribute("type") == "checkbox":
                    if not checkbox.is_selected():
                        checkbox.click()
                    if checkbox.is_selected():
                        checked_count += 1
                    else:
                        unchecked_count += 1
            self.logger.info(f"Checked checkboxes: {checked_count}, Unchecked checkboxes: {unchecked_count}")
            return True, f"Checked checkboxes: {checked_count}, Unchecked checkboxes: {unchecked_count}"
        except Exception as e:
            self.logger.error(f"An error occurred while selecting and verifying checkboxes: {str(e)}")
            return False, f"An error occurred: {str(e)}"


    def select_and_verify_radio_buttons(self, value_to_select):
        try:
            radio_container = self.find_element(self.driver, cd.LOCATE_BY_XPATH, locators.RADIOBUTTON)
            radio_buttons = radio_container.find_elements(By.TAG_NAME, "input")
            for radio in radio_buttons:
                if radio.get_attribute("value") == value_to_select:
                    radio.click()
                    break
            checked_count = len([radio for radio in radio_buttons if radio.is_selected()])
            unchecked_count = len(radio_buttons) - checked_count
            self.logger.info(f"Checked radio buttons: {checked_count}, Unchecked radio buttons: {unchecked_count}")
            return True, f"Checked radio buttons: {checked_count}, Unchecked radio buttons: {unchecked_count}"
        except Exception as e:
            self.logger.error(f"An error occurred while selecting and verifying radio buttons: {str(e)}")
            return False, f"An error occurred: {str(e)}"

