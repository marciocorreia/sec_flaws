from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

driver = webdriver.Chrome()
wait = WebDriverWait(driver, 60)

driver.maximize_window()

url = 'http://localhost:8080/SecFlaws/index.jsp'
driver.get(url)

wait.until(EC.url_to_be(url))
assert "SecFlaws Project" in driver.title

time.sleep(1)
element = driver.find_element(By.ID, "input1")
element.send_keys("teste")
time.sleep(1)
element.send_keys(Keys.RETURN)
time.sleep(2)

driver.back()

time.sleep(1)
element = driver.find_element(By.ID, "input1")
element.clear()
element.send_keys("<script>alert('XSS attack')</script>")
time.sleep(1)
element.send_keys(Keys.RETURN)
time.sleep(2)

alert = driver.switch_to.alert
alert.accept()
time.sleep(1)

driver.back()

time.sleep(1)
element.clear()
element = driver.find_element(By.ID, "input2")
element.send_keys("Joao")
time.sleep(1)
element.send_keys(Keys.RETURN)
time.sleep(2)

driver.back()

time.sleep(1)
element = driver.find_element(By.ID, "input2")
element.clear();
element.send_keys("' or ''='")
time.sleep(1)
element.send_keys(Keys.RETURN)
time.sleep(2)

driver.back()
time.sleep(1)

driver.close()
