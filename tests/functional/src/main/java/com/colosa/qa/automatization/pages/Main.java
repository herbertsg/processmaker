package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main extends Page{

    WebElement weSectionButton;
    WebElement weLogout;
    List<WebElement> wel;

    public Main(BrowserInstance browserInstance) {
        super(browserInstance);
    }


    /*public void goSection(String sectionName) throws FileNotFoundException, IOException, Exception{

        //this.we = null;
        browser.switchToDefaultContent();
        System.out.println("Redireccionando a seccion "+sectionName+"...");
        //this.waitForElementPresent(By.cssSelector("ul#pm_menu li a"),60);
        //this.we = browser.findElementById(ConfigurationSettings.getInstance().getSetting("main.menu"));

        this.weMainMenu = browser.findElement("main.WebElement.DashboardMenu");

        this.weSectionButton = this.weMainMenu.findElement(By.linkText(sectionName));
        
        
        //System.out.println(this.we); //raro pero se necesita esta linea para que funcione correctamente
        if(this.weSectionButton == null)
            throw new Exception("Invalid section name.");

        this.weSectionButton.click();
    }*/

    public void goHome() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElement("main.WebElement.HomeMenu");
        
        this.weSectionButton.click();
            
    }

    public void goDesigner() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElement("main.WebElement.DesignerMenu");
        
        this.weSectionButton.click();
    }

    public void goDashboards() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElement("main.WebElement.DashboardMenu");

        this.weSectionButton.click();
    }

    public void goAdmin() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElement("main.WebElement.AdminMenu");
        
        this.weSectionButton.click();
    }

    public void logout() throws FileNotFoundException, IOException, Exception{
        //browser.switchToDefaultContent();
        //if(this.skin==0)
        browser.switchToDefaultContent();
        browser.findElement("main.WebElement.Logout").click();
    }

    public void profile() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        browser.findElement("main.WebElement.profile").click();
    }

/*    public void goToUrl(String url) throws FileNotFoundException, IOException, Exception{
        Browser.gotoUrl(url);
    }*/

}