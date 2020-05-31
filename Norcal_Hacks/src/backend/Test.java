package backend;

public class Test
{
    public static void main(String[] args)
    {
        CookBook book = new CookBook();
        book.getRecipes( "vanilla cake" );
        
        while(!book.isDone())
        {
        }
        System.out.println("Book:\n" + book);
        
        //Close everything
        //myDriver.quit();
        System.out.println("Done!");
        System.exit( 0 );
    }
}
