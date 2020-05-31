package backend;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchThread extends Thread
{
    private WebDriver myDriver;
    private String myType;
    private String myLink;
    private CookBook myBook;
    
    public SearchThread(WebDriver d, CookBook book)
    {
        myDriver = d;
        myBook = book;
    }
    
    @Override
    public void run()
    {
        while (myBook.hasLinks())
        {
            getNewLink();
            try
            {
                myDriver.get( myLink );
                getRecipe();
            }
            catch(NoClassDefFoundError e)
            {
                myBook.addRecipe( null );
            }
        }
        myDriver.quit();
    }
    
    public void getNewLink()
    {
        String[] results = myBook.getLink();
        myLink = results[0];
        myType = results[1];
    }
    
    private void getRecipe()
    {
        ArrayList<WebElement> ingredients = null;
        ArrayList<WebElement> instructions = null;
        WebElement cookingTime = null;
        switch(myType)
        {
            case("foodnetwork"):
                try {
                ingredients = (ArrayList<WebElement>)myDriver.findElements( By.className( "o-Ingredients__a-Ingredient" ) );
                instructions = (ArrayList<WebElement>)myDriver.findElements( By.className( "o-Method__m-Step" ) );
                cookingTime = myDriver.findElement( By.cssSelector( ".o-RecipeInfo__a-Description.m-RecipeInfo__a-Description--Total" ) );
                break;
                }
                catch(NoSuchElementException e)
                {
                    myBook.addRecipe(null);
                    return;
                }
                
            case("sallysbaking"):
            {
                try
                {
                    ingredients = (ArrayList<WebElement>)myDriver.findElements( By.className( "tasty-recipes-ingredients" ) );
                    instructions = (ArrayList<WebElement>)myDriver.findElements( By.className( "tasty-recipes-instructions" ) );
                    cookingTime = myDriver.findElement(By.className("tasty-recipes-total-time"));
                    break;
                }
                catch(IndexOutOfBoundsException e)
                {
                    myBook.addRecipe(null);
                    return;
                }
            }
        }
        ChefThread t = new ChefThread(myLink, myType, ingredients, instructions, cookingTime, myBook);
    }
}
