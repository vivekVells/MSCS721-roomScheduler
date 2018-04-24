import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# make sure you set the executable path properly below for the webdriver

# test case for google search
class GoogleSearch(unittest.TestCase):
    # this function is executed before every test case functions
    def setUp(self):
        # get the Browser webdriver instance
        self.driver = webdriver.Chrome(executable_path=r'E:\Marist\Semester2\SoftwareVerificationAndMaintenance\RoomScheduler\GUItesting\webdrivers\chromedriver.exe')

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


    # this function will be executed after every test method above ran
    def tearDown(self):
        self.driver.close()

#
if __name__ == "__main__":
    unittest.main()


'''
Program Output: (Running Unit Test)
Testing started at 3:27 PM ...
C:\Developer\Python36\python.exe C:\Dev\JetBrains\PyCharm\helpers\pycharm\_jb_unittest_runner.py --target GooglePageUnitTesting.GoogleSearch.test_google_title_in_webpage
Launching unittests with arguments python -m unittest GooglePageUnitTesting.GoogleSearch.test_google_title_in_webpage in E:\Marist\Semester2\SoftwareVerificationAndMaintenance\RoomScheduler\GUItesting


Ran 1 test in 7.982s

OK

Process finished with exit code 0
'''