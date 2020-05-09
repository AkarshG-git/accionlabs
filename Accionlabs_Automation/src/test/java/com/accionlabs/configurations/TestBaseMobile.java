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

import com.accionlabs.constants.PathConstants;
import com.accionlabs.constants.WebConstant;
import com.accionlabs.utils.BaseUtil;


public class TestBaseMobile {

	private static Logger logger = Logger.getLogger(TestBaseMobile.class);

	public static long WAIT = 20;

	protected static String folderName = PathConstants.SCREENSHOT_DIRECTORY_PATH;
	protected static WebDriver webDriver;
	protected WebDriverWait webDriverWait;
	protected Properties configProperties;
	protected static String platform;
	protected static String browserName;
	

	/**
	 * Assign all the basic configurations and properties file to use it in the test classes.
	 * @throws Exception
	 */
	public void initMobileWebDriver() throws Exception {
		logger.info("Initiate Test Suite..");

		configProperties = BaseUtil.getConfigProperties();
		platform = BaseUtil.getPlatform();
		browserName = configProperties.getProperty(WebConstant.BROWSER);
		logger.info("browser found for this execution is: " + browserName);
				
		webDriver = BaseUtil.initializeWebDriverOnMobile(browserName, platform);
		webDriverWait = new WebDriverWait(webDriver, WAIT);
		
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
		initMobileWebDriver();
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
