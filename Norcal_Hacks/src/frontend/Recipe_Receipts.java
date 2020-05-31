package frontend;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Recipe_Receipts
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater( new Runnable() 
        {
            public void run()
            {
                SearchScreen searchScreen = new SearchScreen();
                searchScreen.setVisible( true );
            }
        });
    }
}
