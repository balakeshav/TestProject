package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BookFlight {

	static WebDriver driver;

	
	@Given("User is on the BlazeDemo website")
	public void user_is_on_the_blaze_demo_website() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://blazedemo.com/");
		driver.manage().window().maximize();
	}

	@When("User Selects From and To")
	public void user_selects_from_and_to() {
		Select fromSource = new Select(driver.findElement(By.xpath("//select[@name='fromPort']")));
		fromSource.selectByValue("Paris");
		Select toDestination = new Select(driver.findElement(By.xpath("//select[@name='toPort']")));
		toDestination.selectByValue("Rome");
	}

	@When("clicks on Find Flights button")
	public void clicks_on_find_flights_button() {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@Then("all the available flights should be displayed")
	public void all_the_available_flights_should_be_displayed() {
		List<WebElement> noOfFlights = driver.findElements(By.xpath("//tbody/tr"));
		System.out.println("Number of flight are " + noOfFlights.size());
	}

	@Then("user selects a flight and clicks on the choose flight button")
	public void user_selects_a_flight_and_clicks_on_the_choose_flight_button() {
		driver.findElement(By.xpath("//tbody/tr[3]/td/input[@type='submit']")).click();
	}

	@Then("page to enter all the details displayed")
	public void page_to_enter_all_the_details_displayed() throws IOException {
		// Fill the user details
		
				FileInputStream fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\TestData.properties");
				Properties p = new Properties();
				p.load(fis);
				String name = p.getProperty("Name");
				driver.findElement(By.id("inputName")).sendKeys(name);
				String address = p.getProperty("Address");
				driver.findElement(By.id("address")).sendKeys(address);
				String city = p.getProperty("City");
				driver.findElement(By.id("city")).sendKeys(city);
				String state = p.getProperty("State");
				driver.findElement(By.id("state")).sendKeys(state);
				String zip = p.getProperty("ZipCode");
				driver.findElement(By.id("zipCode")).sendKeys(zip);

				// Card type
				Select cardType = new Select(driver.findElement(By.id("cardType")));
				cardType.selectByValue("visa");
				String cardNumber = p.getProperty("CreditCardNumber");
				driver.findElement(By.id("creditCardNumber")).sendKeys(cardNumber);
				String month = p.getProperty("Month");
				driver.findElement(By.id("creditCardMonth")).clear();
				driver.findElement(By.id("creditCardMonth")).sendKeys(month);
				String year = p.getProperty("Year");
				driver.findElement(By.id("creditCardYear")).clear();
				driver.findElement(By.id("creditCardYear")).sendKeys(year);
				String nameOnCard = p.getProperty("NameonCard");
				driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);
				driver.findElement(By.id("rememberMe")).click();
	}

	@Then("user clicks on Purchase flight button after entering all the details")
	public void user_clicks_on_purchase_flight_button_after_entering_all_the_details() {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@Then("flight booked message should be displayed with Id")
	public void flight_booked_message_should_be_displayed_with_id() {
		String id= driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
		System.out.println("Id is "+id);
		driver.close();
		driver.quit();
	}
}
