package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.InvalidPageException;
import com.colosa.qa.automatization.common.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main extends Page{

    WebElement weSectionButton;
    WebElement weLogout;
    List<WebElement> wel;

    public Main(BrowserInstance browserInstance) throws Exception {
        super(browserInstance);

        //validate if we are in the page
        browser.switchToDefaultContent();
        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        WebElement logoutLink = null;
        browser.switchToDefaultContent();

        logoutLink = browser.findElement("login.WebElement.logoutButton");
    }


    /*public void goSection(String sectionName) throws FileNotFoundException, IOException, Exception{

        //this.we = null;
        browser.switchToDefaultContent();
        Logger.addLog("Redireccionando a seccion "+sectionName+"...");
        //this.waitForElementPresent(By.cssSelector("ul#pm_menu li a"),60);
        //this.we = browser.findElementById(ConfigurationSettings.getInstance().getSetting("main.menu"));

        this.weMainMenu = browser.findElement("main.WebElement.DashboardMenu");

        this.weSectionButton = this.weMainMenu.findElement(By.linkText(sectionName));
        
        
        //Logger.addLog(this.we); //raro pero se necesita esta linea para que funcione correctamente
        if(this.weSectionButton == null)
            throw new Exception("Invalid section name.");

        this.weSectionButton.click();
    }*/

    public void goHome() throws FileNotFoundException, IOException, Exception{

        //verify that we are in the correct page
        verifyPage();

        //Logger.addLog("Main.gotoHome");

        this.weSectionButton = browser.findElement("main.WebElement.HomeMenu");

        //Logger.addLog("Main.gotoHome2");
        //verify element exist

        this.weSectionButton.click();

        //Logger.addLog("Main.gotoHome3");

        //browser.waitForDocumentCompleted(15);

        //Logger.addLog("Main.gotoHome4");
    }

    public void goDesigner() throws FileNotFoundException, IOException, Exception{
        verifyPage();
        this.weSectionButton = browser.findElement("main.WebElement.DesignerMenu");
        
        this.weSectionButton.click();
        //browser.waitForDocumentCompleted(5);
    }

    public void goDashboards() throws FileNotFoundException, IOException, Exception{
        verifyPage();

        this.weSectionButton = browser.findElement("main.WebElement.DashboardMenu");

        this.weSectionButton.click();
        //browser.waitForDocumentCompleted(5);
    }

    public void goAdmin() throws FileNotFoundException, IOException, Exception{
        verifyPage();

        this.weSectionButton = browser.findElement("main.WebElement.AdminMenu");
        
        this.weSectionButton.click();
        //browser.waitForDocumentCompleted(5);
    }

    public void logout() throws FileNotFoundException, IOException, Exception{
        verifyPage();
        //browser.switchToDefaultContent();
        //if(this.skin==0)

        browser.findElement("main.WebElement.Logout").click();
        //browser.waitForDocumentCompleted(5);
    }

    public void profile() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        browser.findElement("main.WebElement.profile").click();
    }

/*    public void goToUrl(String url) throws FileNotFoundException, IOException, Exception{
        Browser.gotoUrl(url);
    }*/

}