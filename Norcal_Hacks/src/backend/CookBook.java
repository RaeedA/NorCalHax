package backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriverException;

public class CookBook
{
    private ArrayList<Recipe> recipes;
    private ChromeOptions options;
    private WebDriver driver;
    private String[] accepted;
    private int threadCounter;
    
    public CookBook()
    {
        recipes = new ArrayList<Recipe>();
        accepted = new String[] {"foodnetwork"};
        threadCounter = 0;
        
        //Setup driver and put it on google
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions o = new ChromeOptions();
        o.addArguments( "--headless" );
        o.addArguments( "--ignore-certificate-errors" );
        o.addArguments( "detach=true" );
        options = o;
        driver = new ChromeDriver(o);
        driver.get("https://google.com");
    }
    
    public void getRecipes(String product)
    {
        //Go to google and do the search
        WebElement element = driver.findElement( By.name( "q" ) );
        element.sendKeys( product + "recipes" );
        element.submit();
        
        //Get links
        element = driver.findElement( By.id( "rso" ) );
        List<WebElement> elements = element.findElements( By.cssSelector( "a" ) );
        HashSet<String> links = new HashSet<String>();
        for (WebElement w : elements)
        {
            String link = w.getAttribute( "href" );
            if(!link.contains( "google" ))
            {
                links.add( link.trim() );
            }
        }
        
        for (String s : links)
        {
            searchLink(s);
        }
        
        driver.quit();
    }
    
    private void searchLink(String link)
    {
        for(String s : accepted)
        {
            if (link.contains( s ))
            {
                WebDriver d = new ChromeDriver();
                SearchThread t = new SearchThread(d, s, link, this);
                threadCounter++;
                t.start();
            }
        }
    }
    
    public void addRecipe(Recipe r)
    {
        recipes.add( r );
        threadCounter--;
    }
    
    public boolean isDone()
    {
        //System.out.print(threadCounter);
        return threadCounter == 0;
    }
    
    public String toString()
    {
        String result = "";
        for (int i = 0; i < recipes.size(); i++)
        {
            result += (i+1) + ":\n";
            result += recipes.get( i );
        }
        return result;
    }
}
