package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil
{
    public static String getValueForKey(String key)throws Throwable
    {
        String path = System.getProperty("user.dir");
        Properties configprop=new Properties();
        FileInputStream fis=new FileInputStream(path+"\\Environment.properties");
        configprop.load(fis);
        return configprop.getProperty(key);
    }
}
