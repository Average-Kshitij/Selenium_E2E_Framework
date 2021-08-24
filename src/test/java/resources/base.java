package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class base  {

	 public static WebDriver driver;
	 
	 public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	 public static ThreadLocal<ChromeOptions> ctOptions = new ThreadLocal<ChromeOptions>();
	 
	 static ChromeOptions chOptions;
	 
	protected Properties prop=new Properties();
	
	Logger log = LogManager.getLogger(base.class);
	 
	public WebDriver initialzedriver() throws IOException
	{
		
		 	
		
		FileInputStream fils=new FileInputStream("C:\\Users\\MY PC\\eclipse-workspace\\E2EProject\\src\\test\\java\\resources\\data.properties");
		prop.load(fils);
		
		//String browserName = System.getProperty("browser");
		String browserName = "chrome";
		
		System.out.println(browserName);
		if(browserName.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\My Files\\Selenium pre-reqs\\chromedriver.exe");
			chOptions = new ChromeOptions();
			ctOptions.set(chOptions);
			if(browserName.contains("headless"))
			{
				ctOptions.get().addArguments("headless"); 
			}
			driver = new ChromeDriver(ctOptions.get());
			//DriverFactory.getDriverFactoryInstance().setDriverInstance(driver);
			tdriver.set(driver);
			
			log.info("Chome driver in base Class : " + tdriver.get());
		}
		
		else if(browserName.contains("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\My Files\\Selenium pre-reqs\\geckodriver.exe");
			FirefoxOptions fxOptions = new FirefoxOptions();
			if(browserName.contains("headless"))
			{
				fxOptions.addArguments("headless");
			}
			driver = new FirefoxDriver(fxOptions);
			tdriver.set(driver);
		}	
		else if(browserName.contains("edge"))
		{
			System.setProperty("webdriver.edge.driver", "D:\\My Files\\Selenium pre-reqs\\msedgedriver.exe");
			driver = new EdgeDriver();
			tdriver.set(driver);
		}			
		
		tdriver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		tdriver.get().manage().window().maximize();
		return tdriver.get();
		
	}
	
	
	public String getScreenShot(String testName , WebDriver driver)
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File f=ts.getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir")+"\\reports\\"+testName+"-"+".png";
		try {
			FileUtils.copyFile(f, new File(destFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destFile;
	}
	
	
	public void searchProduct (String prodName )
	{
		try {
			if(!(prodName==null))
			{
				if(!(prodName.isEmpty()) || !(prodName.equalsIgnoreCase(" ")))
				{
					tdriver.get().findElement(By.xpath("")).sendKeys(prodName);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
