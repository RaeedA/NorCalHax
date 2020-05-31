package backend;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import frontend.LoadingScreen;
import frontend.ResultScreen;

import javax.swing.JProgressBar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CookBook
{
    private ArrayList<Recipe> recipes;
    private ChromeOptions options;
    private WebDriver driver;
    private String[] accepted;
    private JProgressBar bar;
    private int threadNum = 3;
    private int completed;
    private int maxRecipes;
    private Stack<String> links;
    private String product;
    
    public CookBook()
    {
        recipes = new ArrayList<Recipe>();
        accepted = new String[] {"foodnetwork", "sallysbaking"};
        completed = 0;
    }
    
    public void startRunnerThread()
    {
        RunnerThread t = new RunnerThread(this);
        t.start();
        return;
    }
    
    public void getLinks(String product)
    {
        this.product  = product ;
        //Setup driver and put it on google
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions o = new ChromeOptions();
        o.addArguments( "--headless" );
        o.addArguments( "--ignore-certificate-errors" );
        o.addArguments( "detach=true" );
        options = o;
        driver = new ChromeDriver(o);
        driver.get("https://google.com");
        
        //Go to google and do the search
        WebElement element = driver.findElement( By.name( "q" ) );
        element.sendKeys( product + "recipes" );
        element.submit();
        
        //Get links
        element = driver.findElement( By.id( "rso" ) );
        List<WebElement> elements = element.findElements( By.cssSelector( "a" ) );
        links = new Stack<String>();
        for (WebElement w : elements)
        {
            String link = w.getAttribute( "href" );
            if(!link.contains( "google" ) && !link.contains( "youtube" ))
            {
                links.push( link.trim() );
            }
        }
        maxRecipes = links.size();
        bar = new JProgressBar(0, maxRecipes);
        bar.setValue( 0 );
        driver.quit();
    }
    
    public void getRecipes()
    {
        for (int i = 0; i < threadNum; i++)
        {
            WebDriver driver = new ChromeDriver(options);
            SearchThread thread = new SearchThread(driver, this);
            thread.start();
        }
    }
    
    public synchronized String[] getLink()
    {
        try
        {
            while (true)
            {
                String link = links.pop();
                for(String s : accepted)
                {
                    if (link.contains( s ))
                    {
                        return new String[] {link, s};
                    }
                }
                addRecipe(null);
            }
        }
        catch(EmptyStackException e)
        {
            return null;
        }
    }
    
    public void finish()
    {
        ((LoadingScreen)( bar.getParent().getParent().getParent().getParent().getParent())).setVisible( false );
        ResultScreen screen = new ResultScreen(this);
    }
    
    public synchronized void addRecipe(Recipe r)
    {
        if (r == null)
        {
        }
        else
        {
            recipes.add( r );
        }
        completed++;
        System.out.print( completed  + "/" + maxRecipes );
        bar.setValue( completed );
        System.out.println(hasLinks());
        if(!hasLinks())
        {
            finish();
        }
    }
    
    public synchronized boolean isDone()
    {
        return completed >= maxRecipes;
    }
    
    public JProgressBar getBar()
    {
        return bar;
    }
    
    public Recipe chooseBestRecipe()
    {
        if(recipes.size() == 0)
        {
            return null;
        }
        return recipes.get( 0 );
    }
    
    public int numCompleted()
    {
        return completed;
    }
    
    public int getTotalRecipes()
    {
        return maxRecipes;
    }
    
    public Queue<Recipe> sortByTime()
    {
        Queue<Recipe> queue = new PriorityQueue<Recipe>(new TimeComparator());
        for (Recipe r : recipes)
        {
            queue.add( r );
        }
        return queue;
    }
    
    public String toString()
    {
        String result = "";
        for (int i = 0; i < recipes.size(); i++)
        {
            result += (i+1) + ":\n" + recipes.get( i ) + "\n";
        }
        return result;
    }
    
    public boolean hasLinks()
    {
        return !links.isEmpty();
    }

    public String getProduct()
    {
        return product;
    }
}
