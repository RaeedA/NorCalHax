package backend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        System.out.println(driver);
        driver.get("http://stackabuse.com");
        WebElement newsletterEmail = driver.findElement(By.id("email"));
        driver.quit();
    }
}
