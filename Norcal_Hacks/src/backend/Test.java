package backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test
{
    public static void main(String[] args)
    {
        //Initialize driver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions o = new ChromeOptions();
        o.addArguments( "--headless" );
        o.addArguments( "--ignore-certificate-errors" );
        o.addArguments( "detach=true" );
        WebDriver driver = new ChromeDriver(o);
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
        
        //Creates recipes
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        for (String s : links)
        {
            driver.get( s );
            ArrayList<WebElement> ingredients = (ArrayList<WebElement>)driver.findElements( By.className( "o-Ingredients__a-Ingredient" ) );
            ArrayList<WebElement> instructions = (ArrayList<WebElement>)driver.findElements( By.className( "o-Method__m-Step" ) );
            recipes.add( new Recipe(ingredients, instructions, s) );
        }
        
        //Print results
        System.out.println(recipes.get( 0 ));
        
        //Close everything
        driver.quit();
        System.out.println("Done!");
        System.exit( 0 );
    }
    
    public static boolean isUseful(String link)
    {
        return !link.contains( "google" ) && link.contains( "foodnetwork") ;
    }
}
