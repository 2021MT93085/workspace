package workspace;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainScript {
	
	private static final WebDriver NULL = null;
	static WebDriver driver;
	WebDriver edgeDriver;
	static String qaURL = "https://www.saucedemo.com/";
	static Boolean blnStatus = true;
	
	public static void InitializeBrowser(String BrowserName) throws InterruptedException {
		if(!(BrowserName.isEmpty())){
			
	    	if(BrowserName.toLowerCase().contains("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/resources/chromedriver.exe");
				driver = new ChromeDriver();	
			}
	    	else if(BrowserName.toLowerCase().contains("edge")){
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/resources/edgedriver.exe");
				driver = new EdgeDriver();
			}
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, 90);
			
		   
		    driver.get(qaURL); 
		    
		    //Login By username and password
			try {
				System.out.println("About to Login");
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name("user-name")));
				driver.findElement(By.name("user-name")).clear();
				driver.findElement(By.name("user-name")).sendKeys("");
				driver.findElement(By.name("user-name"))
						.sendKeys("standard_user");
				driver.findElement(By.name("password"))
				.sendKeys("secret_sauce");
				driver.findElement(By.name("login-button")).click();
				
				//Implementing Thread.sleep
				Thread.sleep(10000);
				
				System.out.println("Login Successful");
				
			} catch (ElementNotVisibleException rc) {
				blnStatus = false;
				System.out.println("Login URL failed for saucedemo" + rc.getMessage());
			}
			
			if (blnStatus){
				try {
					System.out.println("Reading List Box Contents");
					//Usage of staic xpath
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select")));
					//Usage of Dynamic Xpath
					List <WebElement> options = driver.findElements(By.xpath("//select[@class='product_sort_container']/option"));
					for(WebElement option: options) {
						String value = option.getAttribute("text");
						System.out.println(value);
					}
	
				} catch (ElementNotVisibleException rc) {
					blnStatus = false;
					System.out.println("Read the List Box" + rc.getMessage());
				}
			}
		}
	}
	
	
			
    

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		InitializeBrowser("edge");
		driver.close();
		driver.quit();
	}

}
