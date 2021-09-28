package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewEventCheck {
	@Test
	public void testGoogleSearch() throws InterruptedException {
		// Optional. If not specified, WebDriver searches the PATH for chromedriver.
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\P744\\Downloads\\chromedriver_win32\\chromedriver.exe"); //各自のパスを入れる

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/SeatReservationSystem/");
		WebElement searchBox = driver.findElement(By.name("shop_name"));
		searchBox.sendKeys("Tokyo");
		searchBox = driver.findElement(By.name("password"));
		searchBox.sendKeys("tokyo");
		searchBox.submit();
		searchBox = driver.findElement(By.name("new_event"));

		//元のページのハンドルを記憶
		String Handle = driver.getWindowHandle();

		searchBox.click();

		//次のタブのハンドルを用意し、タブが新しく開かれていたらnewHandleに代入する
		String newHandle = null;
		for (String id : driver.getWindowHandles()) {
			if (!id.equals(Handle)) {
				newHandle = id;
			}
		}
		//newHandleにハンドルを移す
		driver.switchTo().window(newHandle);

		searchBox = driver.findElement(By.name("title"));
		searchBox.sendKeys("アンパンマン");
		searchBox = driver.findElement(By.name("start_at"));
		searchBox.sendKeys("2021-06-25");
		searchBox = driver.findElement(By.name("capacity"));
		searchBox.sendKeys("4");

		//元のページのハンドルを記憶
		Handle = driver.getWindowHandle();

		searchBox.submit();

		//次のタブのハンドルを用意し、タブが新しく開かれていたらnewHandleに代入する
		newHandle = null;
		for (String id : driver.getWindowHandles()) {
			if (!id.equals(Handle)) {
				newHandle = id;
			}
		}
		//newHandleにハンドルを移す

		Thread.sleep(5000); // Let the user actually see something!
		driver.quit();
	}
}
