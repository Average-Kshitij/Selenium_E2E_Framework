package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class base  {

	public static WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<ChromeOptions> ctOptions = new ThreadLocal<ChromeOptions>();
	private String browserName;
	private boolean isHeadless = false;
	static ChromeOptions chOptions;
	protected Properties prop = new Properties();
	Logger log = LogManager.getLogger(base.class);
	protected int explWaitTime =  Integer.parseInt(prop.getProperty("explicitWait"));
	
	@SuppressWarnings({ "unused","resource"})
	public WebDriver initialzedriver() throws IOException
	{
		String projectPath = System.getProperty("user.dir");
				
		File f = new File(projectPath+"/Covid19 related ST associate data_Kshitij.xlsx");
		
		FileInputStream fs = new FileInputStream(f);
		
		FileInputStream fils=new FileInputStream(projectPath+"/resource/data.properties");
		prop.load(fils);
		
		browserName = "chrome";
		
		System.out.println(browserName);
		
		switch(browserName.toUpperCase())
		{
		case "CHROME":
			
			chOptions = new ChromeOptions();
			ctOptions.set(chOptions);
			if (isHeadless) {
				ctOptions.get().addArguments("headless");
			}
			
			driver = WebDriverManager.chromedriver().capabilities(chOptions).create();

			tdriver.set(driver);

			log.info("Chome driver in base Class : " + tdriver.get());
			break;

		case "FIREFOX":
			
			FirefoxOptions fxOptions = new FirefoxOptions();
			if (browserName.contains("headless")) {
				fxOptions.addArguments("headless");
			}
			
			driver = WebDriverManager.firefoxdriver().capabilities(fxOptions).create();
			tdriver.set(driver);
			break;

		case "EDGE":
			EdgeOptions edgeOptions = new EdgeOptions();
			
			driver = WebDriverManager.edgedriver().create();
			tdriver.set(driver);
			break;

		default:
			break;
		}
		
		tdriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
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
	
	
	public void searchProduct (String prodName , WebElement element )
	{
		try {
			if(!(prodName==null))
			{
				if(!(prodName.isEmpty()) || !(prodName.equalsIgnoreCase(" ")))
				{
					waitSync(element , 3);
					
					element.sendKeys(prodName);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean elementIsPresent (WebElement X)
	{
		WebDriverWait wait = new WebDriverWait(tdriver.get(), Duration.ofSeconds(explWaitTime));
		
		boolean popUp=false;
		
		try
		{
	//		wait.until(ExpectedConditions.visibilityOf(X));
			popUp=X.isDisplayed();
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
		return popUp;
		
	}
	
	
	public void enterText(WebElement textBox , String text)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explWaitTime));
		try {
			if (!(text == null)) {
				
				
			}
		} catch (Exception e) {
		}
	}
	
	
	
	public ResultSet connectTodatabase(String query)
	{
		ResultSet output = null;
		
		String ip="localhost", port ="3306";
		try
		{
		
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		
		Connection con = DriverManager.getConnection("jdbc:mysql//"+ip+":"+port+"/kshtest", "root", "root");
		
		Statement st =con.createStatement();
		
		output = st.executeQuery(query);
		
		con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
		
		
	}
	
	public void waitSync(WebElement element , int waitTimeInSec) 
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(waitTimeInSec));
		try
		{
		log.info("In wait sync method");	
					
		wait.until(ExpectedConditions.visibilityOf(element));
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		log.info("Exit wait sync method without exception");
		
		}
		catch(Exception e)
		{
			log.error("Exception in waitSync method");
			
			e.printStackTrace();
		}
		
		driver.findElement(By.className("kshitij"));
	}
	
	
}
