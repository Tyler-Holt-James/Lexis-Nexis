package po;

import dto.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static utils.ColourConverter.getHex;
import static utils.Page.scroll;
import static utils.StringUtils.getCurrentDirectory;


public class LexisNexisHomePage extends DriverManager
{
    //ints
    private final int HTML_AMOUNT = 235;
    //strings
    private final String URL = "https://www.selenium.dev/selenium/web/web-form.html";
    private final String CONFIRMATION_TEXT = ("Form submitted");
    private final String HEADER_TEXT = ("Web form");
    //by locators
    private final By HEADING = By.tagName("h1");
    private final By VERIFY = By.className("display-6");
    private final By TEXT_INPUT_ID = By.id("my-text-id");
    private final By PASSWORD_ID = By.cssSelector("[type='password']");
    private final By TEXT_AREA_ID = By.name("my-textarea");
    private final By DISABLED_ID = By.cssSelector("[placeholder='Disabled input']");
    private final By READ_ONLY_ID = By.name("my-readonly");
    private final By DD_SELECT_ID = By.name("my-select");
    private final By DD_DATALIST_ID = By.name("my-datalist");
    private final By FILE_INPUT_ID = By.cssSelector("[type='file']");
    private final By CHECKED_CHECKBOX_ID = By.id("my-check-1");
    private final By DEFAULT_CHECKBOX_ID = By.id("my-check-2");
    private final By CHECKED_RADIO_ID = By.id("my-radio-1");
    private final By DEFAULT_RADIO_ID = By.id("my-radio-2");
    private final By COLOUR_PICKER_ID = By.cssSelector("[type='color']");
    private final By DATE_PICKER_ID = By.name("my-date");
    private final By RANGE_SLIDER_ID = By.cssSelector("[type='range']");
    private final By HIDDEN_ID = By.name("my-hidden");
    private final By SUBMIT_ID = By.cssSelector("[type='submit']");

    //the go method will take the browser to the form
    public void go()
    {
        webDriver.get(URL);
    }

    //the verify method will check to see if the page has loaded before starting the form
    public boolean verify()
    {
        String header = webDriver.findElement(HEADING).getText();
        return header.equals(HEADER_TEXT);
    }

    public void enterDetails(Input input)
    {
        //for most of these inputs I will be anonymously getting the web element and sending the input from the feature file straight to the respected element
        //textInput
        webDriver.findElement(TEXT_INPUT_ID).sendKeys(input.getTextInput());
        //password
        webDriver.findElement(PASSWORD_ID).sendKeys(input.getPassword());
        //textArea
        webDriver.findElement(TEXT_AREA_ID).sendKeys(input.getTextArea());
        //for the dropdowns I will be getting the identifier for both respectively, then creating a list of all the options available and clicking on the one that is needed
        //ddSelect
        WebElement ddSelect = webDriver.findElement(DD_SELECT_ID);
        ddSelect.click();
        selectElement(ddSelect.findElements(By.tagName("option")), input.getDropdownSelect());
        ddSelect.click();
        //ddDatalist
        webDriver.findElement(DD_DATALIST_ID).sendKeys(input.getDropdownDatalist());
        //for the file input, I will be sending the picture in this project to the form using your own computer directory
        //fileInput
        String fileName = getCurrentDirectory() + "\\img\\" + input.getFileInput();
        webDriver.findElement(FILE_INPUT_ID).sendKeys(fileName);
        //for the colour picker, I will use a hashmap which will turn the colour that was inputted from the feature file into hexadecimal, which is what will then be sent to the form
        //colourPicker
        webDriver.findElement(COLOUR_PICKER_ID).sendKeys(getHex(input.getColourPicker()));
        //for the date picker, I will use simpleDateFormat to make sure no matter what format is inputted from the feature file, it will always come out in the correct one
        //had to use a try-catch as .parse was a checked method
        //datePicker
        try
        {
            SimpleDateFormat dayMonthYearInput = new SimpleDateFormat("dd/MM/yyyy");
            Date dayMonthYearString = dayMonthYearInput.parse(input.getDatePicker());
            SimpleDateFormat dayMonthYear = new SimpleDateFormat("MM/dd/yyyy");
            webDriver.findElement(DATE_PICKER_ID).sendKeys(dayMonthYear.format(dayMonthYearString));
        }
        catch (ParseException pe)
        {
            throw new RuntimeException(pe);
        }
        webDriver.findElement(DATE_PICKER_ID).click();
    }

    public void clickableDetails()
    {
        //both the disabled and readonly input boxes cannot be written to, so I will check that they are there using asserts accordingly
        //disabledInput - check to see if its enabled
        assertFalse(webDriver.findElement(DISABLED_ID).isEnabled());
        //readonlyInput - check to see if its visible
        assertTrue(webDriver.findElement(READ_ONLY_ID).isDisplayed());
        //hiddenInput - check to see if its visible
        assertFalse(webDriver.findElement(HIDDEN_ID).isDisplayed());
        //for the checkboxes, I will firstly unselect the checked checkbox, select the default one, then select the checked one again
        //checkboxes
        WebElement checkedCheckbox = webDriver.findElement(CHECKED_CHECKBOX_ID);
        WebElement defaultCheckbox = webDriver.findElement(DEFAULT_CHECKBOX_ID);
        //unselect the checked checkbox
        checkedCheckbox.click();
        //select the default one
        defaultCheckbox.click();
        //select the checked one again
        checkedCheckbox.click();
        //for the radios, I will firstly select the default radio, then select the checked one again
        //radios
        WebElement checkedRadio = webDriver.findElement(CHECKED_RADIO_ID);
        WebElement defaultRadio = webDriver.findElement(DEFAULT_RADIO_ID);
        //select the default radio
        defaultRadio.click();
        //select the checked one again
        checkedRadio.click();
        //for the slider, I will find the element and click on it, then using Keys.LEFT or Keys.RIGHT in a loop, move it in any direction that I please
        //for this example, I will move the slider all the way to the right
        //rangeSlider
        WebElement slider = webDriver.findElement(RANGE_SLIDER_ID);
        for (int i = 0; i < 5; i++)
        {
            slider.sendKeys(Keys.RIGHT);
        }
    }

    public void collectHtmlLinks(String link)
    {
        webDriver.findElement(By.linkText(link)).click();
        int htmlList = webDriver.findElements(By.tagName("a")).size();
        assertEquals(HTML_AMOUNT, htmlList);
        webDriver.navigate().back();
    }

    //click submit button
    public void submitForm()
    {
        //submit
        webDriver.findElement(SUBMIT_ID).click();
    }

    //this will do a check to see if the page can see what it needs to confirm that it has worked
    public boolean verifySubmission()
    {
        return webDriver.findElement(VERIFY).getText().equals(CONFIRMATION_TEXT);
    }

    //this is my own method that will get all the options out of a list and go through them one at a time until the objective is met
    private void selectElement(List<WebElement> options, String objective)
    {
        for (WebElement option:options)
        {
            if (option.getText().equals(objective))
            {
                option.click();
                break;
            }
        }
    }
}