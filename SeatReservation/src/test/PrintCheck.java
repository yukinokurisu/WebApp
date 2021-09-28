package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrintCheck {
	  @Test


	  public void PrintCheck() throws InterruptedException {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\P744\\Downloads\\chromedriver_win32\\chromedriver.exe"); //各自のパスを入れる

		    WebDriver driver = new ChromeDriver();
		    driver.get("http://localhost:8080/SeatReservationSystem/");
		    WebElement searchBox = driver.findElement(By.name("shop_name"));
		    searchBox.sendKeys("Tokyo");
		    searchBox = driver.findElement(By.name("password"));
		    searchBox.sendKeys("tokyo");
		    searchBox.submit();
		    driver.navigate().to("http://localhost:8080/SeatReservationSystem/EventListServlet");
		    searchBox = driver.findElement(By.name("event_details"));
		    searchBox.submit();
		    searchBox = driver.findElement(By.name("ticket_list"));
		    searchBox.submit();
		    searchBox = driver.findElement(By.name("reissue"));
		    searchBox.submit();

		    driver.quit();
	}
}
