package assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;
public class FitPioAutomation {

	public static void main(String[] args) {
		// Set up the WebDriver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		try {
			//1. Navigate to the FitPeo Homepage
			driver.get("https://www.fitpeo.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//2. Navigate to the Revenue Calculator Page
			WebElement revenueCalculatorLink = driver.findElement(By.linkText("Revenue Calculator"));
			revenueCalculatorLink.click();

			//3. Scroll Down to the Slider section
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement sliderSection = driver.findElement(By.xpath("//h4[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf']")); // Adjust the locator as needed
			js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);

			//4. Adjust the Slider to 823
			WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-sy3s50']"));
			Actions move = new Actions(driver);
			move.clickAndHold(slider).moveByOffset(94, 20).release().perform();

			//5. Update the Text Field to 560
			// WebElement textField = driver.findElement(By.xpath("//div/input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')]")); // Adjust the locator as needed
			//   Wait for the text field to be visible and enabled
			WebDriverWait wait = new WebDriverWait(driver, 5);
			WebElement textField1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')]"))); // Adjust the locator to target the input field
			textField1.click();	         
			//textField.clear();
			textField1.sendKeys(Keys.CONTROL,"a");
			textField1.sendKeys(Keys.DELETE);
			textField1.sendKeys("560");

			//6. Validate the Slider Value
			String sliderValue = textField1.getAttribute("value");
			if ("560".equals(sliderValue)) {
				System.out.println("Slider updated to 560 successfully.");
			} else {
				System.out.println("Slider update failed. Current value: " + sliderValue);
			}

			// Scroll Down to the Slider section	           
			WebElement sliderSection2 = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']")); // Adjust the locator as needed
			js.executeScript("arguments[0].scrollIntoView(true);", sliderSection2);

			//7.  Select CPT Codes
			driver.findElement(By.xpath("//p[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"][contains(.,'99091')]/following-sibling::label//input[1]")).click();
			driver.findElement(By.xpath("//p[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"][contains(.,'99453')]/following-sibling::label//input[1]")).click();
			driver.findElement(By.xpath("//p[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"][contains(.,'99454')]/following-sibling::label//input[1]")).click();
			driver.findElement(By.xpath("//p[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"][contains(.,'99474')]/following-sibling::label//input[1]")).click();

			// Scroll up to the Slider section
			WebElement sliderSection3 = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1pr1g5o']")); // Adjust the locator as needed
			js.executeScript("arguments[0].scrollIntoView(true);", sliderSection3);

			//8,9. Validate Total Recurring Reimbursement
			WebElement reimbursementHeader = driver.findElement(By.xpath("//div[@class='MuiBox-root css-m1khva']/p[2][contains(.,'75600')]")); // Adjust locator
			String reimbursementValue = reimbursementHeader.getText();
			if (reimbursementValue.contains("75600")) {
				System.out.println("Total Recurring Reimbursement is correct: " + reimbursementValue);
			} else {
				System.out.println("Reimbursement value is incorrect. Found: " + reimbursementValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			driver.quit();
		}
	}

}
