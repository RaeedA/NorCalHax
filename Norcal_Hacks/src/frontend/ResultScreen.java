package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import backend.*;

public class ResultScreen extends JFrame
{
    private Recipe recipe;
    private CookBook book;
    private int recipeIndex;
    private String product;
    
    public ResultScreen(CookBook book)
    {
        super("Result!");
        product = book.getProduct();
        recipe = book.chooseBestRecipe();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        
        setSize(screenWidth/2, screenHeight/2);
        
        //Wrap up
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo( null );
        pack();
        setVisible(true); 
    }
    
    public void start()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        JPanel ingredients = displayIngredients();
        JPanel instructions = displayInstructions();
        
        panel.add(ingredients, c);
        
        c.gridy = 1;
        panel.add(instructions, c);
        
        add(panel);
        
        pack();
    }
    
    private JPanel displayInstructions()
    {
        JPanel panel = new JPanel();
        ArrayList<String> instructions = recipe.getMyInstructions();

        //Text Area
        JTextArea area = new JTextArea();
        area.setColumns( 75 );
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable( false );
        area.setFocusable( false );
        
        for(int i = 0; i < instructions.size(); i++)
        {
            area.append( instructions.get( i ) + "\n\n" ); ;
        }

        panel.add( area, BorderLayout.CENTER );
        return panel;
    }
    
    private JPanel displayIngredients()
    {
        JPanel panel = new JPanel();
        ArrayList<Ingredient> ingredients = recipe.getMyIngredients();
        
        
        JTextArea area = new JTextArea();
        area.setColumns( 75 );
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable( false );
        area.setFocusable( false );
        for (int i = 0; i < ingredients.size(); i++)
        {
            area.append( ingredients.get( i ) + "\n" );
        }
        
        panel.add( area , BorderLayout.CENTER);
        
        return panel;
    }
    
    //Bug?
    private JPanel displayTitle()
    {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(product + " Recipe:");
//        Font labelFont = titleLabel.getFont();
//        String labelText = titleLabel.getText();
//        int stringWidth = titleLabel.getFontMetrics(labelFont).stringWidth(labelText);
//        int componentWidth = titleLabel.getWidth();
//        
//        double widthRatio = (double) componentWidth / (double)stringWidth;
//        
//        int newFontSize = (int)(labelFont.getSize() * widthRatio);
//        int componentHeight = titleLabel.getHeight();
//        
//        int fontSizeToUse = Math.min( newFontSize, componentHeight );
//        
//        titleLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        
        titlePanel.add( titleLabel );
        return titlePanel;
    }
    
    public void next()
    {
        ResultScreen screen = new ResultScreen(book);
        screen.start();
        setVisible(false);
    }
    
    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }
    
    public void setRecipeIndex(int index)
    {
        recipeIndex = index;
    }

}
