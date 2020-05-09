package com.accionlabs.constants;

/**
 * 
 * @author Akarsh Gupta
 *
 */

public final class PathConstants {
	public static final long defaultTimeout = 2000;
	public static final long defaultPollingFrequency = 500;
	public final static String PROPERTIES_BASEPATH = "src/main/resources/properties_files/";
	public final static String SCREENSHOT_DIRECTORY_PATH  = "src/main/resources/##Date##_screenshot/";
	public final static String GLOBAL_DIR = "global/";
	public final static String CONFIG_DIR = "config/";
	public static final String FORWARD_SLASH = "/";
	public static final String EXT_PROPERTIES = ".properties";
	public static final String UNDERSCORE = "_";
	public static final String COMMON = "common";
	public static final String CHROMEDRIVER_PATH = "src/test/resources/drivers/ChromeDriver/chromedriver.exe";
	public static final String FFDRIVER_PATH = "src/test/resources/drivers/FirefoxDriver/geckodriver.exe";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
}
