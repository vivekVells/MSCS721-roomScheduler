import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

driver = webdriver.Chrome(executable_path=r'C:\Users\maristuser\Desktop\roomscheduler github\GUItesting\webdrivers\chromedriver.exe')
driver.get('https://www.google.com')
search_element = driver.find_element_by_name('q')
search_element.clear()
search_element.send_keys('vivekVells github account')
search_element.send_keys(Keys.ENTER)

# test case for google search
class GoogleSearch(unittest.TestCase):
    # this function is executed before every test case functions
    def setUp(self):
        # get the Browser webdriver instance
        self.driver = webdriver.Chrome(executable_path=r'C:\Users\maristuser\Desktop\roomscheduler github\GUItesting\webdrivers\chromedriver.exe')

    # test case for finding google's title
    def test_google_title_in_webpage(self):
        driver = self.driver
        driver.maximize_window() # maximise the window
        driver.implicitly_wait(10) # made the driver's next action to wait for 10 seconds implicitly
        # goto desired website
        driver.get('https://www.google.com/')
        # test whether the title is correct using assertIn function
        self.assertIn("Google", driver.title)

        try:
            assert "Google" in driver.title
        except AssertionError:
            print("Google title not found....")

    # test case to search and goto Vivek Vellaiyappan's github account
    def test_goto_vivekVells_gitHub_account(self):
        driver = self.driver
        search_elem = driver.find_element_by_name('q')

        # make sure the element does not have any pre-populated values before sening the new search terms
        search_elem.clear()
        search_elem.send_keys('Vivek Vellaiyappan GitHub')
        search_elem.send_keys(Keys.RETURN)

        # check whether the page contains this address "https://github.com/vivekVells"
        self.assertIn('vivekVells', driver.page_source)
        print(driver.page_source)

if __name__ == "__main__":
    unittest.main()
