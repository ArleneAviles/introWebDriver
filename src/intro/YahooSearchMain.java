package intro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YahooSearchMain {
	
	

	public static void main(String[] args) {
		//INICIALIZACION DE SYSTEM.SETPROPERTY()
		System.setProperty("webdriver.edge.driver", "C:\\Windows");
	    WebDriver driver;
		driver = new ChromeDriver(); //arranca el navegador
		driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
		driver.get("http://www.yahoo.com"); //navega a la url que le indiquemos
		WebElement searchBox = driver.findElement(By.id("uh-search-box"));
		WebElement searchButton = driver.findElement(By.id("uh-search-button"));
		
		searchBox.clear();
		searchBox.sendKeys("Selenium");
		searchButton.click();
		
		WebElement seleniumLink = driver.findElement(By.linkText("Selenium - Web Browser Automation"));
		seleniumLink.click();
		
		ArrayList<String> windowIds = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Number of windows: " + windowIds.size());
		
		for(String windowId: driver.getWindowHandles()) {
			driver.switchTo().window(windowId);
		}
		
		WebElement downloadsLink = driver.findElement(By.linkText("Download"));
		downloadsLink.click();
		
		driver.close();

	}

}
