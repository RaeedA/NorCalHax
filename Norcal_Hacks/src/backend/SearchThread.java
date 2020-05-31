package backend;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchThread extends Thread
{
    private WebDriver myDriver;
    private String myType;
    private String myLink;
    private CookBook myBook;
    
    public SearchThread(WebDriver d, String type, String link, CookBook book)
    {
        myDriver = d;
        myType = type;
        myLink = link;
        myBook = book;
    }
    
    @Override
    public void run()
    {
        myDriver.get( myLink );
        getRecipe();
    }
    
    private void getRecipe()
    {
        ArrayList<WebElement> ingredients;
        ArrayList<WebElement> instructions;
        switch(myType)
        {
            case("foodnetwork"):
                ingredients = (ArrayList<WebElement>)myDriver.findElements( By.className( "o-Ingredients__a-Ingredient" ) );
                instructions = (ArrayList<WebElement>)myDriver.findElements( By.className( "o-Method__m-Step" ) );
                myBook.addRecipe( new Recipe(ingredients, instructions, myLink) );
                myDriver.quit();
                break;
        }
    }
}
