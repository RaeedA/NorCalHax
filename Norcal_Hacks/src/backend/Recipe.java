package backend;

import java.sql.Time;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Represents a recipe with instructions, ingredients, and a link
 *
 *  @author  Raeed Azom & Jeffery Lee
 *  @version May 31, 2020
 *  @author  Projects: Norcal_Hacks
 */
public class Recipe
{
    private ArrayList<Ingredient> myIngredients;
    private ArrayList<String> myInstructions;
    private String myLink;
    private Time cookingTime;
    
    /**
     * @param ingredients webelements of ingredients
     * @param instructions webelements with instructions
     * @param link link to site
     */
    @SuppressWarnings("deprecation")
    public Recipe(ArrayList<String> ingredients, ArrayList<String> instructions, String link, String time)
    {
        myLink = link;
        myIngredients = new ArrayList<Ingredient>();
        myInstructions = new ArrayList<String>();
        Pattern p = Pattern.compile( "^([\\d\\s\\/]+)(\\w+)" );
        for (String w : ingredients)
        {
            String text = w;
            int index = text.indexOf( "and" );
            if (index < 15 && index != -1)
            {
                //System.out.println(index + " " + text);
                text = text.substring( 0, index-1 ) + text.substring( index+3 );
            }
            Matcher m = p.matcher(text);
            if(m.find())
            {
                myIngredients.add( new Ingredient(m, text.substring( m.end() ).trim()) );
            }
            else
            {
                myIngredients.add( new Ingredient(text));
            }
        }
        Time t = new Time(0, 0, 0);
        p = Pattern.compile( "(\\d+)\\sh" );
        Matcher m = p.matcher( time );
        if (m.find())
        {
            t.setHours( Integer.parseInt(m.group(1) ));
        }
        p = Pattern.compile( "(\\d+)\\sm" );
        m = p.matcher( time );
        if (m.find())
        {
            t.setMinutes( Integer.parseInt(m.group(1) ));
        }
        cookingTime = t;
        
        for (String w : instructions)
        {
            myInstructions.add( w );
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
        result = result.substring( 0, result.length()-1 ) + "\nTime: " + cookingTime + "\nLink: " + myLink;
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
    
    /**
     * @return cooking time
     */
    public Time getCookingTime()
    {
        return cookingTime;
    }
}
