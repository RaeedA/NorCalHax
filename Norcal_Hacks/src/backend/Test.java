package backend;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test
{
    public static void main(String[] args)
    {
        //Initialize driver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        
        //Go to google and do the search
        WebElement element = driver.findElement( By.name( "q" ) );
        element.sendKeys( "vanilla cupcake recipes" );
        element.submit();
        
        //Get links
        element = driver.findElement( By.id( "rso" ) );
        List<WebElement> elements = element.findElements( By.cssSelector( "a" ) );
        Set<String> links = new HashSet<String>();
        for (WebElement w : elements)
        {
            String link = w.getAttribute( "href" );
            if (isUseful(link))
            {
                links.add( link.trim() );
            }
        }
        
        for (String s : links)
        {
            driver.get( s );
            List<WebElement> ingredients = driver.findElements( By.className( "o-Ingredients__m-Body" ) );
            for (WebElement w : ingredients)
            {
                System.out.println(w.getText());
            }
            
        }
                
        /*for (WebElement w : elements)
        {
            System.out.println(w.getAttribute( "href" ));
        }*/

        driver.quit();
        System.out.println("Done!");
        System.exit( 0 );
        return;
    }
    
    public static boolean isUseful(String link)
    {
        return !link.contains( "google" ) && link.contains( "foodnetwork") ;
    }
}
