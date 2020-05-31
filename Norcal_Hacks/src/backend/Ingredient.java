package backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ingredient
{
    private String type;
    private String amount;
    private String units;
    
    /**
     * @param g1 group one of matcher
     * @param g2 group two of matcher
     */
    public Ingredient(Matcher m, String ingredient)
    {
        amount = m.group(1).trim();
        units = m.group(2).trim();
        type = findType(ingredient);
    }
    
    /**
     * @param ingredient special ingredient to add
     */
    public Ingredient(String ingredient)
    {
        type = ingredient;
        amount = null;
        units = null;
    }

    public String findType(String ingredient)
    {
        Pattern p = null;
        Matcher m = null;
        String result = null;
        if (ingredient.contains( "milk" ))
        {
            result = lookBehind(ingredient, "milk", m, p);
        }
        else if (ingredient.contains( "powder" ))
        {
            result = lookBehind(ingredient, "powder", m, p);
        }
        else if (ingredient.contains( "egg" ))
        {
            result = lookBehind(ingredient, "egg", m, p);
            if (Integer.parseInt( amount ) > 1)
            {
                result += "s";
            }
        }
        else if (ingredient.contains( "salt" ))
        {
            result = "salt";
        }
        else if (ingredient.contains( "sugar" ))
        {
            result = lookBehind(ingredient, "sugar", m, p);
        }
        else if (ingredient.contains( "extract" ))
        {
            result = lookBehind(ingredient, "extract", m, p);
        }
        else if (ingredient.contains( "butter" ))
        {
            if (ingredient.contains( "butter," ))
            {
                result = lookBehind(ingredient, "butter", m, p);
                p = Pattern.compile( "(?<=butter,)\\s[\\w-]+" );
                m = p.matcher( ingredient );
                if(m.find())
                {
                    result += "," + m.group();
                }
            }
            else
            {
                p = Pattern.compile("[\\w-]+\\s[\\w-]+\\s+(?=butter)");
                m = p.matcher( ingredient );
                if(m.find())
                {
                    result = m.group() + "butter";
                }
                else
                {
                    result = lookBehind(ingredient, "butter", m, p);
                }
            }
        }
        else
        {
            result = ingredient;
        }
        result.trim();
        return result;
    }
    
    private String lookBehind(String string, String word, Matcher m, Pattern p)
    {
        p = Pattern.compile( "[\\w-]+\\s+(?=" + word + ")" );
        m = p.matcher( string );
        if (m.find())
        {
            return m.group() + word;
        }
        return word;
    }
    
    public boolean isMeasureable()
    {
        return !(amount == null && units == null);
    }
    
    /**
     * toString method
     * @return returns string representation of this ingredient
     */
    @Override
    public String toString()
    {
        if (isMeasureable())
        {
            return amount + " " + units + " " + type;
        }
        else
        {
            return type;
        }
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return Returns the amount.
     */
    public String getAmount()
    {
        return amount;
    }

    /**
     * @return Returns the units.
     */
    public String getUnits()
    {
        return units;
    }
}
