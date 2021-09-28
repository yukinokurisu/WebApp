package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TicketCheckTest {
	  @Test
	  public void TicketCheck_Test() throws InterruptedException {
	    // Optional. If not specified, WebDriver searches the PATH for chromedriver.
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\P744\\Downloads\\chromedriver_win32\\chromedriver.exe"); //各自のパスを入れる

	    WebDriver driver = new ChromeDriver();
	    driver.get("http://localhost:8080/SeatReservationSystem/");
	    WebElement searchBox = driver.findElement(By.name("shop_name"));
	    searchBox.sendKeys("Tokyo");
	    searchBox = driver.findElement(By.name("password"));
	    searchBox.sendKeys("tokyo");
	    searchBox.submit();
	    searchBox = driver.findElement(By.name("ticket_check"));
	    searchBox.click();
	    Thread.sleep(5000);  // Let the user actually see something!
	    driver.quit();
	  }
}
