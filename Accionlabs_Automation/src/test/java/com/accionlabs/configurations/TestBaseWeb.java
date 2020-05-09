package com.accionlabs.configurations;

import java.io.File;
import java.time.LocalDate;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.accionlabs.constants.WebConstant;
import com.accionlabs.constants.PathConstants;
import com.accionlabs.utils.BaseUtil;


public class TestBaseWeb {

	private static Logger logger = Logger.getLogger(TestBaseWeb.class);

	public static long WAIT = 20;

	protected static String folderName = PathConstants.SCREENSHOT_DIRECTORY_PATH;
	protected static WebDriver webDriver;
	protected WebDriverWait webDriverWait;
	protected Properties configProperties;
	protected static Properties testProperties;
	protected static String browserName;
	
	/**
	 * Assign all the basic configurations and properties file to use it in the test classes.
	 * @throws Exception
	 */
	public void initWebDriver() throws Exception {
		logger.info("Initiate Test Suite..");

		configProperties = BaseUtil.getConfigProperties();
		browserName = configProperties.getProperty(WebConstant.BROWSER);
		logger.info("browserName found for this execution is: " + browserName);
				
		String gloablTestProperties = PathConstants.PROPERTIES_BASEPATH + PathConstants.GLOBAL_DIR;
		testProperties = BaseUtil.readPropertyFile(gloablTestProperties, PathConstants.COMMON);
		
		webDriver = BaseUtil.initializeWebDriver(browserName);

		logger.info("Initializing WebDriver with Info: " + webDriver);
		webDriverWait = new WebDriverWait(webDriver, WAIT);
		logger.info("Default wait assigned is 20 seconds.");
		
	}

	/**
	 * Setting up the test automation suit and clear all unwanted files.
	 * @throws Exception
	 */
	@BeforeSuite
	public void testStart() throws Exception {
		String log4jConfPath = "src/main/resources/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		folderName = folderName.replace("##Date##", LocalDate.now().toString());
		BaseUtil.recursiveDelete(new File(folderName));
		initWebDriver();
	}

	/*
	 * 
	 * This is a @AfterSuite method
	 */
	@AfterSuite
	public void tearDown() {
	    webDriver.quit();
	}
	
}
