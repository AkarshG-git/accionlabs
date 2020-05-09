package com.accionlabs.test.web;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.accionlabs.configurations.TestBaseWeb;
import com.accionlabs.constants.WebConstant;
import com.accionlabs.test.pages.HomePageObjects;
import com.accionlabs.utils.BaseUtil;


public class HomePageTest extends TestBaseWeb {
    HomePageObjects homePageObjects = null;
    
    @BeforeTest
    public void beforeTest() {
	PageFactory.initElements(webDriver, this);
	homePageObjects = new HomePageObjects(webDriver);
	webDriver.get("https://familiar.lsac.org");
    }

    @Test
    public void verifyCurrentUrl() {
	String getTitle = webDriver.getCurrentUrl();
	boolean flag = false;
	System.out.println("the current URL is: "+getTitle);
	if(getTitle.equalsIgnoreCase(configProperties.getProperty(WebConstant.REDIRECTED_URL))){
	    assert flag = true;
	} else {
	    BaseUtil.captureScreenShots();
	    assert flag;
	}
    }
    
    @Test
    public void verifyLogoDisplay() {
	BaseUtil.verifyElementDisplayed(homePageObjects.getLogo());
    }
    
    @Test
    public void verifyHomepageLinks() {
	BaseUtil.verifyElementDisplayed(homePageObjects.getLink1());
	BaseUtil.verifyElementDisplayed(homePageObjects.getLink2());
	BaseUtil.verifyElementDisplayed(homePageObjects.getApplyLawSchool());
	BaseUtil.verifyElementDisplayed(homePageObjects.getChooseLawSchool());
	BaseUtil.verifyElementDisplayed(homePageObjects.getDataAndResearch());
    }
    

    @AfterClass
    public void afterSuite() {
	webDriver.close();
    }

}
