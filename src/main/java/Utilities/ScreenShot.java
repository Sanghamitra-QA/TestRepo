package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShot
{
    public static void Takescreen(WebDriver driver, String sname) throws Throwable{
        String path = System.getProperty("user.dir");
        File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File(path+"\\Screenshots\\"+sname+".png"));
    }
}
