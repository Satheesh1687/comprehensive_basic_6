from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import utils.common_defines as cd
from utils.lib.LoggerUtils import set_logger


class BaseClass(object):

    def __init__(self, driver):
        self.driver = driver
        self.logger = set_logger(__name__)

    def wait_element_to_be_clickable(self, driver, element_method, element_to_wait, wait_time=40):
        element = None
        if element_method.upper() == cd.LOCATE_BY_XPATH:
            element = WebDriverWait(driver, wait_time).until(EC.element_to_be_clickable((By.XPATH, element_to_wait)))
        elif element_method.upper() == cd.LOCATE_BY_ID:
            element = WebDriverWait(driver, wait_time).until(EC.element_to_be_clickable((By.ID, element_to_wait)))
        elif element_method.upper() == cd.LOCATE_BY_CLASS_NAME:
            element = WebDriverWait(driver, wait_time).until(EC.element_to_be_clickable((By.CLASS_NAME, element_to_wait)))
        elif element_method.upper() == cd.LOCATE_BY_CSS_SELECTOR:
            element = WebDriverWait(driver, wait_time).until(EC.element_to_be_clickable((By.CSS_SELECTOR, element_to_wait)))
        else:
            self.logger.info("Pass a valid method to find locator")
        return element

    def wait_element_to_be_visible(self, driver, element_method, element_to_wait, wait_time=40):
        element = None
        if element_method.upper() == cd.LOCATE_BY_XPATH:
            element = WebDriverWait(driver, wait_time).until(EC.visibility_of_element_located((By.XPATH, element_to_wait)))
        elif element_method.upper() == cd.LOCATE_BY_ID:
            element = WebDriverWait(driver, wait_time).until(EC.visibility_of_element_located((By.ID, element_to_wait)))
        elif element_method.upper() == cd.LOCATE_BY_CLASS_NAME:
            element = WebDriverWait(driver, wait_time).until(EC.visibility_of_element_located((By.CLASS_NAME, element_to_wait)))
        elif element_method.upper() == cd.LOCATE_BY_CSS_SELECTOR:
            element = WebDriverWait(driver, wait_time).until(EC.visibility_of_element_located((By.CSS_SELECTOR, element_to_wait)))
        else:
            self.logger.info("Pass a valid method to find locator")
        return element

    def find_element(self, driver, element_method, element_to_find, retry=2, wait_time=40):
        element = None
        if element_method.upper() == cd.LOCATE_BY_XPATH:
            element = driver.find_element(By.XPATH, element_to_find)
        elif element_method.upper() == cd.LOCATE_BY_ID:
            element = driver.find_element(By.ID, element_to_find)
        elif element_method.upper() == cd.LOCATE_BY_CLASS_NAME:
            element = driver.find_element(By.CLASS_NAME, element_to_find)
        elif element_method.upper() == cd.LOCATE_BY_CSS_SELECTOR:
            element = driver.find_element(By.CSS_SELECTOR, element_to_find)
        elif element_method.upper() == cd.LOCATE_BY_LINK_TEXT:
            element = driver.find_element(By.LINK_TEXT, element_to_find)
        elif element_method.upper() == cd.LOCATE_BY_PARTIAL_LINK_TEXT:
            element = driver.find_element(By.PARTIAL_LINK_TEXT, element_to_find)
        else:
            self.logger.info("Pass a valid method to find locator")
        return element

    def find_elements(self, driver, element_method, element_to_find, retry=2, wait_time=40):
        elements = []  # Initialize an empty list
        try:
            if element_method.upper() == cd.LOCATE_BY_XPATH:
                elements = driver.find_elements(By.XPATH, element_to_find)
            elif element_method.upper() == cd.LOCATE_BY_ID:
                elements = driver.find_elements(By.ID, element_to_find)
            elif element_method.upper() == cd.LOCATE_BY_CLASS_NAME:
                elements = driver.find_elements(By.CLASS_NAME, element_to_find)
            elif element_method.upper() == cd.LOCATE_BY_CSS_SELECTOR:
                elements = driver.find_elements(By.CSS_SELECTOR, element_to_find)
            elif element_method.upper() == cd.LOCATE_BY_LINK_TEXT:
                elements = driver.find_elements(By.LINK_TEXT, element_to_find)
            elif element_method.upper() == cd.LOCATE_BY_PARTIAL_LINK_TEXT:
                elements = driver.find_elements(By.PARTIAL_LINK_TEXT, element_to_find)
            elif element_method.upper() == cd.LOCATE_BY_TAG_NAME:
                elements = driver.find_elements(By.TAG_NAME, element_to_find)
            else:
                self.logger.info("Pass a valid method to find locator")
        except Exception as e:
            self.logger.error(f"An error occurred while finding elements: {str(e)}")
        return elements
