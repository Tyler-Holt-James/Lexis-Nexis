package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Page extends DriverManager
{
    public static void scroll(WebElement element)
    {
            new Actions(webDriver).scrollToElement(element).perform();
    }
}
