package frontend;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import backend.*;

public class ResultScreen extends JFrame
{
    private Recipe recipe;
    private CookBook book;
    private int recipeIndex;
    
    public ResultScreen(CookBook book)
    {
        super("Result!");
        recipe = book.chooseBestRecipe();
        
        
        //Wrap up
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo( null );
        pack();
        setVisible(true); 
    }
    
    public void start()
    {
        JPanel panel = new JPanel();
        panel.add(displayTitle(), BorderLayout.NORTH);
        panel.add(displayIngredients(), BorderLayout.WEST );
        panel.add(displayInstructions(), BorderLayout.EAST );
        panel.setBackground(Color.ORANGE);
        add(panel);
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
        ingredientsPanel.add( makeLabel("Ingredients", JLabel.CENTER, JLabel.CENTER), constraints );
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
            if(ingredient.isMeasureable())
            {
                constraints.gridx = 3;
                ingredientsPanel.add( new JLabel(ingredient.getAmount() + " " + ingredient.getUnits()), constraints);
            }
            
        }
        return ingredientsPanel;
    }
    
    //Bug?
    private JPanel displayTitle()
    {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel( book.getProduct() + " Recipe:");
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
    
    private JPanel displayOtherResults()
    {
        ResultScreen screen1 = new ResultScreen(book);
        ResultScreen screen2 = new ResultScreen(book);
        
        Recipe nextRecipe = book.chooseBestRecipe();
        if(nextRecipe != null)
        {
            screen1.setRecipe( book.chooseBestRecipe() );
        }
        nextRecipe = book.chooseBestRecipe();
        if(nextRecipe != null)
        {
            screen2.setRecipe( book.chooseBestRecipe() );
        }
        screen1.setRecipeIndex( 1 );
        screen2.setRecipeIndex( 2 );
        return null;
    }
    
    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }
    
    public void setRecipeIndex(int index)
    {
        recipeIndex = index;
    }
    
    private ResultScreen setUpNewResult( Recipe recipe, int recipeIndex)
    {
        return null;
    }

}
