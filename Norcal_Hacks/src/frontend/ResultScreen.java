package frontend;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import backend.*;

public class ResultScreen extends Screen
{
    Recipe recipe;
    CookBook book;
    public ResultScreen(String foodToSearch)
    {
        super("Recipe for " + foodToSearch);
        foodName = foodToSearch;
        book = new CookBook();
        book.getRecipes( foodName );
        recipe = book.chooseBestRecipe();
        setComponents();
    }

    @Override
    protected void setComponents()
    {
        System.out.println(recipe);
        add(displayIngredients(), BorderLayout.WEST );
        add(displayInstructions(), BorderLayout.EAST );
    }

    @Override
    protected void resetSearch()
    {
        // TODO Auto-generated method stub
        
    }
    private JLabel makeLabel( String text, int horizPosition, int vertPosition)
    {
        JLabel label = new JLabel();
        if(text != null)
        {
            label.setText( text );
        }
        label.setHorizontalAlignment( horizPosition );
        label.setVerticalAlignment( vertPosition );
        return label;
    }
    
    private JPanel displayInstructions()
    {
        ArrayList<String> instructions = recipe.getMyInstructions();
        String instructionsText = "";
        JPanel instructionsPanel = new JPanel();
        BoxLayout instructionsLayout = new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS);
        instructionsPanel.setLayout( instructionsLayout );
        //Text Area
        JTextArea instructionsArea = new JTextArea();
        for(int i = 0; i < instructions.size(); i++)
        {
            instructionsText += instructions.get( i ) + "\n";
        }
        instructionsArea.setText( instructionsText );
        //Label
        instructionsPanel.add( makeLabel("Instructions: ", JLabel.LEFT, JLabel.CENTER) );
        instructionsPanel.add( instructionsArea );
        return instructionsPanel;
    }
    
    private JPanel displayIngredients()
    {
        JPanel ingredientsPanel = new JPanel();
        ArrayList<Ingredient> ingredients = recipe.getMyIngredients();
        GridBagLayout ingredientsLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        ingredientsPanel.setLayout( ingredientsLayout );
        int horiz = GridBagConstraints.HORIZONTAL;
        
        constraints.fill = horiz;
        constraints.gridx = 0;
        constraints.gridy = 1;
        ingredientsPanel.add( new JLabel("Ingredients") );
        return ingredientsPanel;
    }
    
    

}
