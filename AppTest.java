package com.qait.automation.tatoc;


import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class AppTest {
	
	WebDriver driver;
	
	
	@BeforeTest
	 public void launchBrowser(  )
	    {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\arpanmishra\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
	      
	    }
	
	@Test
	 public void step_01_lauchURL(  )
	    {
			
			driver.get("http://10.0.1.86/tatoc");
	      
	    }
	
	
	@Test
	public void step_02_ClickButtonTest() {
		
		driver.findElement(By.cssSelector("a")).click();
		
	}
	
	@Test
	public void step_03_ClickOnGreenBox() {
		
		driver.findElement(By.className("greenbox")).click();
		
	}
	
	@Test
	public void step_04_ClickOnBox2() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("main")));

		String result = driver.findElement(By.id("answer")).getAttribute("class");
		Boolean condition = true;
		while(condition) {
			driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click();
			driver.switchTo().frame(driver.findElement(By.id("child")));
			String secondresult = driver.findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().parentFrame();
			
			if(result.equals(secondresult))
			{
				condition = false;
			}
			
			}
		driver.findElement(By.cssSelector("body > center > a:nth-child(9)")).click();
		driver.switchTo().defaultContent();

		
	}
	
	@Test
	public void step_05_DrangAndDropTest() {
		
		WebElement From=driver.findElement(By.xpath("//*[@id='dragbox']"));
		WebElement To=driver.findElement(By.xpath("//*[@id='dropbox']"));
		Actions act=new Actions(driver);
		
		act.dragAndDrop(From, To).build().perform();	
		driver.findElement(By.cssSelector("body > div > div.page > a")).click();
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();
		
	}
	
	@Test
	public void step_06_LaunchNewWindowTest() {
		
		ArrayList<String> str = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(str.get(1));
		driver.findElement(By.cssSelector("#name")).sendKeys("Arpan");
		driver.findElement(By.cssSelector("#submit")).click();
		 driver.switchTo().window(str.get(0));
		  driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(6)")).click();
		
		  
	}
	
	@Test
	public void step_07_GenerateToekenTest() {
		  driver.findElement(By.xpath("//a[contains(text(),'Generate Token')]")).click();
		   
		   // driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(8)")).click();
		 
		  String s=driver.findElement(By.xpath("//*[@id='token']")).getText();
		  System.out.println(s);
		  String[] tokenValue= s.split("\\s");//
		  System.out.println(tokenValue[1]);
		  String token= tokenValue[1];
		  Cookie cookie = new Cookie("Token",token);
		  driver.manage().addCookie(cookie);
		  driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();
		
	}
	
   
}
