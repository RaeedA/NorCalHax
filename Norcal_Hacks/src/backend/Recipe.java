package backend;

import java.awt.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

public class Recipe
{
    private ArrayList<Ingredient> myIngredients;
    private ArrayList<String> myInstructions;
    private String myLink;
    
    public Recipe(ArrayList<WebElement> ingredients, ArrayList<WebElement> instructions, String link)
    {
        myLink = link;
        myIngredients = new ArrayList<Ingredient>();
        myInstructions = new ArrayList<String>();
        Pattern p = Pattern.compile( "^([\\d\\s\\/]+)(\\w+)" );
        for (WebElement w : ingredients)
        {
            String text = w.getText().trim();
            Matcher m = p.matcher( w.getText());
            if(m.find())
            {
                myIngredients.add( new Ingredient(m, text.substring( m.end() ).trim()) );
            }
            else
            {
                myIngredients.add( new Ingredient(text));
            }
        }
        for (WebElement w : instructions)
        {
            myInstructions.add( w.getText().trim() );
        }
    }

    /**
     * toString method for this class
     * @return string representation
     */
    @Override
    public String toString()
    {
        String result = "Ingredients: ";
        for (Ingredient i : myIngredients)
        {
            result += i + "; ";
        }
        result = result.substring( 0, result.length()-2 ) + "\nInstructions: ";
        for (String s : myInstructions)
        {
            result += s + " ";
        }
        result = result.substring( 0, result.length()-1 ) + "\nLink: ";
        result += myLink;
        return result;
    }

    /**
     * @return Returns myIngredients.
     */
    public ArrayList<Ingredient> getMyIngredients()
    {
        return myIngredients;
    }

    /**
     * @return Returns myInstructions.
     */
    public ArrayList<String> getMyInstructions()
    {
        return myInstructions;
    }

    /**
     * @return Returns myLink.
     */
    public String getMyLink()
    {
        return myLink;
    }
}
