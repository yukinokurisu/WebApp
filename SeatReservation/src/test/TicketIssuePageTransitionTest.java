package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TicketIssuePageTransitionTest {
	  @Test
	  public void testGoogleSearch() throws InterruptedException {
	    // Optional. If not specified, WebDriver searches the PATH for chromedriver.
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\P743\\Downloads\\chromedriver_win32\\chromedriver.exe");

	    WebDriver driver = new ChromeDriver();
	    driver.get("http://localhost:8080/SeatReservationSystem/");
//	    Thread.sleep(5000);  // Let the user actually see something!
	    WebElement searchBox = driver.findElement(By.name("shop_name"));
	    searchBox.sendKeys("Tokyo");
	    searchBox = driver.findElement(By.name("password"));
	    searchBox.sendKeys("Tokyo2021");
	    searchBox.submit();
	    driver.navigate().to("http://localhost:8080/SeatReservationSystem/EventListServlet");
	    driver.navigate().to("http://localhost:8080/SeatReservationSystem/EventDetailsServlet?event_details=%E8%A9%B3%E7%B4%B0&event_id=1");
	    driver.findElement(By.name("ticket_issue")).click();
	    driver.findElement(By.name("print")).click();
//	    driver.quit();
	  }
}
