package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
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
	    searchBox.sendKeys("Tokyo");
	    searchBox.submit();
	    String LoginError = "ログインに失敗しました。";
	    driver.findElement(By.id("message")).getText();
	    assertEquals(LoginError,"message");

//	    driver.quit();
	  }
}
