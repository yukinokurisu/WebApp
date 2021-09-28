package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestList {
	  @Test
	  public void testGoogleSearch() throws InterruptedException {
	    // Optional. If not specified, WebDriver searches the PATH for chromedriver.
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\\\P745\\Downloads\\chromedriver_win32\\chromedriver.exe"); //各自のパスを入れる

	    //ログインページを開く
	    WebDriver driver = new ChromeDriver();
	    driver.get("http://localhost:8080/SeatReservationSystem/LoginServlet");
	    //Thread.sleep(5000);  // Let the user actually see something!


	    //ショップネームに文字列入力、からのパスワード入力
	    WebElement loginBox = driver.findElement(By.name("shop_name"));
	    loginBox.sendKeys("Tokyo");
	    loginBox = driver.findElement(By.name("password"));
	    loginBox.sendKeys("Tokyo2");
	    loginBox.submit();
	   // Thread.sleep(5000);  // Let the user actually see something!




	    loginBox = driver.findElement(By.name("event_list"));

	    String handle_one =  driver.getWindowHandle();

	    loginBox.click();
	    Thread.sleep(1000);
	   // driver.quit();


        String newHandle = null;
        for (String id : driver.getWindowHandles()) {
            if (!id.equals(handle_one)) {
                newHandle = id;
            }
        }
        //newHandleにハンドルを移す
        driver.switchTo().window(newHandle);


	    loginBox = driver.findElement(By.name("searchEvent"));
	    loginBox.click();
	    Thread.sleep(1000);

	    loginBox = driver.findElement(By.name("event_detail"));
	    loginBox.click();
	    Thread.sleep(1000);

	    driver.navigate().back();
	    Thread.sleep(1000);

	    driver.navigate().back();
	    Thread.sleep(1000);

	    loginBox = driver.findElement(By.name("newEvent"));
	    loginBox.click();
	    Thread.sleep(1000);

	    driver.navigate().back();










	  }
}