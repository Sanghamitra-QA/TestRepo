package DriverFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest
{
    @Test
    public void muvi_Strat()
    {
        DriverScript dr= new DriverScript();
        try
        {
            dr.startTest();

        }
        catch(Throwable t)
        {
            Assert.fail();
            System.out.println(t.getMessage());
        }
    }
}
