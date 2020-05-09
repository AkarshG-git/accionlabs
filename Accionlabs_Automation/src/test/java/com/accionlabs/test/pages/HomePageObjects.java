package com.accionlabs.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {

    public HomePageObjects(WebDriver driver) {
	PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.LINK_TEXT, using = "Discover Law")
    private WebElement discoverLaw;
    
    @FindBy(how = How.LINK_TEXT, using = "The LSAT")
    private WebElement lsat;
    
    @FindBy(how = How.LINK_TEXT, using = "Choosing a Law School")
    private WebElement chooseLawSchool;
    
    @FindBy(how = How.LINK_TEXT, using = "Applying to Law School")
    private WebElement applyLawSchool;
    
    @FindBy(how = How.LINK_TEXT, using = "Data & Research")
    private WebElement dataAndResearch;
    
    @FindBy(how = How.XPATH, using = "//img[@class='branding-block__logo']")
    private WebElement logo;
    
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'js-menu-open')]")
    private WebElement menu;
    
    @FindBy(how = How.LINK_TEXT, using = "About LSAC")
    private WebElement aboutLSAC;
    
    @FindBy(how = How.LINK_TEXT, using = "Events")
    private WebElement events;
    
    @FindBy(how = How.LINK_TEXT, using = "Blog")
    private WebElement blog;
    
    @FindBy(how = How.LINK_TEXT, using = "Contact Us")
    private WebElement contactUs;
    
    @FindBy(how = How.LINK_TEXT, using = "Log in as...")
    private WebElement loginAs;

    @FindBy(how = How.LINK_TEXT, using = "Search")
    private WebElement search;
    
    public WebElement getDiscoverLaw() {
        return discoverLaw;
    }

    public void setDiscoverLaw(WebElement discoverLaw) {
        this.discoverLaw = discoverLaw;
    }

    public WebElement getMenu() {
        return menu;
    }

    public void setMenu(WebElement menu) {
        this.menu = menu;
    }

    public WebElement getAboutLSAC() {
        return aboutLSAC;
    }

    public void setAboutLSAC(WebElement aboutLSAC) {
        this.aboutLSAC = aboutLSAC;
    }

    public WebElement getEvents() {
        return events;
    }

    public void setEvents(WebElement events) {
        this.events = events;
    }

    public WebElement getBlog() {
        return blog;
    }

    public void setBlog(WebElement blog) {
        this.blog = blog;
    }

    public WebElement getContactUs() {
        return contactUs;
    }

    public void setContactUs(WebElement contactUs) {
        this.contactUs = contactUs;
    }

    public WebElement getLoginAs() {
        return loginAs;
    }

    public void setLoginAs(WebElement loginAs) {
        this.loginAs = loginAs;
    }

    public WebElement getSearch() {
        return search;
    }

    public void setSearch(WebElement search) {
        this.search = search;
    }

    public WebElement getLogo() {
	return logo;
    }
    
    public void setLogo(WebElement logo) {
	this.logo = logo;
    }

    public WebElement getChooseLawSchool() {
        return chooseLawSchool;
    }

    public void setChooseLawSchool(WebElement chooseLawSchool) {
        this.chooseLawSchool = chooseLawSchool;
    }

    public WebElement getApplyLawSchool() {
        return applyLawSchool;
    }

    public void setApplyLawSchool(WebElement applyLawSchool) {
        this.applyLawSchool = applyLawSchool;
    }

    public WebElement getDataAndResearch() {
        return dataAndResearch;
    }

    public void setDataAndResearch(WebElement dataAndResearch) {
        this.dataAndResearch = dataAndResearch;
    }

    public WebElement getLink1() {
	return discoverLaw;
    }

    public void setLink1(WebElement discoverLaw) {
	this.discoverLaw = discoverLaw;
    }
    
    public WebElement getLink2() {
	return lsat;
    }

    public void setLink2(WebElement lsat) {
	this.lsat = lsat;
    }

}
