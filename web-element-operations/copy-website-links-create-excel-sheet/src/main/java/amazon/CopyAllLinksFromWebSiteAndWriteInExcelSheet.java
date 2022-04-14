package amazon;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CopyAllLinksFromWebSiteAndWriteInExcelSheet {
	public static void main(String[] args) throws Exception {

		String projectpath = System.getProperty("user.dir");
//		File file = new File("D:\\ExcelFile\\railwayy.xlsx");
		File file = new File(projectpath + "/excel/railwireinternet.xlsx");
		FileOutputStream fos = new FileOutputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Active Links");
		sheet.getRow(0).createCell(1).setCellValue("Broken Links");
		sheet.getRow(0).createCell(2).setCellValue("NullValues");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.railwireinternet.com/tamilnadu");
//		driver.get("https://www.amazon.in/");

		List<WebElement> links = driver.findElements(By.tagName("a"));
//		List<WebElement> weblinks = driver.findElements(By.xpath("//a[@href]"));
		System.out.println("Total Links Are " + links.size());
//		System.out.println("Total Links Are " +weblinks.size());

		int i = 1;
		int j = 1;
		int z = 1;

		for (WebElement link : links) {
			sheet.createRow(i);
			String linkURL = link.getAttribute("href");
//			System.out.println(j + " ." + linkURL);
			try {
				URL url = new URL(linkURL);

				URLConnection urlConnection = url.openConnection();
				HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
				httpURLConnection.setConnectTimeout(5000);
				httpURLConnection.connect();
				sheet.createRow(i);
				if (httpURLConnection.getResponseCode() == 200) {
					sheet.getRow(i).createCell(0).setCellValue(linkURL);
					i++;
				} else {
					sheet.getRow(j).createCell(1).setCellValue(linkURL);
					j++;
				}
//					System.out.println(". " + linkURL + " - " + httpURLConnection.getResponseMessage());
//				else
//					System.err.println(". " + linkURL + " - " + httpURLConnection.getResponseMessage() + " - is a broken link");

				httpURLConnection.disconnect();

			} catch (Exception e) {
//				sheet.getRow(z).createCell(2).setCellValue("NullPointerException..!");
//				z++;
//				System.err.println("NullPointerException..!");
//				System.err.println(e.getMessage());
//				System.err.println(e.getCause());
				e.printStackTrace();
			}

		}
		workbook.write(fos);
		workbook.close();
		System.out.println("Excel File Created ");
		driver.quit();

	}

}
