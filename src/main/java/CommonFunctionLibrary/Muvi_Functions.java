package CommonFunctionLibrary;

import Utilities.PropertyFileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Muvi_Functions
{
    public static WebDriver driver;
    //method for browser launching
    public static WebDriver startBrowser(WebDriver driver)throws Throwable
    {
        if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\rinky\\IdeaProjects\\Muvi_Automation\\ChromeDriver\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("firefox"))
        {

        }
        else if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("ie"))
        {

        }else{
            System.out.println("No Browser is Matching");
        }
        return driver;
    }
    //Launching URL
    public static  void openApplication(WebDriver driver)throws Throwable
    {
        driver.get(PropertyFileUtil.getValueForKey("url"));
        driver.manage().window().maximize();
    }
    //method for Wait for element
    public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String waittime)
    {
        WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittime));
        if(locatortype.equalsIgnoreCase("xpath"))
        {
            mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
        }
        else if(locatortype.equalsIgnoreCase("name"))
        {
            mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
        }
        else if(locatortype.equalsIgnoreCase("id"))
        {
            mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
        }
        else{
            System.out.println("unabale to wait for element");
        }
    }
    //method for typeAction
    public static void typeAction(WebDriver driver, String locatortype,String locatorvalue,String testdata)
    {
        if(locatortype.equalsIgnoreCase("xpath")){
            driver.findElement(By.xpath(locatorvalue)).clear();
            driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
        }
        else if(locatortype.equalsIgnoreCase("name")){
            driver.findElement(By.name(locatorvalue)).clear();
            driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
        }
        else if(locatortype.equalsIgnoreCase("id")){
            driver.findElement(By.id(locatorvalue)).clear();
            driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
        }else
        {
            System.out.println("unable to Execute typeaction method");
        }
    }
    //method for clickAction
    public static void clickAction(WebDriver driver, String locatortype,String locatorvalue) throws InterruptedException {
        if(locatortype.equalsIgnoreCase("xpath"))
        {
            driver.findElement(By.xpath(locatorvalue)).click();
            Thread.sleep(2000);
        }
        else if(locatortype.equalsIgnoreCase("name"))
        {
            driver.findElement(By.name(locatorvalue)).click();
        }
        else if(locatortype.equalsIgnoreCase("id"))
        {
            driver.findElement(By.id(locatorvalue)).click();
        }
        else{
            System.out.println("unable to click action method");
        }

    }
    //method for selectAction
    public static void selectAction(WebDriver driver, String locatortype,String locatorvalue) throws InterruptedException {
        if(locatortype.equalsIgnoreCase("xpath"))
        {
            driver.findElement(By.xpath(locatorvalue));
            Thread.sleep(2000);
            Select fruits = new Select(driver.findElement(By.xpath("//*[@id=\"content_category_value\"]")));
            fruits.selectByVisibleText("Movie");
        }
        else if(locatortype.equalsIgnoreCase("name"))
        {
            driver.findElement(By.name(locatorvalue)).click();
        }
        else if(locatortype.equalsIgnoreCase("id"))
        {
            driver.findElement(By.id(locatorvalue)).click();
        }
        else{
            System.out.println("unable to click action method");
        }

    }
    public static void selectStartDate(WebDriver driver, String locatortype,String locatorvalue) throws InterruptedException {
        if(locatortype.equalsIgnoreCase("xpath"))
        {
            driver.findElement(By.xpath(locatorvalue)).click();
            Thread.sleep(2000);
            WebElement Date=driver.findElement(By.xpath("//*[text()=\"25\"]"));
            Date.click();
            //WebElement selectDate=driver.findElement(By.xpath("//*[@class='react-datepicker__year-option' and text()='2020']"));
            //selectDate.click();
            //driver.findElement(By.xpath("(//*[@class='react-datepicker__week'])[1]//div[7]")).click();

        }
        else if(locatortype.equalsIgnoreCase("name"))
        {
            driver.findElement(By.name(locatorvalue)).click();
        }
        else if(locatortype.equalsIgnoreCase("id"))
        {
            driver.findElement(By.id(locatorvalue)).click();
        }
        else{
            System.out.println("unable to click action method");
        }

    }
    public static void isElementVisible(WebDriver driver, String locatortype,String locatorvalue)
    {
        if(locatortype.equalsIgnoreCase("xpath"))
        {
            if (driver.findElement(By.xpath("//*[@id='accept' and @type=\"checkbox\"]")).isDisplayed())
            {
                System.out.println("Element displayed");
            }else {
                System.out.println("Element not displayed");
            }

        }
        else if(locatortype.equalsIgnoreCase("name"))
        {
            driver.findElement(By.name(locatorvalue)).click();
        }
        else if(locatortype.equalsIgnoreCase("id"))
        {
            driver.findElement(By.id(locatorvalue)).click();
        }
        else{
            System.out.println("unable to click action method");
        }

    }
    //method for close browser
    public static void closeBrowser( WebDriver driver)
    {
        driver.close();
    }
    //method for ScrollTo
    public static void switchWindow(WebDriver driver) throws InterruptedException
    {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title=\"Zendesk Chat widget window\"]")));

        driver.switchTo().defaultContent();

    }
    public static void hoverOver(WebDriver driver, String locatortype,String locatorvalue)
    {
        if(locatortype.equalsIgnoreCase("xpath"))
        {
            Actions action = new Actions(driver);
            WebElement elem = driver.findElement(By.xpath("(//a[@href=\"javascript:void(0);\"])[3]"));
            action.moveToElement(elem);
            action.perform();
            driver.findElement(By.xpath("//a[@href=\"/cmscontent/managecontent\"]")).click();
        }
        else if(locatortype.equalsIgnoreCase("name"))
        {
            driver.findElement(By.name(locatorvalue)).click();
        }
        else if(locatortype.equalsIgnoreCase("id"))
        {
            driver.findElement(By.id(locatorvalue)).click();
        }
        else{
            System.out.println("unable to click action method");
        }

    }
    public static void uploadTopBanner (WebDriver driver) throws InterruptedException, AWTException
    {
        WebElement uploadTopBanner = driver.findElement(By.xpath("(//a[@id=\"add_change-top-banner-text\"])[1]"));
        uploadTopBanner.click();
        Thread.sleep(2000);
        WebElement banner=driver.findElement(By.xpath("//form[@id=\"topbannerForm\"]"));
        if (banner.isDisplayed())
        {
            //driver.findElement(By.xpath("//input[@value=\"Upload File\" and @class=\"btn btn-default-with-bg btn-file btn-sm\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"celeb_pic1\"]")).sendKeys("C:\\Users\\rinky\\IdeaProjects\\Muvi_Automation\\InputFiles\\TopBanner.jpg");;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"topbanner_submit_btn\"]")).click();
            Thread.sleep(4000);
        }

    }

    public static void uploadPoster (WebDriver driver) throws InterruptedException, AWTException
    {
        WebElement uploadTopBanner = driver.findElement(By.xpath("//*[@id=\"browse-upload-poster\"]"));
        uploadTopBanner.click();
        Thread.sleep(2000);
        WebElement banner=driver.findElement(By.xpath("(//input[@class=\"btn btn-default-with-bg btn-sm\"])[1]"));
        if (banner.isDisplayed())
        {
            //driver.findElement(By.xpath("//input[@value=\"Upload File\" and @class=\"btn btn-default-with-bg btn-file btn-sm\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"celeb_pic\"]")).sendKeys("C:\\Users\\rinky\\IdeaProjects\\Muvi_Automation\\InputFiles\\Poster.png");
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[@class=\"btn btn-primary\" and text()=\"Next\"])[1]")).click();
            Thread.sleep(4000);
        }

    }
    public static void profilePicture (WebDriver driver) throws InterruptedException, AWTException
    {
        WebElement profilePicture = driver.findElement(By.xpath("//*[@data-toggle=\"modal\" and text()=\"Upload Image\"]"));
        profilePicture.click();
        Thread.sleep(2000);
        WebElement banner=driver.findElement(By.xpath("(//*[@class=\"btn btn-default-with-bg btn-sm\" and @type=\"button\"])[3]"));
        if (banner.isDisplayed())
        {
            //driver.findElement(By.xpath("//input[@value=\"Upload File\" and @class=\"btn btn-default-with-bg btn-file btn-sm\"]")).click();
            driver.findElement(By.xpath("//input[@name=\"Filedata_cast\"]")).sendKeys("C:\\Users\\rinky\\IdeaProjects\\Muvi_Automation\\InputFiles\\ProfilePicture.jpg");;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@id=\"next\"]")).click();
            Thread.sleep(4000);
        }

    }
    public static void uploadVideo (WebDriver driver) throws InterruptedException, AWTException
    {
        WebElement profilePicture = driver.findElement(By.xpath("//a[@id=\"trailer_btn\"]"));
        profilePicture.click();
        Thread.sleep(2000);
        WebElement banner=driver.findElement(By.xpath("(//input[@value=\"Upload File\"])[4]"));
        if (banner.isDisplayed())
        {
            //driver.findElement(By.xpath("//input[@value=\"Upload File\" and @class=\"btn btn-default-with-bg btn-file btn-sm\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"videofile\"]")).sendKeys("C:\\Users\\rinky\\IdeaProjects\\Muvi_Automation\\InputFiles\\videoplayback.mp4");;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@class=\"confirm\"]")).click();
            Thread.sleep(5000);
        }

    }
    public static void  verifyUpload(WebDriver driver) throws InterruptedException, AWTException
    {
        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();

// Now iterate using Iterator
        Iterator<String> I1= s.iterator();

        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);
                Thread.sleep(3000);
                String Title=driver.switchTo().window(child_window).findElement(By.xpath("//*[@class=\"_item-detail\"]")).getText();

               if (Title.equalsIgnoreCase("Test")){
                   System.out.println("Content added successfully");
               }else {
                   System.out.println("Test Fail");
               }
            }
        }
     //switch to the parent window
        driver.switchTo().window(parent);
        }
    //method for Date generate
    public static String generateDate()
    {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
        return sdf.format(date);

    }
}
