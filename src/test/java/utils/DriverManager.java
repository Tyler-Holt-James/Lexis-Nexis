package utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverManager
{
    public static WebDriver webDriver;
    private WebDriverWait elementWait = null;
    String browser = PropertiesReader.getProperty("browser");

    public DriverManager()
    {
        if (webDriver == null) {

            switch (browser) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    //options.addArguments("--headless");
                    webDriver = new ChromeDriver(options);
                    break;

                case "firefox":
                    FirefoxOptions ffoptions = new FirefoxOptions();
                    ffoptions.addArguments("start-maximized");
                    //ffoptions.addArguments("--headless");
                    webDriver = new FirefoxDriver(ffoptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("start-maximized");
                    //edgeOptions.addArguments("--headless");
                    webDriver = new EdgeDriver(edgeOptions);
                    break;

                case "default":
                    throw new IllegalArgumentException("This Does Not Exist.");

            }
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }
    public WebDriver getWebDriver()
    {
        return webDriver;
    }
    public void get(String url)
    {
        webDriver.get(url);
    }
    public static void quit()
    {
        webDriver.quit();
    }
}