package com.accionlabs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.accionlabs.constants.PathConstants;
import com.accionlabs.constants.WebConstant;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class BaseUtil {

    protected static WebDriver webDriver;
    protected static String browserName;

    private static Logger logger = Logger.getLogger(BaseUtil.class);
    private static String folder_name = PathConstants.SCREENSHOT_DIRECTORY_PATH;
    private static DateFormat dateFormat;
    private static String resourceFilePath = PathConstants.PROPERTIES_BASEPATH + PathConstants.CONFIG_DIR;
    private static Properties configProperties = BaseUtil.readPropertyFile(resourceFilePath, PathConstants.COMMON);

    /*
     * 
     * this method reads the properties of property file
     */
    public static String readProperty(String property) {
	Properties prop;
	String value = null;
	try {
	    prop = new Properties();
	    prop.load(new FileInputStream(
		    new File(resourceFilePath + PathConstants.COMMON + PathConstants.EXT_PROPERTIES)));
	    value = prop.getProperty(property);
	    if (value == null || value.isEmpty()) {
		throw new Exception("Value not set or empty");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return value;
    }

    /*
     * 
     * this method reads the property file
     */
    public static Properties readPropertyFile(String filePath, String fileName) {
	if (filePath == null || filePath.trim().isEmpty()) {
	    filePath = filePath + PathConstants.PROPERTIES_BASEPATH + PathConstants.GLOBAL_DIR;
	}
	if (fileName == null || fileName.trim().isEmpty()) {
	    fileName = fileName + PathConstants.COMMON + PathConstants.EXT_PROPERTIES;
	}
	Properties prop = new Properties();
	fileName = fileName + PathConstants.EXT_PROPERTIES;
	//	logger.info(filePath + fileName);
	try {
	    prop.load(new FileInputStream(new File(filePath + fileName)));

	    if (prop == null || prop.isEmpty()) {
		throw new Exception("Property file not set or empty");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return prop;
    }
    /*
     * 
     * Initialize Web driver value for Desktop view of browser .
     */
    public static WebDriver initializeWebDriver(String browserName){
	if(browserName.equalsIgnoreCase("chrome")) {
	    System.setProperty("webdriver.chrome.driver", PathConstants.CHROMEDRIVER_PATH);
	    webDriver = new ChromeDriver();
	} else if(browserName.equalsIgnoreCase("firefox")) {
	    System.setProperty("webdriver.gecko.driver", PathConstants.FFDRIVER_PATH);
	    webDriver = new FirefoxDriver();
	}
	return webDriver;
    }

    /*
     * 
     * Initialize Web driver value for Mobile view of browser on android device.
     */
    public static WebDriver initializeWebDriverOnMobile(String browserName, String platform) throws Exception{

	DesiredCapabilities capabilities = new DesiredCapabilities();
	String completeURL = BaseUtil.readProperty(WebConstant.APPIUM_DRIVER_URL);
	URL appiumURL = new URL(completeURL);
	configProperties.putAll(BaseUtil.readPropertyFile(resourceFilePath, platform));
	logger.info("Config intialized for " + platform + " platform.");
	switch (platform.toLowerCase()) {
	case "ios":
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,  MobilePlatform.IOS);
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
		    configProperties.getProperty(WebConstant.DEVICE_IOS_NAME));
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, 
		    configProperties.getProperty(WebConstant.PLATFORM_IOS_VERSION));
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
	    webDriver = new IOSDriver<>(new URL(completeURL), capabilities);
	    break;
	case "android":
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID);
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
		    configProperties.getProperty(WebConstant.DEVICE_ANDROID_NAME));
	    capabilities.setCapability(MobileCapabilityType.VERSION,
		    configProperties.getProperty(WebConstant.PLATFORM_ANDROID_VERSION));
	    webDriver = new AndroidDriver<>(appiumURL, capabilities);
	    break;
	default: 
	    throw new Exception("Platform not supported");
	}
	return webDriver;
    }

    /*
     * 
     * this method capture screenshots
     */
    public static void captureScreenShots() {
	File f = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
	folder_name = folder_name.replace("##Date##", LocalDate.now().toString());
	dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
	new File(folder_name).mkdir();
	String file_name = dateFormat.format(new Date()) + ".png";
	try {
	    FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
	} catch (IOException e) {
	    logger.error("Not able to save the screenshot file. Exception is=" + e.getMessage());
	}
    }

    public static Properties getConfigProperties() {
	return configProperties;
    }

    public static String getPlatform() {
	String platform = System.getProperty("platform");
	if (platform == null || platform.trim().isEmpty()) {
	    platform = configProperties.getProperty(WebConstant.RUN_PLATFORM);
	}
	return platform;
    }

    /*
     * 
     * this method deletes the unnecessary file and folder from the application.
     */
    public static void recursiveDelete(File file) {
	// to end the recursive loop
	if (!file.exists())
	    return;
	// if directory, go inside and call recursively
	if (file.isDirectory()) {
	    for (File f : file.listFiles()) {
		// call recursively
		recursiveDelete(f);
	    }
	}
	// call delete to delete files and empty directory
	file.delete();
	logger.info("Deleted file/folder: " + file.getAbsolutePath());
    }

    /*
     * 
     * this method use to verify displayed elements
     */
    public static boolean verifyElementDisplayed(WebElement element) {
	logger.debug("Executing Test Step::" + new Object() {
	}.getClass().getEnclosingMethod().getName());

	try {
	    if (element.isDisplayed()) {
		return true;
	    } else {
		logger.error(element.getText() +"not displayed ");
		assert false;
	    }
	} catch (Exception e) {
	    logger.error("Element not available ");
	    assert false;
	}
	return false;
    }
    
    /*
     * 
     * this method use to click on elements
     */
    public static void clickOnElement(WebElement element) {
	logger.debug("Executing Test Step::" + new Object() {
	}.getClass().getEnclosingMethod().getName());
	
	element.click();
	
    }

}
