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
        super(foodToSearch);
        foodName = foodToSearch;
        book = new CookBook();
        book.getRecipes( foodName );
        while(!book.isDone()){}
        recipe = book.chooseBestRecipe();
        setComponents();
    }

    @Override
    protected void setComponents()
    {
        add(displayTitle(), BorderLayout.NORTH);
        add(displayIngredients(), BorderLayout.WEST );
        add(displayInstructions(), BorderLayout.EAST );
        pack();
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
        instructionsArea.setColumns( 35 );
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setLineWrap(true);
        instructionsArea.setEditable( false );
        instructionsArea.setFocusable( false );
        
        for(int i = 0; i < instructions.size(); i++)
        {
            instructionsArea.append( (i+1) +  ". " + instructions.get( i ) + "\n\n" ); ;
        }
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
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx += 5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        ingredientsPanel.add( makeLabel("Ingredients", JLabel.CENTER, JLabel.EAST), constraints );
        constraints.gridy = 1;
        constraints.weighty += 5;
        Ingredient ingredient;
        for(int i = 0; i < ingredients.size(); i++)
        {
            ingredient = ingredients.get( i );
            constraints.gridx = 0;
            ingredientsPanel.add(new JLabel());
            constraints.gridx = 1;
            constraints.gridy++;
            ingredientsPanel.add( new JLabel(ingredient.getType()),constraints );
            if(ingredient.getAmount() != null || ingredient.getUnits() != null)
            {
                constraints.gridx = 3;
                ingredientsPanel.add( new JLabel(ingredient.getAmount() + " " + ingredient.getUnits()), constraints);
            }
            
        }
        return ingredientsPanel;
    }
    
    //Bug?
    public JPanel displayTitle()
    {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel( foodName + " Recipe:");
        Font labelFont = titleLabel.getFont();
        String labelText = titleLabel.getText();
        int stringWidth = titleLabel.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = titleLabel.getWidth();
        
        double widthRatio = (double) componentWidth / (double)stringWidth;
        
        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = titleLabel.getHeight();
        
        int fontSizeToUse = Math.min( newFontSize, componentHeight );
        
        titleLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        
        titlePanel.add( titleLabel );
        return titlePanel;
    }
    
    

}
