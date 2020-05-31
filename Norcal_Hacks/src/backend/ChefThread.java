package backend;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ChefThread extends Thread
{
    private String type;
    private ArrayList<WebElement> ingredients;
    private ArrayList<WebElement> instructions;
    private WebElement cookingTime;
    private CookBook book;
    private String link;
    
    public ChefThread(String link, String type, ArrayList<WebElement> ingredients, ArrayList<WebElement> instructions, WebElement cookingTime, CookBook book)
    {
        this.link = link;
        this.type = type;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.book = book;
    }
    
    @Override
    public void run()
    {
        ArrayList<String> finalIngredients;
        ArrayList<String> finalInstructions;
        switch(type)
        {
            case("foodnetwork"):
            {
                try
                {
                    finalIngredients = new ArrayList<String>();
                    for (WebElement w : ingredients)
                    {
                        finalIngredients.add( w.getText().trim() );
                    }
                
                    finalInstructions = new ArrayList<String>();
                    for (WebElement w : instructions)
                    {
                        finalInstructions.add(w.getText().trim());
                    }
                    book.addRecipe( new Recipe(finalIngredients, finalInstructions, link, cookingTime.getText().trim()) );
                    break;
                }
                catch(NoSuchElementException e)
                {
                    book.addRecipe(null);
                    break;
                }
                catch(NoClassDefFoundError e)
                {
                    book.addRecipe( null );
                    break;
                }
            }
                
            case("sallysbaking"):
            {
                try
                {
                    String ingredientList = ingredients.get( 0 ).getText().trim();
                    finalIngredients = new ArrayList<String>();
                    while (ingredientList.contains( "\n" ))
                    {
                        finalIngredients.add( ingredientList.substring( 0, ingredientList.indexOf( "\n" ) ).trim() );
                        ingredientList = ingredientList.substring( ingredientList.indexOf( "\n" ) ).trim();
                    }
                    
                    String instructionList = instructions.get( 0 ).getText().trim();
                    finalInstructions = new ArrayList<String>();
                    while (instructionList.contains( "\n" ))
                    {
                        finalInstructions.add( instructionList.substring( 0, instructionList.indexOf( "\n" ) ).trim() );
                        instructionList = instructionList.substring( instructionList.indexOf( "\n" ) ).trim();
                    }
                    book.addRecipe( new Recipe(finalIngredients, finalInstructions, link, cookingTime.getText().trim()) );
                    break;
                    }
                catch(IndexOutOfBoundsException e)
                {
                    book.addRecipe(null);
                    break;
                }
            }
        }
    }
}
