package com.accionlabs.test.mobile;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.accionlabs.configurations.TestBaseMobile;
import com.accionlabs.constants.WebConstant;
import com.accionlabs.test.pages.HomePageObjects;
import com.accionlabs.utils.BaseUtil;

public class HomePageMobileView extends TestBaseMobile {
    
    private Logger logger = Logger.getLogger(HomePageMobileView.class);
    protected HomePageObjects homePageObjects;
   
    @BeforeTest
    public void beforeTest() {
	PageFactory.initElements(webDriver, this);
	homePageObjects = new HomePageObjects(webDriver);
	webDriver.get("https://familiar.lsac.org");
    }

    @Test(description = "verify the current URL")
    public void verifyCurrentUrl() {
	String getTitle = webDriver.getCurrentUrl();
	boolean flag = false;
	System.out.println("the current URL is: "+getTitle);
	if(getTitle.equalsIgnoreCase(configProperties.getProperty(WebConstant.REDIRECTED_URL))){
	    logger.info("Current URL is verified");
	    assert flag = true;
	} else {
	    BaseUtil.captureScreenShots();
	    assert flag;
	}
    }
    
    @Test(description = "verify logo")
    public void verifyLogoDisplay() {
	BaseUtil.verifyElementDisplayed(homePageObjects.getLogo());
    }
    
    @Test(description = "verify links on the page")
    public void verifyHomepageLinks() {
	BaseUtil.clickOnElement(homePageObjects.getMenu());
	BaseUtil.verifyElementDisplayed(homePageObjects.getAboutLSAC());
	BaseUtil.verifyElementDisplayed(homePageObjects.getEvents());
	BaseUtil.verifyElementDisplayed(homePageObjects.getBlog());
	BaseUtil.verifyElementDisplayed(homePageObjects.getContactUs());
	BaseUtil.verifyElementDisplayed(homePageObjects.getLoginAs());
	BaseUtil.verifyElementDisplayed(homePageObjects.getBlog());
	BaseUtil.verifyElementDisplayed(homePageObjects.getContactUs());
	BaseUtil.verifyElementDisplayed(homePageObjects.getSearch());
    }
    

}
