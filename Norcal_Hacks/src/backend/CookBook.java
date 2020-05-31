package backend;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CookBook
{
    private ArrayList<Recipe> recipes;
    ChromeDriver driver;
    
    public void CookBook()
    {
        recipes = new ArrayList<Recipe>();
        
        //Setup driver and put it on google
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions o = new ChromeOptions();
        o.addArguments( "--headless" );
        o.addArguments( "--ignore-certificate-errors" );
        o.addArguments( "detach=true" );
        WebDriver driver = new ChromeDriver(o);
        driver.get("https://google.com");
    }
    
    public void getRecepies(String product)
    {
    }
    
    private boolean isValidLink(String link, String[] accepted)
    {
        if (!link.contains( "google" ));
        {
            for(String s : accepted)
            {
                if (link.contains( s ))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
