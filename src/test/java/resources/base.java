package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class base  {

	 public static WebDriver driver;
	 
	 public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	 public static ThreadLocal<ChromeOptions> ctOptions = new ThreadLocal<ChromeOptions>();
	 
	 
	 
	 static ChromeOptions chOptions;
	 
	protected Properties prop=new Properties();
	
	Logger log = LogManager.getLogger(base.class);
	 
	public WebDriver initialzedriver() throws IOException
	{
		
		File f = new File("C:\\Users\\MY PC\\Desktop\\Covid19 related ST associate data_Kshitij.xlsx");
		
		FileInputStream fs = new FileInputStream(f);
		
		XSSFWorkbook wb =  new XSSFWorkbook(fs);
		
		
		XSSFSheet sh ;
		
		
		String projectPath = System.getProperty("user.dir");
		
		FileInputStream fils=new FileInputStream("C:\\Users\\MY PC\\eclipse-workspace\\E2EProject\\src\\test\\java\\resources\\data.properties");
		prop.load(fils);
		
		//String browserName = System.getProperty("browser");
		String browserName = "chrome";
		
		System.out.println(browserName);
		if(browserName.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/chromedriver.exe");
			chOptions = new ChromeOptions();
			ctOptions.set(chOptions);
			if(browserName.contains("chrome"))
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
			System.setProperty("webdriver.gecko.driver", projectPath+"/Drivers/geckodriver.exe");
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
			System.setProperty("webdriver.edge.driver", projectPath+"/Drivers/msedgedriver.exe");
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
	
	
	public void searchProduct (String prodName , WebElement element )
	{
//		driver.findElement(new WebElementbEl)
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try {
			if(!(prodName==null))
			{
				if(!(prodName.isEmpty()) || !(prodName.equalsIgnoreCase(" ")))
				{
					waitSync(element);
					
					element.sendKeys(prodName);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean elementIsPresent (WebElement X)
	{
		WebDriverWait wait = new WebDriverWait(tdriver.get(), 4);
		
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
		WebDriverWait wait = new WebDriverWait(driver, 3);
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
	
	public void waitSync(WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver , 3);
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
