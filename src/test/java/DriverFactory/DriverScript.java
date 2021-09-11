package DriverFactory;

import CommonFunctionLibrary.Muvi_Functions;
import Utilities.ExcelFileUtil;
import Utilities.ScreenShot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;

public class DriverScript
{
    ExtentReports report;
    ExtentTest test;
    WebDriver driver;
    public void startTest() throws Throwable
    {
        //creating reference object for excel utils methods
        ExcelFileUtil excel= new ExcelFileUtil();
        // iterate all row in masterTestCases sheet

        for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
        {
            String ModuleStatus="";
            if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
            {
                //store module name to TCModule
                String TCModule =excel.getData("MasterTestCases", i, 1);
                report=new ExtentReports("./Reports/"+TCModule+Muvi_Functions.generateDate()+".html");
                //iterate all rows in TCModule
                for(int j=1;j<=excel.rowCount(TCModule);j++)
                {
                    test=report.startTest(TCModule);
                    //read al the columns from TCMocule
                    String Description=excel.getData(TCModule, j, 0);
                    String Object_Type=excel.getData(TCModule, j, 1);
                    String Locator_Type=excel.getData(TCModule, j, 2);
                    String Locator_Value=excel.getData(TCModule, j, 3);
                    String Test_Data=excel.getData(TCModule, j, 4);
                    //System.out.println(Description+" "+Object_Type);
                    //calling the method from function library
                    try{
                        if(Object_Type.equalsIgnoreCase("startBrowser"))
                        {
                            System.out.println("Executing startBroswer");
                            driver= Muvi_Functions.startBrowser(driver);
                            System.out.println("Executing startBroswer");
                        }else if (Object_Type.equalsIgnoreCase("openApplication"))
                        {
                            Muvi_Functions.openApplication(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing openApplication");
                        }else if (Object_Type.equalsIgnoreCase("waitForElement"))
                        {
                            Muvi_Functions.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing waitForElement");
                        }else if (Object_Type.equalsIgnoreCase("typeAction"))
                        {
                            Muvi_Functions.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing typeAction");
                        }else if (Object_Type.equalsIgnoreCase("clickAction"))
                        {
                            Muvi_Functions.clickAction(driver, Locator_Type, Locator_Value);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing clickAction");
                        }else if (Object_Type.equalsIgnoreCase("selectAction"))
                        {
                            Muvi_Functions.selectAction(driver, Locator_Type, Locator_Value);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing selectAction");
                        } else if (Object_Type.equalsIgnoreCase("selectStartDate"))
                        {
                            Muvi_Functions.selectStartDate(driver, Locator_Type, Locator_Value);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing selectStartDate");
                        }
                        else if (Object_Type.equalsIgnoreCase("switchWindow"))
                        {
                            Muvi_Functions.switchWindow(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing switchWindow");
                        }else if (Object_Type.equalsIgnoreCase("isElementVisible"))
                        {
                            Muvi_Functions.isElementVisible(driver, Locator_Type, Locator_Value);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing isElementVisible");
                        }else if (Object_Type.equalsIgnoreCase("hoverOver"))
                        {
                            Muvi_Functions.hoverOver(driver, Locator_Type, Locator_Value);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing hoverOver");
                        }else if (Object_Type.equalsIgnoreCase("uploadTopBanner"))
                        {
                            Muvi_Functions.uploadTopBanner(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing uploadTopBanner");
                        }
                        else if (Object_Type.equalsIgnoreCase("uploadPoster"))
                        {
                            Muvi_Functions.uploadPoster(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing uploadPoster");
                        }
                        else if (Object_Type.equalsIgnoreCase("profilePicture"))
                        {
                            Muvi_Functions.profilePicture(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing profilePicture");
                        }
                        else if (Object_Type.equalsIgnoreCase("uploadVideo"))
                        {
                            Muvi_Functions.uploadVideo(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing uploadVideo");
                        }
                        else if (Object_Type.equalsIgnoreCase("verifyUpload"))
                        {
                            Muvi_Functions.verifyUpload(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing verifyUpload");
                        }

                        else if (Object_Type.equalsIgnoreCase("closeBrowser"))
                        {
                            Muvi_Functions.closeBrowser(driver);
                            test.log(LogStatus.INFO, Description);
                            System.out.println("Executing closeBrowser");
                        }


                        //write as pass into status column
                        excel.setCellData(TCModule, j, 5, "PASS");
                        test.log(LogStatus.PASS, Description);
                        ScreenShot.Takescreen(driver,Description);
                        ModuleStatus="TRUE";

                    }
                    catch(Exception e)
                    {
                        excel.setCellData(TCModule, j, 5, "FAIL");
                        ModuleStatus="FALSE";
                        if(ModuleStatus.equalsIgnoreCase("FALSE"))
                        {
                            excel.setCellData("MasterTestCases", i, 3, "FAIL");
                            Thread.sleep(2000);
                            test.log(LogStatus.FAIL, "Test Fail");
                            ScreenShot.Takescreen(driver,Description);
                        }
                        System.out.println(e.getMessage());
                        break;
                    }
                    if(ModuleStatus.equalsIgnoreCase("TRUE"))
                    {
                        excel.setCellData("MasterTestCases", i, 3, "PASS");
                    }
                    report.flush();//push reports to html
                    report.endTest(test);
                }

            }
            else
            {
                //write as not executed in master testcases in status coulmn for flag N
                excel.setCellData("MasterTestCases", i,3, "Not Executed");

            }
        }
    }
}
