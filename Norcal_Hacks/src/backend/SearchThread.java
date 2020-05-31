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
        try
        {
            myDriver.get( myLink );
            getRecipe();
            return;
        }
        catch(NoClassDefFoundError e)
        {
            myBook.addRecipe( null );
            return;
        }
        //System.out.println("complete");
    }
    
    private void getRecipe()
    {
        ArrayList<WebElement> ingredientss;
        ArrayList<WebElement> instructionss;
        ArrayList<String> ingredients;
        ArrayList<String> instructions;
        WebElement cookingTime;
        switch(myType)
        {
            case("foodnetwork"):
                ingredientss = (ArrayList<WebElement>)myDriver.findElements( By.className( "o-Ingredients__a-Ingredient" ) );
                ingredients = new ArrayList<String>();
                for (WebElement w : ingredientss)
                {
                    ingredients.add( w.getText().trim() );
                }
                instructionss = (ArrayList<WebElement>)myDriver.findElements( By.className( "o-Method__m-Step" ) );
                instructions = new ArrayList<String>();
                for (WebElement w : instructionss)
                {
                    instructions.add(w.getText().trim());
                }
                cookingTime = myDriver.findElement( By.className( "o-RecipeInfo__a-Description m-RecipeInfo__a-Description--Total" ) );
                myBook.addRecipe( new Recipe(ingredients, instructions, myLink, cookingTime.getText().trim()) );
                myDriver.quit();
                break;
                
            case("sallysbaking"):
                ingredientss = (ArrayList<WebElement>)myDriver.findElements( By.className( "tasty-recipes-ingredients" ) );
                String ingredientList = ingredientss.get( 0 ).getText().trim();
                ingredients = new ArrayList<String>();
                while (ingredientList.contains( "\n" ))
                {
                    ingredients.add( ingredientList.substring( 0, ingredientList.indexOf( "\n" ) ).trim() );
                    ingredientList = ingredientList.substring( ingredientList.indexOf( "\n" ) ).trim();
                }
                instructionss = (ArrayList<WebElement>)myDriver.findElements( By.className( "tasty-recipes-instructions" ) );
                String instructionList = instructionss.get( 0 ).getText().trim();
                instructions = new ArrayList<String>();
                while (instructionList.contains( "\n" ))
                {
                    instructions.add( instructionList.substring( 0, instructionList.indexOf( "\n" ) ).trim() );
                    instructionList = instructionList.substring( instructionList.indexOf( "\n" ) ).trim();
                }
                cookingTime = myDriver.findElement(By.className("tasty-recipes-total-time"));
                myBook.addRecipe( new Recipe(ingredients, instructions, myLink, cookingTime.getText().trim()) );
                myDriver.quit();
                break;
        }
    }
}
