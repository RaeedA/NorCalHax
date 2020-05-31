package backend;

public class RunnerThread extends Thread
{
    private CookBook myBook;
    
    public RunnerThread(CookBook book)
    {
        myBook = book;
    }
    
    @Override
    public void run()
    {
        myBook.getRecipes( );
    }
}
