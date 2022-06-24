package com.Amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductDetails {
	WebDriver driver;
	
	public void LaunchWebsite() {
		
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
	}
	
	public void SerchingForProduct() {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("phone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		
		driver.findElement(By.xpath("((//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium'])[4]//li//span[@class='a-size-base a-color-base'])[1]")).click();
		
		
		List<WebElement> phones =  driver.findElements(By.xpath("(//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium'])[4]//li//span[@class='a-size-base a-color-base']"));
		
		for (WebElement phone : phones) {
			String txt = phone.getText();
			System.out.println(txt);
			if(txt == "Redmi") {
				phone.click();
				
				break;
			}
		
		}
		
//		List<WebElement> results = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
//		int number =1;
//		for (WebElement result : results) {
//			System.out.println(number+" : "+result.getText());
//			number++;
//		}
		
		
		
		
//		List<WebElement> brands = driver.findElements(By.xpath("(//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium'])[4]/li[@aria-label]"));
		
		/*
		List<WebElement> brands = driver.findElements(By.xpath("(//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium'])[4]//li//span[@class='a-size-base a-color-base']"));
		
		int no=1;
		for (WebElement brand : brands) {
			
			
			String txt = brand.getText();
			
			System.out.println(no+" : "+txt);
			if(txt =="Samsung") {
				brand.click();
				break;
			}
			*/
			
//		}
		/*
		 * List<WebElement> hbrands = driver.findElements(By.
		 * xpath("(//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium'])[4]//li//ul//li[@class='a-spacing-micro']"
		 * )); List<WebElement> fbrands = driver.findElements(By.
		 * xpath("(//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium'])[4]//li//span[@class='a-size-base a-color-base']"
		 * )); for (WebElement brand : brands) { String text = brand.getText();
		 * System.out.println(text); if(text == "Samsung") { brand.click(); break; }
		 * 
		 * }
		 */
		
		
		
		/*
		 * int number=1; for (WebElement hbrand : hbrands) {
		 * 
		 * String htxt = hbrand.getText(); System.out.println(number+ " : " +htxt);
		 * number++; } int no =1; for (WebElement fbrand : fbrands) {
		 * 
		 * String ftxt = fbrand.getText();
		 * 
		 * System.out.println(number+ " : " +ftxt); no++; }
		 */
		
		
//		driver.close();
	}
	public static void main(String[] args) {
		ProductDetails pd = new ProductDetails();
		pd.LaunchWebsite();
		pd.SerchingForProduct();
		
	}

}
