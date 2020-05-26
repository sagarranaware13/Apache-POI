package poiexceldriven;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

	WebDriver driver;
	@Test
	public void DataDrivenTest() throws IOException {
		DataDriven d=new DataDriven();
		ArrayList<String> arrayList=d.getData("typeoftest","fun");

		String url="https://www.google.com/";
		driver.get(url);
		driver.manage().window().maximize();
		
		
		String actualTitile=driver.getTitle();
		String expectedTitle="Google";
		  
		assertEquals(actualTitile, expectedTitle);
		System.out.println("Title verified : The Google is opned");
		
		driver.manage().window().maximize();
		
		for (String str : arrayList) {
			System.out.println(str);
		}
		
		WebElement serach=driver.findElement(By.xpath("//input[@title='Search']"));
		serach.sendKeys(arrayList.get(2));
		
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]")).click();
		
		String title=driver.getTitle();
		
		assertEquals(title, "sagar ranaware - Google Search");
		
	}
	
	@BeforeMethod
	  public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
	}
	  
	  @AfterMethod
	  public void tearDown() {
	  
		  driver.quit();
	  }

	
}
